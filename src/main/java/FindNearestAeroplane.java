import java.lang.Math;
import java.util.HashMap;
import kong.unirest.*;

public class FindNearestAeroplane {

    final static double EARTH_RADIUS = 6371.0;

    /**
     * This method calculates the Great Circle distance between a given set of coordinates,
     * and a plane currently flying closest to the given set of coordinates.
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
        LatLong cardiff = new LatLong(51.4816, 3.1791);

        HashMap<String, Double> londonRadians = london.coordinateToRadians();
        HashMap<String, Double> cardiffRadians = cardiff.coordinateToRadians();
        System.out.println(londonRadians);
        System.out.println(cardiffRadians);

        double x = calculateGreatCircleDistance(londonRadians, cardiffRadians);
        System.out.println(x);
    }

}
