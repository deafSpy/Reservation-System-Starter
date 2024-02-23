package flight.reservation.itinerary;

import flight.reservation.flight.Flight;
import flight.reservation.flight.ScheduledFlight;

import java.util.ArrayList;
import java.util.List;

public class FlightItineraryBuilder {
    private List<ScheduledFlight> flights;

    public FlightItineraryBuilder() {
        this.flights = new ArrayList<>();
    }

    public FlightItineraryBuilder addFlight(ScheduledFlight flight) {
        this.flights.add(flight);
        return this;
    }

    public FlightItineraryBuilder addLayover(ScheduledFlight flight) {
        // Logic to add layovers
        return this;
    }

    public FlightItineraryBuilder addAccommodation(String accommodation) {
        // Logic to add accommodation
        return this;
    }

    public FlightItineraryBuilder addSpecialRequests(String request) {
        // Logic to add special requests
        return this;
    }

    public List<ScheduledFlight> build() {
        return this.flights;
    }
}
