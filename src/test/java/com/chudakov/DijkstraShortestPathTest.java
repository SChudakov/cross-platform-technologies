package com.chudakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class DijkstraShortestPathTest {

    @Test
    public void testSourceNotInGraph() {
        Graph graph = new Graph();
        graph.addVertex(1);

        DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    shortestPath.getShortestPath(0, 1);
                });
    }

    @Test
    public void testTargetNotInGraph() {
        Graph graph = new Graph();
        graph.addVertex(0);

        DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph);
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

        DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph);
        Assertions.assertNull(shortestPath.getShortestPath(0, 1));
    }

    @Test
    public void testShortestPathIsCorrect1() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4), shortestPath.getShortestPath(1, 4));
    }

    @Test
    public void testShortestPathIsCorrect2() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.addEdge(0, 2);
        graph.addEdge(2, 4);

        DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph);
        Assertions.assertEquals(Arrays.asList(0, 2, 4),
                shortestPath.getShortestPath(0, 4));
    }
}