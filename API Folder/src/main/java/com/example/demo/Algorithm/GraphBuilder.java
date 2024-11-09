package com.example.demo.Algorithm;


import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Node;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class GraphBuilder {
    private static final List<Node> node_list = new ArrayList<>();
    public static Graph graph = new Graph();
    public static Graph buildGraphFromGeoJSON(String filePath) {
        JsonObject geoJson = GeoJSONParser.parseGeoJSON(filePath);
        JsonArray features = geoJson.getAsJsonArray("features");
        for (int i = 0; i < features.size(); i++) {
            JsonObject feature = features.get(i).getAsJsonObject();
            JsonObject geometry = feature.getAsJsonObject("geometry");
            JsonArray coordinates = geometry.getAsJsonArray("coordinates");

            String geometryType = geometry.get("type").getAsString();

            if (geometryType.equals("Polygon")) {
                processPolygonFeature(graph, coordinates, i);
            } else if (geometryType.equals("LineString")) {
                processLineStringFeature(graph, coordinates, i);
            }
        }
        graph.tree = new KDTree(node_list);

        for(Node x:graph.getNodes())
        {
            List<Node> neighbors = graph.tree.findKNearestNeighbors(x, 1);
            for(Node y:neighbors)
            {
                double distance = AStarAlgorithm.haversine(x.latitude, x.longitude, y.latitude, y.longitude);
                graph.addEdge(x, y,distance);
            }
        }

        System.out.println("Graph Built successfully");
        return graph;
    }

    private static void processPolygonFeature(Graph graph, JsonArray coordinates, int featureIndex) {
        JsonArray polygonCoordinates = coordinates.get(0).getAsJsonArray();

        Node prevNode = null;
        Node firstNode = null;

        for (int j = 0; j < polygonCoordinates.size(); j++) {
            JsonArray latlon = polygonCoordinates.get(j).getAsJsonArray();
            double lon = latlon.get(0).getAsDouble();
            double lat = latlon.get(1).getAsDouble();

            Node node = new Node("Node_" + featureIndex + "_" + j, lat, lon);
            node_list.add(node);
            graph.addNode(node);

            if (prevNode != null) {
                double distance = AStarAlgorithm.haversine(prevNode.latitude, prevNode.longitude, node.latitude, node.longitude);
                graph.addEdge(prevNode, node, distance);
            }

            if (firstNode == null) {
                firstNode = node;
            }

            prevNode = node;
        }

        // Close the polygon (connect last node to the first)
        if (firstNode != null && prevNode != null) {
            double distance = AStarAlgorithm.haversine(prevNode.latitude, prevNode.longitude, firstNode.latitude, firstNode.longitude);
            graph.addEdge(prevNode, firstNode, distance);
        }
    }

    private static void processLineStringFeature(Graph graph, JsonArray coordinates, int featureIndex) {
        Node prevNode = null;

        for (int j = 0; j < coordinates.size(); j++) {
            JsonArray latlon = coordinates.get(j).getAsJsonArray();
            double lon = latlon.get(0).getAsDouble();
            double lat = latlon.get(1).getAsDouble();

            Node node = new Node("LineNode_" + featureIndex + "_" + j, lat, lon);
            node_list.add(node);
            graph.addNode(node);

            if (prevNode != null) {
                double distance = AStarAlgorithm.haversine(prevNode.latitude, prevNode.longitude, node.latitude, node.longitude);
                graph.addEdge(prevNode, node, distance);
            }

            prevNode = node;
        }
    }
}

