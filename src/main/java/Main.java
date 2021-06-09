import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        int countTries = 0;
        int maxTries = 3;
        while (countTries < maxTries) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter a Latitude:");
                double userLatitude = scanner.nextDouble();

                System.out.println("Please enter a Longitude:");
                double userLongitude = scanner.nextDouble();
                System.out.println("Calling OpenSky API...");

                LatLong userLatLong = new LatLong(userLatitude, userLongitude);
                HashMap<String, ArrayList<Object>> apiResponse = NearestAeroplane.callOpenSkyAPI(userLatLong);
                ArrayList<Object> apiResponseStates = apiResponse.get("states");
                ArrayList<ArrayList<PlaneInformation>> allPlaneInformation = NearestAeroplane.filterAPIResponse(
                        apiResponseStates
                );
                ArrayList<PlaneInformation> nearestAeroplane = NearestAeroplane.findClosest(
                        allPlaneInformation,
                        userLatLong
                );
                System.out.println("Nearest Aeroplane: \n" + nearestAeroplane.get(0).toString());
                break;
            } catch (IllegalArgumentException | InputMismatchException exception) {
                countTries++;
                System.out.println("Please only enter the Lat-Long coordinates as " +
                        "decimal numbers in an acceptable range.");
                if (countTries == maxTries) {
                    throw new Exception("Please only enter the Lat-Long coordinates as " +
                            "decimal numbers in an acceptable range.");
                }
            }
        }
    }
}

