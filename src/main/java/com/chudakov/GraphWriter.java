package com.chudakov;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class GraphWriter {
    public void writeGraph(String path, Graph graph) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<Integer, Set<Integer>> entry : graph.graph.entrySet()) {
                int source = entry.getKey();
                for (int target : entry.getValue()) {
                    writer.write(String.valueOf(source));
                    writer.write(" ");
                    writer.write(String.valueOf(target));
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
