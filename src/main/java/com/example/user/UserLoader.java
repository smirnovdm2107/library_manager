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
        private static class Pair<T, K> {
            final T first;
            final K second;
            public Pair(T first, K second) {
                this.first = first;
                this.second = second;
            }


            public T getFirst() {
                return first;
            }

            public K getSecond() {
                return second;
            }
        }
        public Parser(CharSource source) throws IOException{
            super(source);
        }
        private User parse() throws IOException {
            String login = parseField("login");
            String password = parseField("password");
            String name = parseField("name");
            String surname = parseField("surname");
            return new User(login, password, name, surname);
        }

        private String nextWord() throws IOException{
            skipWhitespaces();
            StringBuilder sb = new StringBuilder();
            while(!isWhitespace()) {
                sb.append(take());
            }
            String result = sb.toString();
            return result.isEmpty() ? null : result;
        }

        private Pair<String, String> parseField() throws IOException {
            String first = nextWord();
            expectFieldSeparator();
            String second = nextWord();
            skipLineSeparator();
            return new Pair<>(first, second);
        }
        private String parseField(String field) throws IOException {
            expect(field);
            expectFieldSeparator();
            String result = nextWord();
            skipLineSeparator();
            return result;
        }

        private void expectFieldSeparator() throws IOException {
            expect(Character::isWhitespace);
            expect(':');
            expect(Character::isWhitespace);
        }
        private void expect(Predicate<Character> predicate) throws IOException {
            if (!take(predicate)) {
                throw new SyntaxException("unexpected char on " + getPos());
            }
        }


        private void skipLineSeparator() throws IOException {
            while(take('\n') || take('\r')) {
                //...do nothing
            }
        }
    }
}
