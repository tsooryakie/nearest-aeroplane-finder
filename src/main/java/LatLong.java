import java.lang.Math;
import java.util.HashMap;

public class LatLong {

    private double latitude;
    private double longitude;

    /**
     * Constructor for the LatLong class - creates a LatLong object of two X,Y coordinates.
     *
     * @param latitude  - Latitude in degrees.
     * @param longitude - Longitude in degrees.
     */
    public LatLong(double latitude, double longitude) throws IllegalArgumentException {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitudinal coordinate must be within -90\u00B0 to 90\u00B0");
        }

        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitudinal coordinate must be within -180\u00B0 to 180\u00B0");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter for the Latitude.
     *
     * @return Latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Getter for the Longitude.
     *
     * @return - Longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Setter for the Latitude.
     *
     * @param newLatitude - New Latitude value
     */
    public void setLatitude(double newLatitude) {
        this.latitude = newLatitude;
    }

    /**
     * Setter for the Longitude.
     * @param newLongitude - New Longitude value
     */
    public void setLongitude(double newLongitude) {
        this.longitude = newLongitude;
    }

    /**
     * This function creates a HashMap of coordinates as Radians rather than degrees,
     * to be used for calculating the Great Circle distance.
     * @return coordinates - HashMap of coordinates as Radians.
     */
    public HashMap<String, Double> coordinateToRadians() {
        HashMap<String, Double> coordinates = new HashMap<String, Double>();
        coordinates.put("latitude", Math.toRadians(this.getLatitude()));
        coordinates.put("longitude", Math.toRadians(this.getLongitude()));

        return coordinates;

    }


    /**
     * The toString method for the LatLong class.
     *
     * @return Human readable LatLong string
     */
    public String toString() {
        return "Latitude: " + this.latitude + " " + "Longitude: " + this.longitude;
    }

}
