import java.util.Arrays;

public class AirlineRouteOptimization {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] flightTimes = {
            {0, 3, INF, 7},
            {8, 0, 2, INF},
            {5, INF, 0, 1},
            {2, INF, INF, 0}
        };
        int[][] shortestPaths = floydWarshall(flightTimes);
        String[] airports = {"A", "B", "C", "D"};
        System.out.println("Shortest travel times between airports:");
        printShortestPaths(shortestPaths, airports);
        System.out.println("\nReal-time update: Adding a 4-hour delay to the route from A to B.");
        flightTimes[0][1] += 4;
        int[][] updatedShortestPaths = floydWarshall(flightTimes);
        System.out.println("Updated shortest travel times between airports:");
        printShortestPaths(updatedShortestPaths, airports);
    }
    public static int[][] floydWarshall(int[][] flightTimes) {
        int numAirports = flightTimes.length;
        int[][] shortestTimes = new int[numAirports][numAirports];
        for (int i = 0; i < numAirports; i++) {
            shortestTimes[i] = Arrays.copyOf(flightTimes[i], numAirports);
        }
        for (int k = 0; k < numAirports; k++) {
            for (int i = 0; i < numAirports; i++) {
                for (int j = 0; j < numAirports; j++) {
                    if (shortestTimes[i][k] != INF && shortestTimes[k][j] != INF) {
                        shortestTimes[i][j] = Math.min(shortestTimes[i][j], shortestTimes[i][k] + shortestTimes[k][j]);
                    }
                }
            }
        }

        return shortestTimes;
    }
    public static void printShortestPaths(int[][] shortestPaths, String[] airports) {
        for (int i = 0; i < shortestPaths.length; i++) {
            for (int j = 0; j < shortestPaths[i].length; j++) {
                if (shortestPaths[i][j] == INF) {
                    System.out.println("From " + airports[i] + " to " + airports[j] + ": No direct route");
                } else {
                    System.out.println("From " + airports[i] + " to " + airports[j] + ": " + shortestPaths[i][j] + " hours");
                }
            }
        }
    }
}