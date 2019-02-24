import java.util.StringTokenizer;

public class Flight {
    String home;
    String destination;
    String departureTime;
    String arrivalTime;
    String adultPrice;
    String availability;
    String flightID;


    public Flight(String home, String destination, String departureTime, String arrivalTime, String adultPrice, String availability, String flightID){
        this.home = home;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.adultPrice = adultPrice;
        this.availability = availability;
        this.flightID = flightID;
    }

    public String getHome(){
        return this.home;
    }
    public String getDestination(){
        return this.destination;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public String getarrivalTime() {
        return this.arrivalTime;
    }

    public String getAdultPrice() {
        return this.adultPrice;
    }

    public String getAvailability(){
        return this.availability;
    }
    public String getFlightID(){
        return this.flightID;
    }
}
