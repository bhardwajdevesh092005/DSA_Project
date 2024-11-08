package com.example.demo.Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.model.Node;
public class Main {
    public static void main(String[] args) {
        String filePath = "API Folder/src/main/java/com/example/demo/Algorithm/export.geojson"; // Specify your GeoJSON
                                                                                                // file path
        Graph graph = GraphBuilder.buildGraphFromGeoJSON(filePath);
        Set<Node> nodes = graph.getNodes();
        List<Node> nodeList = new ArrayList<>();
        for(Node z:nodes)nodeList.add(z);
        KDTree tree = new KDTree(nodeList);
        // Specify start and goal nodes manually or dynamically
        Node start = tree.findKNearestNeighbors(new Node("Start",28.450458,77.0432859),1).get(0); // Example coordinates
        Node goal =  tree.findKNearestNeighbors(new Node("End",28.4520255,77.0437478),1).get(0); // Example coordinates

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
