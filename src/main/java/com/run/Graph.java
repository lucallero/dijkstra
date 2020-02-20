package com.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    HashMap<String, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    void addVertex(String vertex) {
        if (!this.adjacencyList.containsKey(vertex)) {
            this.adjacencyList.put(vertex, new ArrayList<Edge>());
        }
    }

    void addEdge(String vertex1, String vertex2, int weight) {
        if (!this.adjacencyList.containsKey(vertex1)) {
            addVertex(vertex1);
        }
        if (!this.adjacencyList.containsKey(vertex2)) {
            addVertex(vertex2);
        }
        this.adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
        this.adjacencyList.get(vertex2).add(new Edge(vertex1, weight));
    }

    @Override
    public String toString() {
        return "AdjacencyLyst:" + this.adjacencyList;
    }

    public static void main(String[] args) {
        Graph app = new Graph();

        app.addVertex("A");
        app.addVertex("B");
        app.addVertex("C");

        app.addEdge("A", "B", 20);
        app.addEdge("A", "C", 12);
        app.addEdge("B", "C", 15);

        System.out.println(app);
    }

}

class Edge {
    String vertex;
    int weight;

    Edge(String vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.vertex + "(" + this.weight + ")";
    }

}