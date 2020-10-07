package com.chudakov;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    final Map<Integer, Set<Integer>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    public Graph(Map<Integer, Set<Integer>> graph) {
        this.graph = graph;
    }

    public Set<Integer> getSuccessors(Integer v) {
        if (!graph.containsKey(v)) {
            throw new IllegalArgumentException("Graph does not contain vertex " + v);
        }
        return graph.get(v);
    }

    public void addEdge(Integer source, Integer target) {
        addVertex(source);
        addVertex(target);
        graph.get(source).add(target);
    }

    public void addVertex(Integer vertex) {
        graph.putIfAbsent(vertex, new HashSet<>());
    }

    public boolean containsVertex(Integer vertex) {
        return graph.containsKey(vertex);
    }
}
