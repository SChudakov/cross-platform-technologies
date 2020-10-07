package com.chudakov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader {
    public Graph readGraph(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Graph result = new Graph();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] sourceAndTarget = line.split(" ");
                if (sourceAndTarget.length != 2) {
                    throw new RuntimeException("Invalid graph format in file: " + filePath);
                }
                Integer source = Integer.valueOf(sourceAndTarget[0]);
                Integer target = Integer.valueOf(sourceAndTarget[1]);
                result.addEdge(source, target);
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading graph from file: " + filePath, e);
        }
    }
}
