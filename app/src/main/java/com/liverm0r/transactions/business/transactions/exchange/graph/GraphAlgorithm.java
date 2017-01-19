package com.liverm0r.transactions.business.transactions.exchange.graph;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphAlgorithm {

    private final List<CurrencyNode> mNodes;
    private final List<CurrencyEdge> mEdges;
    private Set<CurrencyNode> mSettledNodes;
    private Set<CurrencyNode> mUnSettledNodes;
    private Map<CurrencyNode, CurrencyNode> mPredecessors;
    private Map<CurrencyNode, Float> mRates;

    private CurrencyNode mCurrentSource;

    public GraphAlgorithm(RatesGraph graph) {
        mNodes = new ArrayList<>(graph.getVertexes());
        mEdges = new ArrayList<>(graph.getEdges());

        mRates = new HashMap<>();
        mPredecessors = new HashMap<>();
    }

    //—————————————————————————————————————————————————————————————————————— api

    public void calc(@NonNull CurrencyNode source) {

        mCurrentSource = source;

        mSettledNodes = new HashSet<>(mNodes.size());
        mUnSettledNodes = new HashSet<>(mNodes.size());
        mRates = new HashMap<>();
        mPredecessors = new HashMap<>();
        mRates.put(source, 1.0f); // source change on source with no effect
        mUnSettledNodes.add(source);
        while (mUnSettledNodes.size() > 0) {
            CurrencyNode node = getMinimum(mUnSettledNodes);
            mSettledNodes.add(node);
            mUnSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    public LinkedList<CurrencyNode> getPath(@NonNull CurrencyNode target) {
        LinkedList<CurrencyNode> path = new LinkedList<>();
        CurrencyNode step = target;
        if (mPredecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (mPredecessors.get(step) != null) {
            step = mPredecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

    public @NonNull Map<CurrencyNode, Float> getRates() {
        return mRates;
    }

    //—————————————————————————————————————————————————————————————————————— helpers

    private void findMinimalDistances(CurrencyNode node) {
        List<CurrencyNode> adjacentNodes = getNeighbors(node);
        for (CurrencyNode target : adjacentNodes) {

            // when we exchange currency, we need the product, not the sum
            float newPossibleWeight = getShortestDistance(node) * getDistance(node, target);

            if (getShortestDistance(target) > newPossibleWeight) {
                mRates.put(target, newPossibleWeight);
                mPredecessors.put(target, node);
                mUnSettledNodes.add(target);
            }
        }
    }

    private float getDistance(CurrencyNode node, CurrencyNode target) {
        for (CurrencyEdge edge : mEdges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<CurrencyNode> getNeighbors(CurrencyNode node) {
        List<CurrencyNode> neighbors = new ArrayList<>();
        for (CurrencyEdge edge : mEdges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private CurrencyNode getMinimum(Set<CurrencyNode> vertexes) {
        CurrencyNode minimum = null;
        for (CurrencyNode vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(CurrencyNode vertex) {
        return mSettledNodes.contains(vertex);
    }

    private float getShortestDistance(CurrencyNode destination) {
        Float rate = mRates.get(destination);
        if (rate == null) {
            return Float.MAX_VALUE;
        } else {
            return rate;
        }
    }
}
