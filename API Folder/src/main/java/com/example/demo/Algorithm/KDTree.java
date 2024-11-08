package com.example.demo.Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
}
