package com.example.demo.Algorithm;

import java.util.*;

class AStarAlgorithm {
    // Heuristic function for estimating the cost from a node to the goal
    public static double heuristic(Node a, Node b) {
        return haversine(a.latitude, a.longitude, b.latitude, b.longitude);
    }

    // A* search algorithm implementation
    public static List<Node> aStar(Graph graph, Node start, Node goal) {
        for (Node node : graph.getNodes()) {
            System.out.println("Node: " + node.latitude + " " + node.longitude);
            for (Edge edge : graph.getNeighbors(node)) {
                System.out.println("  Edge to: " + edge.end.latitude +" " + edge.end.longitude+ " Cost: " + edge.cost);
            }
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
                return reconstructPath(cameFrom, current);
            }

            // Explore the neighbors of the current node
            for (Edge edge : graph.getNeighbors(current)) {
                Node neighbor = edge.end;
                double tentative_gScore = gScore.getOrDefault(current, Double.POSITIVE_INFINITY) + edge.cost;
                System.out.println("Current Node: " + current);
                System.out.println("gScore: " + gScore);
                System.out.println("fScore: " + fScore);

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

        // If no path is found, return null
        return null;
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
    private static List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
        List<Node> path = new ArrayList<>();
        path.add(current); // Include the goal node
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        Collections.reverse(path); // Reverse the path to get it from start to goal
        return path;
    }
}
