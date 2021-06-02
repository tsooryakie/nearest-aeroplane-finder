public class PlaneInformation {

    private String icaoCode, callSign, country;
    private double latitude, longitude, altitude;

    /**
     * @param icaoCode
     * @param callSign
     * @param country
     * @param latitude
     * @param longitude
     * @param altitude
     */
    public PlaneInformation(String icaoCode, String callSign, String country,
                            double latitude, double longitude, double altitude) {

        this.icaoCode = icaoCode;
        this.callSign = callSign;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
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
