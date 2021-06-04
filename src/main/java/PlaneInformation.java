public class PlaneInformation {

    private String icaoCode, callSign, country;
    private Double longitude, latitude, altitude;

    /**
     * Constructor for the PlaneInformation class - Creates a PlaneInformation object with relevant information
     * about a given aeroplane.
     *
     * @param icaoCode  - Plane's ICAO Code
     * @param callSign  - Plane's Call Sign
     * @param country   - Plane's Country of Origin
     * @param longitude - Plane's Longitude
     * @param latitude  - Plane's Latitude
     * @param altitude  - Plane's Current Altitude
     */
    public PlaneInformation(String icaoCode, String callSign, String country,
                            Double longitude, Double latitude, Double altitude) {

        if (callSign == null) {
            throw new IllegalArgumentException("API Replied with null Call Sign");
        }
        if (longitude == null) {
            throw new IllegalArgumentException("API Replied with null Longitude");
        }
        if (latitude == null) {
            throw new IllegalArgumentException("API Replied with null Latitude");
        }
        if (altitude == null) {
            throw new IllegalArgumentException("API Replied with null Altitude");
        }

        this.icaoCode = icaoCode;
        this.callSign = callSign;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    /**
     * Getter for the icaoCode variable.
     *
     * @return - ICAO Code.
     */
    public String getIcaoCode() {
        return this.icaoCode;
    }

    /**
     * Getter for the callSign variable.
     *
     * @return - Call Sign
     */
    public String getCallSign() {
        return this.callSign;
    }

    /**
     * Getter for the country variable.
     *
     * @return - Country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Getter for the latitude variable.
     *
     * @return - Latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Getter for the longitude variable.
     *
     * @return - Longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Getter for the altitude variable.
     *
     * @return - Altitude
     */
    public double getAltitude() {
        return this.altitude;
    }

    /**
     * Setter for the icaoCode variable.
     *
     * @param newIcaoCode - New ICAO Code
     */
    public void setIcaoCode(String newIcaoCode) {
        this.icaoCode = newIcaoCode;
    }

    /**
     * Setter for the callSign variable.
     *
     * @param newCallSign - New Call Sign
     */
    public void setCallSign(String newCallSign) {
        this.callSign = newCallSign;
    }

    /**
     * Setter for the country variable.
     *
     * @param newCountry - New Country
     */
    public void setCountry(String newCountry) {
        this.country = newCountry;
    }

    /**
     * Setter for the Latitude variable.
     *
     * @param newLatitude - New Latitude
     */
    public void setLatitude(double newLatitude) {
        this.latitude = newLatitude;
    }

    /**
     * Setter for the Longitude variable.
     *
     * @param newLongitude - New Longitude
     */
    public void setLongitude(double newLongitude) {
        this.longitude = newLongitude;
    }

    /**
     * Setter for the Altitude variable.
     *
     * @param newAltitude - New Altitude
     */
    public void setAltitude(double newAltitude) {
        this.altitude = newAltitude;
    }

    /**
     * PlaneInformation toString method to represent the plane information in a human readable format.
     *
     * @return - PlaneInformation human readable string.
     */
    public String toString() {
        return "Flight: " + getCallSign() + ", ICAO Code: " + getIcaoCode() + ", Country: " + getCountry() +
                ", At Position: " + getLatitude() + ", " + getLongitude() + ", At Altitude: " + getAltitude();
    }
}
