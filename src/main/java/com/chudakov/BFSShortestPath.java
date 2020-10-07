package com.chudakov;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSShortestPath extends BaseShortestPathAlgorithm {

    private Map<Integer, Pair<Integer, Integer>> distanceAndPredecessorMap;

    public BFSShortestPath(Graph graph) {
        super(graph);
    }

    public List<Integer> getShortestPath(Integer source, Integer sink) {
        assertCorrectSourceAndTarget(source, sink);
        if (source.equals(sink)) {
            return Collections.singletonList(source);
        }
        distanceAndPredecessorMap = new HashMap<>();
        distanceAndPredecessorMap.put(source, Pair.of(0, null));

        Set<Integer> visited = new HashSet<>();
        visited.add(source);

        Queue<Pair<Integer, Integer>> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(Pair.of(source, 0));

        while (!bfsQueue.isEmpty()) {
            Pair<Integer, Integer> entry = bfsQueue.remove();

            Integer vertex = entry.getLeft();
            Integer distance = entry.getRight();

            if (vertex.equals(sink)) {
                break;
            }

            for (Integer successor : graph.getSuccessors(vertex)) {
                if (!visited.contains(successor)) {
                    visited.add(successor);
                    bfsQueue.add(Pair.of(successor, distance + 1));
                    distanceAndPredecessorMap.put(successor, Pair.of(distance + 1, vertex));
                }
            }
        }

        return composePath(source, sink, distanceAndPredecessorMap);
    }
}
