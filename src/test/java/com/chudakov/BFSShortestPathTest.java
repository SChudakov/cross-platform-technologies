package com.chudakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class BFSShortestPathTest {

    @Test
    public void testSourceNotInGraph() {
        Graph graph = new Graph();
        graph.addVertex(1);

        BFSShortestPath shortestPath = new BFSShortestPath(graph);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    shortestPath.getShortestPath(0, 1);
                });
    }

    @Test
    public void testTargetNotInGraph() {
        Graph graph = new Graph();
        graph.addVertex(0);

        BFSShortestPath shortestPath = new BFSShortestPath(graph);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    shortestPath.getShortestPath(0, 1);
                });
    }

    @Test
    public void testNoPath() {
        Graph graph = new Graph();
        graph.addVertex(0);
        graph.addVertex(1);

        BFSShortestPath shortestPath = new BFSShortestPath(graph);
        Assertions.assertNull(shortestPath.getShortestPath(0, 1));
    }

    @Test
    public void testShortestPathIsCorrect() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.addEdge(0, 2);
        graph.addEdge(2, 4);

        BFSShortestPath shortestPath = new BFSShortestPath(graph);
        Assertions.assertEquals(Arrays.asList(0, 2, 4),
                shortestPath.getShortestPath(0, 4));
    }
}