package com.file_reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Dmytro Honchar
 * Date: 9/7/2022
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String twoLines =
                CustomFileReader.processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }
}

@FunctionalInterface
interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}

class CustomFileReader {
    static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }
}
