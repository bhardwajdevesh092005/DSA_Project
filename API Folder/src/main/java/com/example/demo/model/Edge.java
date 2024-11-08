package com.example.demo.model;

public class Edge {
    public Node start;
    public Node end;
    public double cost;

    public Edge(Node start, Node end, double cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}
