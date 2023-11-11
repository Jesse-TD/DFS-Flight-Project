// FileLoader.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    public static RouteExplorer readFlightDataFile(String fileName) throws IOException {
        RouteExplorer routeExplorer = new RouteExplorer();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int n = Integer.parseInt(br.readLine());  // Number of flight paths
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                String[] parts = line.split("\\|");
                String startCity = parts[0];
                String endCity = parts[1];
                int cost = Integer.parseInt(parts[2]);
                int time = Integer.parseInt(parts[3]);
                routeExplorer.addCityRoute(startCity, endCity, cost, time);
                routeExplorer.addCityRoute(endCity, startCity, cost, time);  // Since it's an undirected graph
            }
        }
        return routeExplorer;
    }



    public static ArrayList<String[]> readPathToCalculateFile(String fileName) throws IOException {
        ArrayList<String[]> requests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int n = Integer.parseInt(br.readLine());  // Number of requested plans
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                String[] parts = line.split("\\|");
                requests.add(parts);
            }
        }
        return requests;
    }
}
