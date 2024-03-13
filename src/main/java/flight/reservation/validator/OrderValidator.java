package flight.reservation.validator;

import flight.reservation.flight.ScheduledFlight;

import java.util.List;

public interface OrderValidator {
    boolean validateOrder(List<String> passengerNames, List<ScheduledFlight> flights);
    void setNextValidator(OrderValidator nextValidator);
}

