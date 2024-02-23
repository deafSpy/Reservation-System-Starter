package flight.reservation.flight;

import java.util.Date;

public interface FlightObserver {
    void updateDepartureTime(Date newDepartureTime);
}
