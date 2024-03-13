package flight.reservation.validator;

import flight.reservation.flight.ScheduledFlight;

import java.util.List;

public class CapacityValidator implements OrderValidator {
    private OrderValidator nextValidator;
    
    @Override
    public boolean validateOrder(List<String> passengerNames, List<ScheduledFlight> flights) {
        // Customer customer = Customer.getInstance();
        boolean valid = flights.stream().allMatch(scheduledFlight -> {
            try {
                return scheduledFlight.getAvailableCapacity() >= passengerNames.size();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
        });
        if (!valid) {
            System.out.println("Insufficient capacity for the specified flights.");
        }
        return valid && (nextValidator == null || nextValidator.validateOrder(passengerNames, flights));
    }

    @Override
    public void setNextValidator(OrderValidator nextValidator) {
        this.nextValidator = nextValidator;
    }
}

