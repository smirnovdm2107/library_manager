package com.example.user;

import com.example.user.User;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

public class UserLoader {
    private final User user;

    public UserLoader(String path) throws IOException {
        user = new Parser(new InputStreamCharSource(new FileInputStream(path))).parse();
    }

    public User getUser() {
        return user;
    }
    private static class Parser extends BaseParser {

        public Parser(CharSource source) throws IOException{
            super(source);
        }
        private User parse() throws IOException {
            String login = parseField("login");
            String password = parseField("password");
            String name = parseField("name");
            String surname = parseField("surname");
            return new User(name, surname, login, password);
        }

        private String nextWord() throws IOException{
            StringBuilder sb = new StringBuilder();
            while(!isWhitespace()) {
                sb.append(take());
            }
            return sb.toString();
        }

        private String parseField(String field) throws IOException {
            expect(field);
            expect(Character::isWhitespace);
            expect(':');
            expect(Character::isWhitespace);
            return nextWord();
        }
        private void expect(Predicate<Character> predicate) throws IOException {
            if (!take(predicate)) {
                throw new SyntaxException("unexpected char on " + getPos());
            }
        }
    }
}
