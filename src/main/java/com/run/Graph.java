package com.run;

import java.util.HashMap;

public class Graph {

    HashMap<Integer, Vertex> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

}

class Vertex {
    String node;
    int weight;

    Vertex(String node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}