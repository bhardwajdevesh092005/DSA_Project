package com.example.demo.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.demo.model.Edge;
import com.example.demo.model.Node;

public class Graph {
    public final Map<Node, List<Edge>> adjList = new HashMap<>();

    public KDTree tree;

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node start, Node end, double cost) {
        addNode(start); // Ensure the start node is added
        addNode(end);   // Ensure the end node is added
        adjList.get(start).add(new Edge(start, end, cost));
        adjList.get(end).add(new Edge(end, start, cost));
    }

    public List<Edge> getNeighbors(Node node) {
        List<Edge> neighbors = adjList.get(node);
        return (neighbors != null) ? neighbors : new ArrayList<>();
    }

    public Node getNode(double latitude,double longitude)
    {
        for(Node x:this.getNodes())
        {
            if(Math.abs(x.latitude-latitude) < 1e-8d && Math.abs(x.longitude-longitude) < 1e-8d)
            {
                System.out.println("Found found found");
                return x;
            }
        }
        return null;
    }

    public Set<Node> getNodes() {
        return adjList.keySet();
    }
}

