package com.example.demo.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.demo.model.Node;

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
    private final Map<Node, List<Edge>> adjList = new HashMap<>();

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

