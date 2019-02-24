import com.amadeus.Amadeus;
import com.amadeus.Params;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightDestination;
import com.amadeus.resources.FlightOffer;
import com.amadeus.resources.Location;

import java.util.ArrayList;

public class AmadeusExample {
    public static void main(String[] args) throws ResponseException {
        if (window.XMLHttpRequest) {
            httpRequest = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }

        httpRequest.onreadystatechange = function(){
            // Process the server response here.
        }

        httpRequest.open('GET', 'http://www.example.org/some.file', true);
        httpRequest.send();

        cityFlightInfo;

        Amadeus amadeus = Amadeus
                .builder("HMhWxea49LH9nTnOcmGAAUrl24mPBQnI", "8iYhXIoYWZP3OLtr")
                .build();
        Location[] locations = amadeus.referenceData.locations.get(Params
                .with("keyword", "US")
                .and("subType", Locations.ANY));
        String choice = (this is what jackson is gonna send me);
        String date = (this is another thing jackson is gonna send me);

        if (choice.equals("Chicago")){
            for (int i = 0; i < locations.length; i++) {
                if (locations[i].getIataCode().equals("CHI")){
                    Flight[] flights = cityFlightInfo(locations[i], date);
                }
            }
        } else if (choice.equals("New York City")){
            for (int i = 0; i < locations.length; i++) {
                if (locations[i].getIataCode().equals("NYC")){
                    Flight[] flights = cityFlightInfo(locations[i], date);
                }
            }
        } else if (choice.equals("Los Angeles")){
            for (int i = 0; i < locations.length; i++) {
                if (locations[i].getIataCode().equals("LAX")){
                    Flight[] flights = cityFlightInfo(locations[i], date);
                }
            }
        } else if (choice.equals("Jackson")) {
            for (int i = 0; i < locations.length; i++) {
                if (locations[i].getIataCode().equals("ATL")){
                    Flight[] flights = cityFlightInfo(locations[i], date);
                }
            }
        } else {
            for (int i = 0; i < locations.length; i++) {
                if (locations[i].getIataCode().equals("SEA")){
                    Flight[] flights = cityFlightInfo(locations[i], date);
                }
            }
        }
        and now communicate back to jackson with the array of information
    }

    public static Flight[] cityFlightInfo(Location selected, String date) throws ResponseException{

        Amadeus amadeus = Amadeus
                .builder("HMhWxea49LH9nTnOcmGAAUrl24mPBQnI", "8iYhXIoYWZP3OLtr")
                .build();

        ArrayList<Location> matches = new ArrayList<Location>();
        //ArrayList<Locations> origins = new ArrayList<Locations>();
        ArrayList<FlightOffer> directs = new ArrayList<FlightOffer>();

        //here, we create a list of some, if not all of the possible locations in the united states

        Location[] locations = amadeus.referenceData.locations.get(Params
                .with("keyword", "US")
                .and("subType", Locations.ANY));

        //direct flights only
        //we are only going to talk about domestic flights locations
        //here, we make an array of all tickets being sold that fly directly to our location

        for (int i = 0; i < locations.length; i++) {
            if (!locations[i].getIataCode().equals("NYC")) {
                FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get(Params
                        .with("origin", locations[i].getIataCode())
                        .and("destination", selected.getIataCode())
                        .and("departureDate", date));
                for (FlightOffer check : flightOffers){
                    if (check.toString().length() < 1000){
                        directs.add(check);
                    }
                }
            }
        }
        //now we exchange our array of flight offers into an array of flights, to better print it in html
        FlightOffer[] switchOver = new FlightOffer[directs.size()];
        for (int i = 0; i < directs.size(); i++) {
            switchOver[i] = directs.get(i);
        }
        Flight[] flights = getFlights(switchOver);

        //because there can be multiple airports near or in a given city, we find all destinations corresponding to the city name

        for (int i = 0; i < locations.length; i++) {
            if (locations[i].getAddress().getCityName().equals(selected.getAddress().getCityName())){
                matches.add(locations[i]);
            }
        }

        //next, we need to find all flights to the given city that have not been sold yet
        //it will include availability, seat type, pricing, etc and will be displayed when a plane is clicked on

        return flights;
    }
    public static Flight[] getFlights(FlightOffer[] input){
        Flight[] returnArray = new Flight[input.length];
        String home;
        String destination;
        String departureTime;
        String arrivalTime;
        String adultPrice;
        String availability;
        String flightID;

        for (int i = 0; i < input.length; i++) {
            String flightOffer = input[i].toString();
            int indexOf = 0;
            indexOf = flightOffer.indexOf("iataCode=");
            home = flightOffer.substring(indexOf + 9, indexOf + indexOf + 12);
            indexOf = flightOffer.indexOf("at=", indexOf);
            departureTime = flightOffer.substring(indexOf + 3, flightOffer.indexOf("arrival=") - 3);
            indexOf = flightOffer.indexOf("iataCode=", indexOf);
            destination = flightOffer.substring(indexOf + 9, indexOf + 12);
            indexOf = flightOffer.indexOf("at=", indexOf);
            arrivalTime = flightOffer.substring(indexOf + 3, flightOffer.indexOf("carrierCode=") - 3);
            indexOf = flightOffer.indexOf("availability=");
            availability = flightOffer.substring(indexOf + 13, flightOffer.indexOf("fareBasis=") - 2);
            indexOf = flightOffer.indexOf("FlightOffer.Price", indexOf);
            adultPrice = flightOffer.substring(indexOf + 24, indexOf + 29);
            indexOf = flightOffer.indexOf("id=", 0);
            flightID = flightOffer.substring(indexOf + 3, flightOffer.indexOf("offerItems=", 0) - 2);
            Flight addition = new Flight(home, destination, departureTime, arrivalTime, adultPrice, availability, flightID);
            returnArray[i] = addition;
        }
        return returnArray;
    }
}