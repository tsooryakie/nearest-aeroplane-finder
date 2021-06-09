import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LatLongTest {

    private final LatLong londonLatLong = new LatLong(51.5074, 0.1278);

    @Test
    void latLongConstructorTest0() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LatLong(-50000,
                -5000));
    }

    @Test
    void latLongConstructorTest1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LatLong(51.5074,
                -5000));
    }

    @Test
    void latLongConstructorTest2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LatLong(-50000,
                0.1278));
    }

    @Test
    void getLatitudeTest() {
        assertEquals(51.5074, londonLatLong.getLatitude());
    }


    @Test
    void getLongitudeTest() {
        assertEquals(0.1278, londonLatLong.getLongitude());
    }

    @Test
    void setLatitudeTest() {
        LatLong testLatLong = new LatLong(50, -20);
        testLatLong.setLatitude(49.5);
        assertEquals(49.5, testLatLong.getLatitude());
    }

    @Test
    void setLongitudeTest() {
        LatLong testLatLong = new LatLong(50, -20);
        testLatLong.setLongitude(1);
        assertEquals(1, testLatLong.getLongitude());
    }

    @Test
    void latLongToStringTest() {
        assertEquals("Latitude: 51.5074 Longitude: 0.1278", londonLatLong.toString());
    }


}