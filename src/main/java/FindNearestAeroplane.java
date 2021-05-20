import java.lang.Math;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.Map;
import kong.unirest.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class FindNearestAeroplane {

    final static double EARTH_RADIUS = 6371.0;
    final static String OPENSKY_API_URL = "https://opensky-network.org/api/states/all/";

    public static HashMap<String, Object> parseJsonAPIResponse(String response) {

        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, Object> jsonResponseMap = gson.fromJson(response, type);

        return jsonResponseMap;

    }

    /**
     * This method calls the OpenSky API and uses the JSON response from the API call to construct
     * a key-value HashMap of information about the aeroplanes near the input coordinates.
     *
     * @param inputCoordinates - LatLong Input Coordinates to find aeroplanes near.
     * @return HashMap of aeroplane information
     */
    public static HashMap<String, Object> callOpenSkyAPI(LatLong inputCoordinates) {

        HashMap<String, Object> responseHashMap = new HashMap<String, Object>();
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

    public static void main(String[] args) {
        LatLong london = new LatLong(51.5074, 0.1278);
        HashMap<String, Double> londonRadians = london.coordinateToRadians();

        HashMap<String, Object> y = callOpenSkyAPI(london);
        for(Map.Entry<String, Object> entry: y.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("Key: " + key);
            System.out.println("\n");
        }

    }

}
