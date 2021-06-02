public class PlaneInformation {

    private String icaoCode, callSign, country;
    private Double longitude, latitude, altitude;

    /**
     * @param icaoCode
     * @param callSign
     * @param country
     * @param longitude
     * @param latitude
     * @param altitude
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
     * @return
     */
    public String getIcaoCode() {
        return this.icaoCode;
    }

    /**
     * @return
     */
    public String getCallSign() {
        return this.callSign;
    }

    /**
     * @return
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * @return
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * @return
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * @return
     */
    public double getAltitude() {
        return this.altitude;
    }

    /**
     * @param newIcaoCode
     */
    public void setIcaoCode(String newIcaoCode) {
        this.icaoCode = newIcaoCode;
    }

    /**
     * @param newCallSign
     */
    public void setCallSign(String newCallSign) {
        this.callSign = newCallSign;
    }

    /**
     * @param newCountry
     */
    public void setCountry(String newCountry) {
        this.country = newCountry;
    }

    /**
     * @param newLatitude
     */
    public void setLatitude(double newLatitude) {
        this.latitude = newLatitude;
    }

    /**
     * @param newLongitude
     */
    public void setLongitude(double newLongitude) {
        this.longitude = newLongitude;
    }

    /**
     * @param newAltitude
     */
    public void setAltitude(double newAltitude) {
        this.altitude = newAltitude;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "Flight: " + getCallSign() + ", ICAO Code: " + getIcaoCode() + ", Country: " + getCountry() +
                ", At Position: " + getLatitude() + ", " + getLongitude() + ", At Altitude: " + getAltitude();
    }
}
