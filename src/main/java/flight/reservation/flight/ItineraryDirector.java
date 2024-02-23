package flight.reservation.flight;

import flight.reservation.itinerary.FlightItineraryBuilder;

import java.util.Date;
import java.util.List;

public class ItineraryDirector {
    public List<ScheduledFlight> buildOneWayItinerary(Flight flight, Date date) {
        return new FlightItineraryBuilder()
                .addFlight(new ScheduledFlight(flight.getNumber(), flight.getDeparture(), flight.getArrival(), flight.getAircraft(), date))
                .build();
    }

    public List<ScheduledFlight> buildRoundTripItinerary(Flight outboundFlight, Flight returnFlight, Date outboundDate, Date returnDate) {
        return new FlightItineraryBuilder()
                .addFlight(new ScheduledFlight(outboundFlight.getNumber(), outboundFlight.getDeparture(), outboundFlight.getArrival(), outboundFlight.getAircraft(), outboundDate))
                .addFlight(new ScheduledFlight(returnFlight.getNumber(), returnFlight.getDeparture(), returnFlight.getArrival(), returnFlight.getAircraft(), returnDate))
                .build();
    }

    public List<ScheduledFlight> buildMultiCityItinerary(List<Flight> flights, List<Date> dates) {
        if (flights.size() != dates.size()) {
            throw new IllegalArgumentException("Number of flights must match number of dates.");
        }

        FlightItineraryBuilder builder = new FlightItineraryBuilder();
        for (int i = 0; i < flights.size(); i++) {
            builder.addFlight(new ScheduledFlight(flights.get(i).getNumber(), flights.get(i).getDeparture(), flights.get(i).getArrival(), flights.get(i).getAircraft(), dates.get(i)));
        }
        return builder.build();
    }
}
