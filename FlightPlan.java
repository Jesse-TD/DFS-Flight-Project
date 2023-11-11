import java.io.IOException;
import java.util.ArrayList;

public class FlightPlan {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java FlightPlan <FlightDataFile> <PathsToCalculateFile> <OutputFile>");
            return;
        }

        String flightDataFile = args[0];
        String pathsToCalculateFile = args[1];
        String outputFile = args[2];

        try {
            RouteExplorer explorer = FileLoader.readFlightDataFile(flightDataFile);
            ArrayList<String[]> requestedPlans = FileLoader.readPathToCalculateFile(pathsToCalculateFile);

            explorer.flightRequests(requestedPlans, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

