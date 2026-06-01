package codelab.modul6;

import java.util.*;

public class GraphExercise {

    // ==================================================================================
    // TRANS-JAVA RAILWAY NETWORK
    // ==================================================================================
    // Scenario: We are building a digital map for the railway network across Java.
    // Graphs represent stations (Nodes) and tracks (Edges).

    // FUNDAMENTAL THEORY:
    // 1. VERTEX (Node): A station (e.g., "Surabaya").
    // 2. EDGE (Link): The track connecting two stations.
    // 3. ADJACENCY LIST: A way to store the graph where each node has a list of neighbors.
    // 4. UNDIRECTED GRAPH: Trains can move both ways (A <-> B).
    // 5. DIRECTED GRAPH: Trains move one way (A -> B). Here, we assume tracks are two-way.
    // 6. BFS (Breadth-First Search): Explore neighbor by neighbor (Level by level).
    // 7. DFS (Depth-First Search): Explore as deep as possible before backtracking.

    // A Map to store the Graph: Station Name -> List of Connected Stations
    private Map<String, List<String>> adjVertices;

    public GraphExercise() {
        this.adjVertices = new HashMap<>();
    }

    // ==================================================================================
    // MISSION 1: BUILD THE RAILWAY (Adjacency List)
    // ==================================================================================

    public void addVertex(String label) {
        // Init new entry in the map if it doesn't exist
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    public void addEdge(String label1, String label2) {
        // Connect label1 -> label2
        adjVertices.get(label1).add(label2);

        // Connect label2 -> label1 (Undirected / Two-way track)
        adjVertices.get(label2).add(label1);
    }

    // Print the Graph (Adjacency List View)
    public void printGraph() {
        for (String vertex : adjVertices.keySet()) {
            System.out.print("Station " + vertex + " connects to: ");
            System.out.println(adjVertices.get(vertex));
        }
    }

    // ==================================================================================
    // MISSION 2: ROUTE PLANNING (BFS & DFS)
    // ==================================================================================

    // BFS: Level-order traversal (Queue-based)
    // Checks all immediate neighbors first before moving further.
    public void bfs(String root) {
        System.out.println("\n[BFS Traversal starting from " + root + "]");
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.print(vertex + " -> ");

            for (String neighbor : adjVertices.get(vertex)) {
                // If neighbor has NOT been visited yet
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("END");
    }

    // DFS: Depth-first traversal (Stack/Recursion-based)
    // Goes as deep as possible down one path before backing up.
    public void dfs(String root) {
        System.out.println("\n[DFS Traversal starting from " + root + "]");
        Set<String> visited = new HashSet<>();
        dfsRecursive(root, visited);
        System.out.println("END");
    }

    private void dfsRecursive(String vertex, Set<String> visited) {
        visited.add(vertex);
        System.out.print(vertex + " -> ");

        for (String neighbor : adjVertices.get(vertex)) {
            // If neighbor has NOT been visited yet, go deeper
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        GraphExercise railway = new GraphExercise();

        // 1. Create Stations (Vertices)
        railway.addVertex("Surabaya");
        railway.addVertex("Malang");
        railway.addVertex("Madiun");
        railway.addVertex("Solo");
        railway.addVertex("Jogja");
        railway.addVertex("Bandung");
        railway.addVertex("Jakarta");

        // 2. Lay Tracks (Edges)
        railway.addEdge("Jakarta", "Bandung");
        railway.addEdge("Bandung", "Jogja");
        railway.addEdge("Jogja", "Solo");
        railway.addEdge("Solo", "Madiun");
        railway.addEdge("Madiun", "Surabaya");
        railway.addEdge("Madiun", "Malang");
        railway.addEdge("Malang", "Surabaya");

        // 3. Visualize
        System.out.println(">>> TRANS-JAVA RAILWAY NETWORK <<<");
        railway.printGraph();

        // 4. Test Traversals
        // BFS from Surabaya
        railway.bfs("Surabaya");

        // DFS from Surabaya
        railway.dfs("Surabaya");
    }
}
