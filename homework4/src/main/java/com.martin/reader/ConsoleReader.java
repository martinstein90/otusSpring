package com.martin.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readLine() throws IOException {
        return reader.readLine();
    }
}
