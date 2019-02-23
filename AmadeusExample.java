import com.amadeus.Amadeus;
import com.amadeus.Params;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;

public class AmadeusExample {
    public static void main(String[] args) throws ResponseException {
        Amadeus amadeus = Amadeus
                .builder("HMhWxea49LH9nTnOcmGAAUrl24mPBQnI", "8iYhXIoYWZP3OLtr")
                .build();

        Location[] locations = amadeus.referenceData.locations.airports.get(Params
                .with("latitude", 0.1278)
                .and("longitude", 51.5074));

        for (int i = 0; i < locations.length; i++) {
            System.out.println(locations[i].toString());
        }
    }
}