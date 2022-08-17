package com.example.user;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamCharSource implements CharSource {

    private final Reader input;
    private int pos = 0;
    private static final int END = -1;
    private int ch;

    public InputStreamCharSource(final InputStream input) throws IOException {
        this.input = new BufferedReader(new InputStreamReader(input, StandardCharsets.US_ASCII));
        ch = input.read();
    }

    @Override
    public char next() throws IOException {
        char res = (char) ch;
        ch = input.read();
        ++pos;
        return res;
    }

    @Override
    public boolean hasNext() {
        return ch != -1;
    }

    @Override
    public int getPos() {
        return pos;
    }
}
