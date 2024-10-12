package com.example.demo.Algorithm;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        String filePath = "API Folder/src/main/java/com/example/demo/Algorithm/export.geojson"; // Specify your GeoJSON file path
        Graph graph = GraphBuilder.buildGraphFromGeoJSON(filePath);

        // Specify start and goal nodes manually or dynamically
        Node start = new Node("Start", 28.4874428, 77.0888263); // Example coordinates
        Node goal = new Node("Goal", 28.4876165, 77.005289);   // Example coordinates
        
        List<Node> path = AStarAlgorithm.aStar(graph, start, goal);

        if (path != null) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println(node.name + " (" + node.latitude + ", " + node.longitude + ")");
            }
        } else {
            System.out.println("No path found");
        }
    }
}
