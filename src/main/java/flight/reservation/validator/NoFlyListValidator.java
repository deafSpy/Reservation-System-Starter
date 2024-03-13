package flight.reservation.validator;

import flight.reservation.Customer;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.order.FlightOrder;

import java.util.List;

public class NoFlyListValidator implements OrderValidator {
    private OrderValidator nextValidator;

    @Override
    public boolean validateOrder(List<String> passengerNames, List<ScheduledFlight> flights) {
        Customer customer = Customer.getInstance();
        boolean valid = !FlightOrder.getNoFlyList().contains(customer.getName());
        if (!valid) {
            System.out.println("Customer " + customer.getName() + " is on the no-fly list.");
        }
        return valid && (nextValidator == null || nextValidator.validateOrder(passengerNames, flights));
    }

    @Override
    public void setNextValidator(OrderValidator nextValidator) {
        this.nextValidator = nextValidator;
    }
}


