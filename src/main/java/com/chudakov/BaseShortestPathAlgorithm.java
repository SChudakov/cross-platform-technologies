package com.chudakov;

import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class BaseShortestPathAlgorithm implements ShortestPathAlgorithm {
    protected final Graph graph;

    public BaseShortestPathAlgorithm(Graph graph) {
        this.graph = graph;
    }

    protected void assertCorrectSourceAndTarget(Integer source, Integer sink) {
        if (!graph.containsVertex(source)) {
            throw new IllegalArgumentException("Graph must contain the source vertex.");
        }
        if (!graph.containsVertex(sink)) {
            throw new IllegalArgumentException("Graph must contain the target vertex.");
        }
    }

    protected List<Integer> composePath(Integer source, Integer target,
                                      Map<Integer, Pair<Integer, Integer>> distanceAndPredecessorMap) {
        LinkedList<Integer> result = new LinkedList<>();

        Integer it = target;
        Pair<Integer, Integer> dapEntry = distanceAndPredecessorMap.get(it);

        if (dapEntry == null) {
            return null;
        }

        while (dapEntry.getRight() != null) {
            result.addFirst(it);

            it = dapEntry.getRight();
            dapEntry = distanceAndPredecessorMap.get(it);
        }
        result.addFirst(source);

        return result;
    }
}
