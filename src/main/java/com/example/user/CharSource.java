package com.example.user;

import java.io.IOException;

public interface CharSource {
    char next() throws IOException;
    boolean hasNext();
    int getPos();
}
