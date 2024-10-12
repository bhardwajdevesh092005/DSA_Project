package com.example.demo.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Node {
    String name;
    double latitude;
    double longitude;

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return Math.abs(other.latitude-latitude) < 1e-8d && Math.abs(other.longitude-longitude) < 1e-8d;
            //    name.equals(other.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        long latBits = Double.doubleToLongBits(latitude);
        long lonBits = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (latBits ^ (latBits >>> 32));
        result = 31 * result + (int) (lonBits ^ (lonBits >>> 32));
        return result;
    }
}

class Edge {
    Node start;
    Node end;
    double cost;

    public Edge(Node start, Node end, double cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

public class Graph {
    private Map<Node, List<Edge>> adjList = new HashMap<>();

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node start, Node end, double cost) {
        addNode(start); // Ensure the start node is added
        addNode(end);   // Ensure the end node is added
        adjList.get(start).add(new Edge(start, end, cost));
    }

    public List<Edge> getNeighbors(Node node) {
        List<Edge> neighbors = adjList.get(node);
        return (neighbors != null) ? neighbors : new ArrayList<>();
    }

    public Node getNode(Node node)
    {
        for(Node x:this.getNodes())
        {
            if(node.equals(x))
            {
                System.out.println("Found found found");
                return x;
            }
        }
        return null;
    }

    // Method to get all nodes in the graph
    public Set<Node> getNodes() {
        return adjList.keySet(); // Return the set of nodes
    }
}

