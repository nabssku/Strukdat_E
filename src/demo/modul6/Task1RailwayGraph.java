package demo.modul6;

import java.util.*;

class Station {
    private String code;
    private String name;
    private String city;

    public Station(String code, String name, String city) {
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

    public void displayInfo() {
        System.out.println(code + " - " + name + " (" + city + ")");
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + city + ")";
    }
}

class Route {
    private String destination;
    private int distance;
    private int travelTime;
    private double fare;

    public Route(String destination, int distance, int travelTime, double fare) {
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

    @Override
    public String toString() {
        return "to " + destination +
                " | Distance: " + distance + " km" +
                " | Time: " + travelTime + " minutes" +
                " | Fare: Rp" + fare;
    }
}

class RailwayGraph {
    private HashMap<String, Station> stations;
    private HashMap<String, ArrayList<Route>> adjacencyList;

    public RailwayGraph() {
        stations = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addStation(Station station) {
        stations.putIfAbsent(station.getCode(), station);
        adjacencyList.putIfAbsent(station.getCode(), new ArrayList<>());
    }

    public void addRoute(String source, String destination, int distance, int travelTime, double fare) {
        if (!stations.containsKey(source) || !stations.containsKey(destination)) {
            System.out.println("Station code not found!");
            return;
        }

        // Undirected graph: source -> destination
        adjacencyList.get(source).add(new Route(destination, distance, travelTime, fare));

        // Undirected graph: destination -> source
        adjacencyList.get(destination).add(new Route(source, distance, travelTime, fare));
    }

    public void getConnections(String stationCode) {
        if (!adjacencyList.containsKey(stationCode)) {
            System.out.println("Station not found!");
            return;
        }

        System.out.println("\nConnections from " + stationCode + " - " + stations.get(stationCode).getName() + ":");

        ArrayList<Route> routes = adjacencyList.get(stationCode);

        if (routes.isEmpty()) {
            System.out.println("No connections available.");
            return;
        }

        for (Route route : routes) {
            Station destinationStation = stations.get(route.getDestination());
            System.out.println("- " + destinationStation.getName() + " (" + route + ")");
        }
    }

    public void removeRoute(String source, String destination) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            System.out.println("Station not found!");
            return;
        }

        adjacencyList.get(source).removeIf(route -> route.getDestination().equals(destination));
        adjacencyList.get(destination).removeIf(route -> route.getDestination().equals(source));

        System.out.println("\nRoute between " + source + " and " + destination + " removed.");
    }

    public void removeStation(String stationCode) {
        if (!stations.containsKey(stationCode)) {
            System.out.println("Station not found!");
            return;
        }

        for (String code : adjacencyList.keySet()) {
            adjacencyList.get(code).removeIf(route -> route.getDestination().equals(stationCode));
        }

        adjacencyList.remove(stationCode);
        stations.remove(stationCode);

        System.out.println("\nStation " + stationCode + " removed.");
    }

    public void displayStations() {
        System.out.println("\n=== Station List ===");
        for (Station station : stations.values()) {
            station.displayInfo();
        }
    }

    public void displayGraph() {
        System.out.println("\n=== Railway Graph Adjacency List ===");

        for (String code : adjacencyList.keySet()) {
            System.out.print(code + " connects to: ");

            ArrayList<Route> routes = adjacencyList.get(code);

            for (Route route : routes) {
                System.out.print(route.getDestination() + " ");
            }

            System.out.println();
        }
    }
}

public class Task1RailwayGraph {
    public static void main(String[] args) {
        RailwayGraph railway = new RailwayGraph();

        railway.addStation(new Station("JKT", "Gambir Station", "Jakarta"));
        railway.addStation(new Station("BDG", "Bandung Station", "Bandung"));
        railway.addStation(new Station("JOG", "Tugu Station", "Yogyakarta"));
        railway.addStation(new Station("SLO", "Solo Balapan Station", "Solo"));
        railway.addStation(new Station("MDN", "Madiun Station", "Madiun"));
        railway.addStation(new Station("SBY", "Surabaya Gubeng Station", "Surabaya"));
        railway.addStation(new Station("MLG", "Malang Station", "Malang"));

        railway.addRoute("JKT", "BDG", 150, 180, 150000);
        railway.addRoute("BDG", "JOG", 390, 420, 250000);
        railway.addRoute("JOG", "SLO", 65, 70, 50000);
        railway.addRoute("SLO", "MDN", 100, 100, 70000);
        railway.addRoute("MDN", "SBY", 160, 150, 120000);
        railway.addRoute("MDN", "MLG", 170, 180, 130000);
        railway.addRoute("MLG", "SBY", 95, 120, 90000);

        railway.displayStations();
        railway.displayGraph();
        railway.getConnections("SBY");

        // Contoh remove, boleh aktifkan kalau mau demo hapus data
        // railway.removeRoute("MLG", "SBY");
        // railway.displayGraph();

        // railway.removeStation("MLG");
        // railway.displayGraph();
    }
}
