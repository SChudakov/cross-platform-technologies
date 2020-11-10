package com.chudakov;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraShortestPath extends BaseShortestPathAlgorithm {
    private Queue<Pair<Integer, Integer>> heap;
    private Map<Integer, Pair<Integer, Integer>> distanceAndPredecessorMap;

    public DijkstraShortestPath(Graph graph) {
        super(graph);
    }

    public List<Integer> getShortestPath(Integer source, Integer sink) {
        assertCorrectSourceAndTarget(source, sink);
        if (source.equals(sink)) {
            return Collections.singletonList(source);
        }

        distanceAndPredecessorMap = new HashMap<>();
        distanceAndPredecessorMap.put(source, Pair.of(0, null));

        heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getLeft(), o2.getRight()));
        heap.add(Pair.of(0, source));

        while (!heap.isEmpty()) {
            Pair<Integer, Integer> min = heap.remove();

            Integer distance = min.getLeft();
            Integer vertex = min.getRight();

            if (vertex.equals(sink)) {
                break;
            }

            for (Integer successor : graph.getSuccessors(vertex)) {
                updateDistance(successor, vertex, distance + 1);
            }
        }

        return composePath(source, sink, distanceAndPredecessorMap);
    }

    private void updateDistance(Integer vertex, Integer predecessor, int distance) {
        Pair<Integer, Integer> distanceAndPredecessor = distanceAndPredecessorMap.get(vertex);
        if (distanceAndPredecessor == null || distance < distanceAndPredecessor.getLeft()) {
            heap.add(Pair.of(distance, vertex));
            distanceAndPredecessorMap.put(vertex, Pair.of(distance, predecessor));
        }
    }
}
