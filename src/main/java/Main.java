import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) {
        LatLong london = new LatLong(51.5074, 0.1278);
        HashMap<String, Double> londonRadians = london.coordinateToRadians();

        HashMap<String, ArrayList<Object>> y = NearestAeroplane.callOpenSkyAPI(london);
        ArrayList<Object> z = y.get("states");
        ArrayList<ArrayList<PlaneInformation>> test = NearestAeroplane.filterAPIResponse(z);
        System.out.println(test);
    }
}
