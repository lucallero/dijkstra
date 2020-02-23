package com.run;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {

    HashMap<Vertex, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    void addVertex(Vertex vertex) {
        if (!this.adjacencyList.containsKey(vertex)) {
            this.adjacencyList.put(vertex, new ArrayList<Edge>());
        }
    }

    void addEdge(Vertex vertex1, Vertex vertex2, int weight) {
        if (!this.adjacencyList.containsKey(vertex1)) {
            addVertex(vertex1);
        }
        if (!this.adjacencyList.containsKey(vertex2)) {
            addVertex(vertex2);
        }
        this.adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
        this.adjacencyList.get(vertex2).add(new Edge(vertex1, weight));
    }

    List<String> dijkstra(Vertex start, Vertex finish) {
        PriorityQueue<Vertex> nodes = new PriorityQueue<>();
        HashMap<Vertex, Integer> distances = new HashMap<>();
        HashMap<Vertex, Vertex> previous = new HashMap<>();
        Vertex smallest = null;
        List<String> path = new ArrayList<>();

        // build initial state
        for (Vertex vertex : this.adjacencyList.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                vertex.priority = 0;
                nodes.add(vertex);
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                vertex.priority = Integer.MAX_VALUE;
                nodes.add(vertex);
            }
            previous.put(vertex, null);
        }
        // as long as there is something to visit
        while (!(nodes.isEmpty())) {
            smallest = nodes.poll();
            if (smallest.equals(finish)) {
                System.out.println(distances);
                System.out.println(previous);
                // done
                // build path to return
                while (previous.get(smallest) != null) {
                    path.add(smallest.value);
                    smallest = previous.get(smallest);
                }
                path.add(smallest.value);
                Collections.reverse(path);
                break;
            }
            if (smallest != null || distances.get(smallest) != Integer.MAX_VALUE) {
                for (Edge neighbor : this.adjacencyList.get(smallest)) {
                    // calculate distance to neighbor
                    int candidate = distances.get(smallest) + neighbor.weight;
                    if (candidate < distances.get(neighbor.vertex)) {
                        // updating new samllest distance do neighbor
                        distances.put(neighbor.vertex, candidate);
                        // update previos - how we got to neighbor
                        previous.put(neighbor.vertex, smallest);
                        // enqueue in priority queue with new priority
                        neighbor.vertex.priority = candidate;
                        nodes.add(neighbor.vertex);
                    }
                }
            }
        }
        System.out.println(path);
        return path;
    }

    @Override
    public String toString() {
        return "AdjacencyLyst:" + this.adjacencyList;
    }

    public static void main(String[] args) {
        Graph app = new Graph();
        app.addEdge(new Vertex("A"), new Vertex("B"), 4);
        app.addEdge(new Vertex("A"), new Vertex("C"), 2);
        app.addEdge(new Vertex("B"), new Vertex("E"), 3);
        app.addEdge(new Vertex("C"), new Vertex("D"), 2);
        app.addEdge(new Vertex("C"), new Vertex("F"), 4);
        app.addEdge(new Vertex("D"), new Vertex("E"), 3);
        app.addEdge(new Vertex("D"), new Vertex("F"), 1);
        app.addEdge(new Vertex("E"), new Vertex("F"), 1);
        app.dijkstra(new Vertex("A"), new Vertex("E"));
    }
}

class Edge implements Comparable<Edge> {
    Vertex vertex;
    int weight;

    Edge(Vertex vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.vertex + "(" + this.weight + ")";
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        return this.vertex.equals(((Edge) obj).vertex);
    }
}

class Vertex implements Comparable<Vertex> {
    String value;
    int priority;

    Vertex(String val) {
        this.value = val;
        this.priority = Integer.MAX_VALUE;
    }

    Vertex(String val, int priority) {
        this.value = val;
        this.priority = priority;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.priority - o.priority;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < this.value.length(); i++) {
            hash = hash * 31 + this.value.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex)) {
            return false;
        }
        return this.value.equals(((Vertex) obj).value);
    }
}

// class PriorityQueue {
// List<Vertex> values = new ArrayList<>();

// void enqueue(Vertex v, int priority) {
// v.priority = priority;
// this.values.add(v);
// Collections.sort(this.values);
// }

// Vertex dequeue() {
// return this.values.remove(0);
// }
// }