import java.lang.Math;
import java.util.*;
import java.lang.reflect.Type;
import kong.unirest.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class NearestAeroplane {

    final static double EARTH_RADIUS = 6371.0;
    final static String OPENSKY_API_URL = "https://opensky-network.org/api/states/all/";

    /**
     * This method parses the JSON response into an easier to deal with HashMap,
     * which can be filtered more easily.
     *
     * @param response - OpenSky API JSON response as a String.
     * @return jsonResponseMap - HashMap of the JSON response.
     */
    public static HashMap<String, ArrayList<Object>> parseJsonAPIResponse(String response) {

        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, ArrayList<Object>> jsonResponseMap = gson.fromJson(response, type);

        return jsonResponseMap;

    }

    /**
     * This method calls the OpenSky API and uses the JSON response from the API call to construct
     * a key-value HashMap of information about the aeroplanes near the input coordinates.
     *
     * @param inputCoordinates - LatLong Input Coordinates to find aeroplanes near.
     * @return HashMap of aeroplane information
     */
    public static HashMap<String, ArrayList<Object>> callOpenSkyAPI(LatLong inputCoordinates) {

        HashMap<String, ArrayList<Object>> responseHashMap = new HashMap<String, ArrayList<Object>>();
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(OPENSKY_API_URL)
                    .queryString("lamin", inputCoordinates.getLatitude())
                    .queryString("lomin", inputCoordinates.getLongitude())
                    .queryString("lamax", inputCoordinates.getLatitude())
                    .queryString("lomax", inputCoordinates.getLongitude())
                    .asJson();

            responseHashMap = parseJsonAPIResponse(jsonResponse.getBody().toString());
            return responseHashMap;

        } catch (UnirestException exception) {
            System.out.println("OpenSky API took too long to respond. Exiting...");
        }

        return responseHashMap;
    }

    /**
     * This method filters the API response to a series of ArrayLists which only contain
     * the information which is relevant and interesting to us:
     * (ICAO24 code, Callsign, Country, Longitude, Latitude, Altitude).
     *
     * @param responseJson - ArrayList of plane information
     * @return filteredResponse - ArrayList of plane information (filtered to contain relevant information only).
     */
    public static ArrayList<ArrayList<Object>> filterAPIResponse(ArrayList<Object> responseJson) {

        ArrayList<ArrayList<Object>> filteredResponse = new ArrayList<ArrayList<Object>>();
        for (int i = 0; i < responseJson.size(); i++) {
            ArrayList<Object> tempAeroplaneInformation = (ArrayList<Object>) responseJson.get(i);
            ArrayList<Object> aeroplaneInformation = new ArrayList<>();
            int[] indicesToKeep = {0, 1, 2, 5, 6, 13};
            for (int j = 0; j < indicesToKeep.length; j++) {
                aeroplaneInformation.add(tempAeroplaneInformation.get(indicesToKeep[j]));
            }
            filteredResponse.add(aeroplaneInformation);

        }

        return filteredResponse;
    }

    /**
     * This method calculates the Great Circle distance between a given set of coordinates,
     * and a plane currently flying closest to the given set of coordinates.
     *
     * @param inputCoordinates - HashMap of Latitude and Longitude coordinates of the input location.
     * @param planeCoordinates - HashMap of Latitude and Longitude coordinates of the plane location.
     * @return greatCircleDistance - Spherical distance of plane from given coordinates.
     */
    public static double calculateGreatCircleDistance(HashMap<String, Double> inputCoordinates,
                                                      HashMap<String, Double> planeCoordinates) {
        double greatCircleDistance = EARTH_RADIUS * Math.acos(
                Math.sin(inputCoordinates.get("latitude")) * Math.sin(planeCoordinates.get("latitude")) +
                        Math.cos(inputCoordinates.get("latitude")) * Math.cos(planeCoordinates.get("latitude")) *
                                Math.cos(inputCoordinates.get("longitude") - planeCoordinates.get("longitude"))
        );

        return greatCircleDistance;
    }

    /**
     * This method uses the calculateGreatCircleDistance() method to return an ArrayList of information
     * about the closest aeroplane.
     * @param filteredAPIResponse - Filtered list of all aeroplanes and their information.
     * @return ArrayList of the information of the closest aeroplane.
     */
    public static ArrayList<Object> findClosest(ArrayList<Object> filteredAPIResponse) {

        ArrayList<Object> closestPlaneInformation = new ArrayList<Object>();

        return closestPlaneInformation;
    }

    /**
     * This method prints the resulting information about the closest aeroplane.
     * @param closestAeroplaneInformation - ArrayList of information of the closest plane from findClosest() method.
     */
    public static void printClosestAeroplaneInformation(ArrayList<Object> closestAeroplaneInformation) {
        System.out.println(closestAeroplaneInformation);
    }

    public static void main(String[] args) {
        LatLong london = new LatLong(51.5074, 0.1278);
        HashMap<String, Double> londonRadians = london.coordinateToRadians();

        HashMap<String, ArrayList<Object>> y = callOpenSkyAPI(london);
        ArrayList<Object> z = y.get("states");
        ArrayList<ArrayList<Object>> test = filterAPIResponse(z);
    }

}