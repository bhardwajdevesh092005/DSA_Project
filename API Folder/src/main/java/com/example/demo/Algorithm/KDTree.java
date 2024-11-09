package com.example.demo.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.example.demo.model.Node;

public class KDTree {

    private final Node root;

    public KDTree(List<Node> nodes) {
        this.root = buildTree(nodes, 0);
    }

    private Node buildTree(List<Node> nodes, int depth) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        // Alternate sorting by latitude (0) and longitude (1)
        int axis = depth % 2;
        nodes.sort(axis == 0 ? Comparator.comparingDouble(node -> node.latitude) : Comparator.comparingDouble(node -> node.longitude));

        int medianIndex = nodes.size() / 2;
        Node medianNode = nodes.get(medianIndex);

        // Divide into left and right sublists
        List<Node> leftSublist = nodes.subList(0, medianIndex);
        List<Node> rightSublist = nodes.subList(medianIndex + 1, nodes.size());

        //build left and right subtrees using rcursion and assign the returned values to the left and the right parameter of the node
        medianNode.left = buildTree(new ArrayList<>(leftSublist), depth + 1);
        medianNode.right = buildTree(new ArrayList<>(rightSublist), depth + 1);

        return medianNode;
    }

    public Node getRoot() {
        return root;
    }
    public List<Node> findKNearestNeighbors(Node target, int k) {
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(nd -> -nd.distance));
        knnSearch(root, target, k, 0, pq);

        List<Node> neighbors = new ArrayList<>();
        while (!pq.isEmpty()) {
            neighbors.add(pq.poll().node);
        }
        Collections.reverse(neighbors);  // To get closest neighbors in ascending order
        return neighbors;
    }

    private void knnSearch(Node current, Node target, int k, int depth, PriorityQueue<NodeDistance> pq) {
        if (current == null) {
            return;
        }

        double distance = calculateDistance(current, target);
        if (!current.equals(target))
        {
        if (pq.size() < k) {
            pq.add(new NodeDistance(current, distance));
        } else if (distance < pq.peek().distance) {
            pq.poll();
            pq.add(new NodeDistance(current, distance));
        }
    }

        int axis = depth % 2;
        Node next = (axis == 0 ? target.latitude < current.latitude : target.longitude < current.longitude) ? current.left : current.right;
        Node other = next == current.left ? current.right : current.left;

        knnSearch(next, target, k, depth + 1, pq);

        double axisDistance = axis == 0 ? Math.abs(target.latitude - current.latitude) : Math.abs(target.longitude - current.longitude);
        if (pq.size() < k || axisDistance < pq.peek().distance) {
            knnSearch(other, target, k, depth + 1, pq);
        }
    }
    private double calculateDistance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.latitude - b.latitude, 2) + Math.pow(a.longitude - b.longitude, 2));
    }

    private static class NodeDistance {
        Node node;
        double distance;

        public NodeDistance(Node node, double distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
