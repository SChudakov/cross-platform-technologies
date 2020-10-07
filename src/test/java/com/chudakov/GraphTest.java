package com.chudakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class GraphTest {
    @Test
    public void testEmptyGraph() {
        Graph graph = new Graph();
        Assertions.assertFalse(graph.containsVertex(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            graph.getSuccessors(0);
        });
    }

    @Test
    public void testSmallGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);


        Assertions.assertTrue(graph.containsVertex(0));
        Assertions.assertTrue(graph.containsVertex(1));
        Assertions.assertTrue(graph.containsVertex(2));

        Assertions.assertEquals(Collections.singleton(1), graph.getSuccessors(0));
        Assertions.assertEquals(Collections.singleton(2), graph.getSuccessors(1));
    }
}