package com.chudakov;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStarShortestPath extends BaseShortestPathAlgorithm {
    private AStartHeuristic heuristic;

    private Queue<Pair<Integer, Integer>> heap;
    private Map<Integer, Pair<Integer, Integer>> distanceAndPredecessorMap;
    private Map<Integer, Integer> gScoreMap;
    private Map<Integer, Pair<Integer, Integer>> vertexToHeapNodeMap;
    private Set<Integer> visited;

    public AStarShortestPath(Graph graph, AStartHeuristic heuristic) {
        super(graph);
        this.heuristic = heuristic;
    }

    public List<Integer> getShortestPath(Integer source, Integer sink) {
        assertCorrectSourceAndTarget(source, sink);
        if (source.equals(sink)) {
            return Collections.singletonList(source);
        }

        heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getLeft(), o2.getRight()));
        distanceAndPredecessorMap = new HashMap<>();
        gScoreMap = new HashMap<>();
        vertexToHeapNodeMap = new HashMap<>();
        visited = new HashSet<>();

        gScoreMap.put(source, 0);
        distanceAndPredecessorMap.put(source, Pair.of(0, null));
        Pair<Integer, Integer> sourceVertexEntry = Pair.of(0, source);
        heap.add(sourceVertexEntry);
        vertexToHeapNodeMap.put(source, sourceVertexEntry);

        do {
            Pair<Integer, Integer> currentNode = heap.remove();

            if (currentNode.getValue().equals(sink)) {
                return composePath(source, sink, distanceAndPredecessorMap);
            }

            expandNode(currentNode, sink);
            visited.add(currentNode.getValue());
        } while (!heap.isEmpty());

        return composePath(source, sink, distanceAndPredecessorMap);
    }

    private void expandNode(Pair<Integer, Integer> currentNode, Integer sink) {

        Integer distance = currentNode.getLeft();
        Integer vertex = currentNode.getRight();

        for (Integer successor : graph.getSuccessors(vertex)) {
            if (successor.equals(currentNode.getValue())) {
                continue;
            }

            int gScore_current = gScoreMap.get(vertex);
            int tentativeGScore = gScore_current + 1;
            int fScore = tentativeGScore + heuristic.getEstimatedDistance(successor, sink);

            if (vertexToHeapNodeMap.containsKey(successor)) {
                if (tentativeGScore >= gScoreMap.get(successor)) {
                    continue;
                }

                distanceAndPredecessorMap.put(successor, Pair.of(tentativeGScore, vertex));
                gScoreMap.put(successor, tentativeGScore);

                if (visited.contains(successor)) {
                    visited.remove(successor);
                    heap.add(Pair.of(fScore, successor));
                }
                heap.add(Pair.of(fScore, successor));
            } else {
                distanceAndPredecessorMap.put(successor, Pair.of(tentativeGScore, vertex));
                gScoreMap.put(successor, tentativeGScore);

                Pair<Integer, Integer> heapEntry = Pair.of(fScore, successor);
                heap.add(heapEntry);
                vertexToHeapNodeMap.put(successor, heapEntry);
            }
        }
    }
}
