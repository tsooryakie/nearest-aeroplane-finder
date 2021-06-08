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
    public static ArrayList<ArrayList<PlaneInformation>> filterAPIResponse(ArrayList<Object> responseJson) {

        ArrayList<ArrayList<PlaneInformation>> filteredResponse = new ArrayList<ArrayList<PlaneInformation>>();
        for (int i = 0; i < responseJson.size(); i++) {
            ArrayList<Object> tempAeroplaneInformation = (ArrayList<Object>) responseJson.get(i);
            ArrayList<PlaneInformation> aeroplaneInformationList = new ArrayList<>();
            try {
                PlaneInformation planeInformation = new PlaneInformation(
                        (String) tempAeroplaneInformation.get(0),
                        (String) tempAeroplaneInformation.get(1),
                        (String) tempAeroplaneInformation.get(2),
                        (double) tempAeroplaneInformation.get(5),
                        (double) tempAeroplaneInformation.get(6),
                        (double) tempAeroplaneInformation.get(13)
                );
                aeroplaneInformationList.add(planeInformation);
                filteredResponse.add(aeroplaneInformationList);
            } catch (IllegalArgumentException | NullPointerException exception) {
                continue;
            }

        }

        return filteredResponse;
    }

    /**
     * This method calculates the Great Circle distance between a given set of coordinates,
     * and a plane currently flying closest to the given set of coordinates.
     *
     * @param inputCoordinates - HashMap of Latitude and Longitude coordinates of the input location (in Radians).
     * @param planeCoordinates - HashMap of Latitude and Longitude coordinates of the plane location (in Radians).
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
     *
     * @param filteredAPIResponse - Filtered list of all aeroplanes and their information.
     * @param inputLatLong        - User input LatLong Coordinates, from which to calculate distance to nearest plane.
     * @return ArrayList of the information of the closest aeroplane.
     */
    public static ArrayList<PlaneInformation> findClosest(ArrayList<ArrayList<PlaneInformation>> filteredAPIResponse,
                                                          LatLong inputLatLong) {

        LatLong initialPlaneLatLong = new LatLong(filteredAPIResponse.get(0).get(0).getLatitude(),
                filteredAPIResponse.get(0).get(0).getLongitude());

        int minDistanceIndex = 0;
        double minDistance = calculateGreatCircleDistance(inputLatLong.coordinateToRadians(),
                initialPlaneLatLong.coordinateToRadians());
        for (int i = 0; i < filteredAPIResponse.size(); i++) {
            LatLong planeLatLong = new LatLong(filteredAPIResponse.get(i).get(0).getLatitude(),
                    filteredAPIResponse.get(i).get(0).getLongitude());

            double distance = calculateGreatCircleDistance(inputLatLong.coordinateToRadians(),
                    planeLatLong.coordinateToRadians());

            if (distance <= minDistance) {
                minDistance = distance;
                minDistanceIndex = i;
            }

        }

        return filteredAPIResponse.get(minDistanceIndex);
    }

}