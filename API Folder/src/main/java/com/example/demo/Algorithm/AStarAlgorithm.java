package com.example.demo.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.example.demo.model.Edge;
import com.example.demo.model.Node;
public class AStarAlgorithm {
    // Heuristic function for estimating the cost from a node to the goal
    public static double heuristic(Node a, Node b) {
        return haversine(a.latitude, a.longitude, b.latitude, b.longitude);
    }

    // A* search algorithm implementation
    public static List<Node> aStar(Graph graph, Node start, Node goal) {
        // for (Node node : graph.getNodes()) {
        //     System.out.println("Node: " + node.latitude + " " + node.longitude);
        //     System.out.println(graph.getNeighbors(node).size());
        //     for (Edge edge : graph.getNeighbors(node)) {
        //         System.out.println("  Edge to: " + edge.end.latitude +" " + edge.end.longitude+ " Cost: " + edge.cost);
        //     }
        // }
        if(start == null || goal == null)
        {
            return null;
        }
        // Early exit if start and goal are the same
        if (start.equals(goal)) {
            return Collections.singletonList(start);
        }
                
        Map<Node, Node> cameFrom = new HashMap<>();  // Used to reconstruct the path

        // The cost of getting from the start node to each node
        Map<Node, Double> gScore = new HashMap<>();
        gScore.put(start, 0.0);

        // The estimated total cost (gScore + heuristic) from start to goal through each node
        Map<Node, Double> fScore = new HashMap<>();
        fScore.put(start, heuristic(start, goal));

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> fScore.getOrDefault(n, Double.POSITIVE_INFINITY)));
        Set<Node> openSetTracker = new HashSet<>();  // Set to track if a node is in the open set

        // Initialize open set with the start node
        openSet.add(start);
        openSetTracker.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            openSetTracker.remove(current);

            // If we reach the goal, reconstruct and return the path
            if (current.equals(goal)) {
                System.out.println("End Matched");
                return reconstructPath(cameFrom, goal,current);
            }

            // Explore the neighbors of the current node
            for (Edge edge : graph.getNeighbors(current)) {
                Node neighbor = edge.end;
                double tentative_gScore = gScore.getOrDefault(current, Double.POSITIVE_INFINITY) + edge.cost;
                // System.out.println("Current Node: " + current);
                // System.out.println("gScore: " + gScore);
                // System.out.println("fScore: " + fScore);

                // If a shorter path to the neighbor is found
                if (tentative_gScore < gScore.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentative_gScore);
                    fScore.put(neighbor, tentative_gScore + heuristic(neighbor, goal));

                    // Add the neighbor to the open set if it's not already in it
                    if (!openSetTracker.contains(neighbor)) {
                        openSet.add(neighbor);
                        openSetTracker.add(neighbor);
                    }
                }
            }
        }
        for(Node x:cameFrom.keySet())
        {
            System.out.println(x);
            System.out.println(cameFrom.get(x));
        }
        // If no path is found, return null
        return reconstructPath(cameFrom, goal,start);
    }

    // Haversine formula to calculate the great-circle distance between two points
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }

    // Reconstruct the path from the goal node back to the start node
    private static List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current,Node start) {
        List<Node> path = new ArrayList<>();
        path.add(current); // Include the goal node
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        System.out.println(start.latitude);
        Collections.reverse(path); // Reverse the path to get it from start to goal
        // for(Node x:path)
        // {
        //     System.out.println(Double.toString(x.latitude) + " " + Double.toString(x.longitude));
        // }
        System.out.println("Path formed");
        return path;
    }
}
