package demo.modul6;

import java.util.*;

class StationTask2 {
    private String code;
    private String name;
    private String city;

    public StationTask2(String code, String name, String city) {
        this.code = code;
        this.name = name;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}

class RouteTask2 {
    private String destination;
    private int distance;
    private int travelTime;
    private double fare;

    public RouteTask2(String destination, int distance, int travelTime, double fare) {
        this.destination = destination;
        this.distance = distance;
        this.travelTime = travelTime;
        this.fare = fare;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public double getFare() {
        return fare;
    }
}

class RailwayTraversalGraph {
    private HashMap<String, StationTask2> stations;
    private HashMap<String, ArrayList<RouteTask2>> adjacencyList;

    public RailwayTraversalGraph() {
        stations = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addStation(StationTask2 station) {
        stations.putIfAbsent(station.getCode(), station);
        adjacencyList.putIfAbsent(station.getCode(), new ArrayList<>());
    }

    public void addRoute(String source, String destination, int distance, int travelTime, double fare) {
        if (!stations.containsKey(source) || !stations.containsKey(destination)) {
            System.out.println("Station code not found!");
            return;
        }

        adjacencyList.get(source).add(new RouteTask2(destination, distance, travelTime, fare));
        adjacencyList.get(destination).add(new RouteTask2(source, distance, travelTime, fare));
    }

    public void displayGraph() {
        System.out.println("\n=== Railway Graph Adjacency List ===");

        for (String code : adjacencyList.keySet()) {
            System.out.print(code + " connects to: ");

            for (RouteTask2 route : adjacencyList.get(code)) {
                System.out.print(route.getDestination() + " ");
            }

            System.out.println();
        }
    }

    public void bfsTraversal(String startCode) {
        if (!stations.containsKey(startCode)) {
            System.out.println("Start station not found!");
            return;
        }

        System.out.println("\n=== BFS Traversal from " + startCode + " ===");

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startCode);
        queue.add(startCode);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " -> ");

            for (RouteTask2 route : adjacencyList.get(current)) {
                String neighbor = route.getDestination();

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("END");
    }

    public void dfsTraversal(String startCode) {
        if (!stations.containsKey(startCode)) {
            System.out.println("Start station not found!");
            return;
        }

        System.out.println("\n=== DFS Traversal from " + startCode + " ===");

        Set<String> visited = new HashSet<>();
        dfsRecursive(startCode, visited);

        System.out.println("END");
    }

    private void dfsRecursive(String current, Set<String> visited) {
        visited.add(current);
        System.out.print(current + " -> ");

        for (RouteTask2 route : adjacencyList.get(current)) {
            String neighbor = route.getDestination();

            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public void findPathBFS(String start, String end) {
        if (!stations.containsKey(start) || !stations.containsKey(end)) {
            System.out.println("Start or destination station not found!");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        HashMap<String, String> previous = new HashMap<>();

        queue.add(start);
        visited.add(start);
        previous.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            for (RouteTask2 route : adjacencyList.get(current)) {
                String neighbor = route.getDestination();

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!previous.containsKey(end)) {
            System.out.println("\nNo path found from " + start + " to " + end);
            return;
        }

        ArrayList<String> path = new ArrayList<>();
        String current = end;

        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }

        Collections.reverse(path);

        System.out.println("\n=== Shortest Path by Stops using BFS ===");
        System.out.println("From " + start + " to " + end + ":");
        System.out.println(String.join(" -> ", path));
        System.out.println("Total stops/edges: " + (path.size() - 1));
    }
}

public class Task2GraphTraversal {
    public static void main(String[] args) {
        RailwayTraversalGraph railway = new RailwayTraversalGraph();

        railway.addStation(new StationTask2("JKT", "Gambir Station", "Jakarta"));
        railway.addStation(new StationTask2("BDG", "Bandung Station", "Bandung"));
        railway.addStation(new StationTask2("JOG", "Tugu Station", "Yogyakarta"));
        railway.addStation(new StationTask2("SLO", "Solo Balapan Station", "Solo"));
        railway.addStation(new StationTask2("MDN", "Madiun Station", "Madiun"));
        railway.addStation(new StationTask2("SBY", "Surabaya Gubeng Station", "Surabaya"));
        railway.addStation(new StationTask2("MLG", "Malang Station", "Malang"));

        railway.addRoute("JKT", "BDG", 150, 180, 150000);
        railway.addRoute("BDG", "JOG", 390, 420, 250000);
        railway.addRoute("JOG", "SLO", 65, 70, 50000);
        railway.addRoute("SLO", "MDN", 100, 100, 70000);
        railway.addRoute("MDN", "SBY", 160, 150, 120000);
        railway.addRoute("MDN", "MLG", 170, 180, 130000);
        railway.addRoute("MLG", "SBY", 95, 120, 90000);

        railway.displayGraph();

        railway.bfsTraversal("SBY");
        railway.dfsTraversal("SBY");
        railway.findPathBFS("JOG", "SBY");
    }
}
