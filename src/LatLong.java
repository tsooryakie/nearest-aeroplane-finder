import java.lang.Math;
import java.util.HashMap;

public class LatLong {

    private final double latitude;
    private final double longitude;

    /**
     *
     * @param latitude
     * @param longitude
     */
    public LatLong(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     *
     * @return
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Double> coordinateToRadians(double latitude, double longitude) {
        HashMap<String, Double> coordinates = new HashMap<String, Double>();
        coordinates.put("latitude", Math.toRadians(latitude));
        coordinates.put("longitude", Math.toRadians(longitude));

        return coordinates;

    }

    /**
     *
     * @return
     */
    public String toString() {
        return "Latitude: " + this.latitude + " " + "Longitude: " + this.longitude;
    }
}
