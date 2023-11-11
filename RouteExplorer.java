import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class RouteExplorer {
    private HashMap<String, CityRouteList> cityList;

    // city is basically an adjacencyList

    public RouteExplorer() {
        this.cityList = new HashMap<>();
    }

    // Basically adding an Edge to the Route or Graph
    public void addCityRoute(String startCity, String endCity, int cost, int time) {
        // It adds the start city if not already in the graph
        if (!cityList.containsKey(startCity)) {
            cityList.put(startCity, new CityRouteList());
        }
        // It adds the end city as a vertex in the start city's list
        cityList.get(startCity).addVertex(endCity, cost, time);
    }

    // This method if for finding all possible paths between two cities

    public ArrayList<ArrayList<String>> exploreRoutes(String startCity, String endCity) {
        ArrayList<ArrayList<String>> allRoutes = new ArrayList<>();
        DfsStack stack = new DfsStack();
        ArrayList<String> visited = new ArrayList<>();

        // recursive function to perform the backtracking
        recursiveSearch(startCity, endCity, stack, visited, allRoutes);

        return allRoutes;
    }

    private void recursiveSearch(String present, String endCity, DfsStack stack, ArrayList<String> visited, ArrayList<ArrayList<String>> allRoutes) {
        stack.pushToStack(new Vertex(present, 0, 0));  // Push current city to stack
        visited.add(present);  // Its marks the present city as visited

        if (present.equals(endCity)) {
            // Finds a path and then adds it to allRoutes
            ArrayList<String> route = new ArrayList<>();
            Vertex value = stack.peekStackTop();
            while (value != null) {
                route.add(0, value.getCity());
                value = value.getNextVertex();
            }
            allRoutes.add(route);
        } else {
            CityRouteList neighbors = cityList.get(present);
            Vertex vertex = neighbors.firstVertex;
            while (vertex != null) {
                if (!visited.contains(vertex.getCity())) {
                    recursiveSearch(vertex.getCity(), endCity, stack, visited, allRoutes);
                }
                vertex = vertex.getNextVertex();
            }
        }

        // Backtrack
        visited.remove(present);  // Remove present city from visited
        stack.popOffStack();  // Pop present city from stack
    }

    public void flightRequests(ArrayList<String[]> requests, String outputFileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            int flightNumber = 1;  // Initialize flight number

            for (String[] request : requests) {
                String startCity = request[0];
                String endCity = request[1];
                String sortBy = request[2];

                ArrayList<ArrayList<String>> allRoutes = exploreRoutes(startCity, endCity);

                // This sorts routes based on the Time or Cost
                Comparator<ArrayList<String>> comparator;
                if ("T".equals(sortBy)) {
                    comparator = Comparator.comparingInt(this::timeTotal);
                } else {
                    comparator = Comparator.comparingInt(this::costTotal);
                }
                Collections.sort(allRoutes, comparator);

                // This generates the output to the OutputFile
                writer.println("Flight " + flightNumber + ": " + startCity + ", " + endCity + " (" + (sortBy.equals("T") ? "Time" : "Cost") + ")");
                for (int i = 0; i < Math.min(3, allRoutes.size()); i++) {
                    ArrayList<String> route = allRoutes.get(i);
                    int totalTime = timeTotal(route);
                    int totalCost = costTotal(route);
                    writer.println("Path " + (i + 1) + ": " + String.join(" -> ", route) + ". Time: " + totalTime + " Cost: " + totalCost);
                }
                writer.println();

                flightNumber++;  // Increment flight number for the next flight
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int timeTotal(ArrayList<String> route) {
        //  This calculates the total time for a given route
        int totalTime = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            String startCity = route.get(i);
            String endCity = route.get(i + 1);
            Vertex vertex = cityList.get(startCity).findVertex(endCity);
            if (vertex != null) {
                totalTime += vertex.getTime();
            }
        }
        return totalTime;
    }

    private int costTotal(ArrayList<String> route) {
        //This calculates the total cost for a given route

        int totalCost = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            String startCity = route.get(i);
            String endCity = route.get(i + 1);
            Vertex vertex = cityList.get(startCity).findVertex(endCity);
            if (vertex != null) {
                totalCost += vertex.getCost();
            }
        }

        return totalCost;
    }
}
