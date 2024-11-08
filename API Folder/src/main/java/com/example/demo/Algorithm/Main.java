package com.example.demo.Algorithm;

import java.util.List;

import com.example.demo.model.Node;
public class Main {
    public static void main(String[] args) {
        String filePath = "API Folder/src/main/java/com/example/demo/Algorithm/export.geojson"; // Specify your GeoJSON
                                                                                                // file path
        Graph graph = GraphBuilder.buildGraphFromGeoJSON(filePath);

        // Specify start and goal nodes manually or dynamically
        Node start = new Node("Start", 28.450458,77.0432859); // Example coordinates
        Node goal = new Node("Goal",  28.4520255,77.0437478); // Example coordinates

        List<Node> path = AStarAlgorithm.aStar(graph, start, goal);

        if (path != null) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.latitude + ", " + node.longitude + ")");
            }
        } else {
            System.out.println("No path found");
        }
    }
}
