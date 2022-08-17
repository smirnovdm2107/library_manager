package com.example.user;

import java.io.IOException;
import java.util.function.Predicate;

public class BaseParser {
    protected final CharSource source;
    protected char ch;
    protected static final char END = '\0';

    int pos = 0;
    public BaseParser(CharSource source) throws IOException {
        this.source = source;
        ch = source.next();
    }

    public int getPos() {
        return source.getPos();
    }
    public boolean test(char expected) {
        return ch == expected;
    }

    public boolean test(Predicate<Character> predicate) {
        return predicate.test(ch);
    }

    protected char take() throws IOException {
        char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }
    public boolean take(char expected) throws  IOException {
        if (ch == expected) {
            take();
            return true;
        }
        return false;
    }

    public boolean take(Predicate<Character> predicate) throws IOException {
        if (test(predicate)) {
            take();
            return true;
        }
        return false;
    }

    public void expect(char expected) throws IOException {
        if (!take(expected)) {
            throw new IllegalArgumentException(String.format("expected '%s', found '%s' on %d", expected, ch, getPos()));
        }
    }

    public void expect(String expected) throws IOException {
        for (int i = 0; i < expected.length(); ++i) {
            expect(expected.charAt(i));
        }
    }

    public boolean between(char min, char max) {
        return min <= ch && ch <= max;
    }

    public void skipWhitespaces() throws IOException {
        while(isWhitespace()) {
            take();
        }
    }

    public boolean isWhitespace() {
        return Character.isWhitespace(ch);
    }
}
