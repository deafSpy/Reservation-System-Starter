package flight.reservation;

import flight.reservation.flight.ScheduledFlight;
import flight.reservation.flight.FlightObserver;
import flight.reservation.order.FlightOrder;
import flight.reservation.order.Order;
import flight.reservation.validator.CapacityValidator;
import flight.reservation.validator.NoFlyListValidator;
import flight.reservation.validator.OrderValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

public class Customer implements FlightObserver {
    
    private String email;
    private String name;
    private List<Order> orders;
    private static Customer instance;
    
    private OrderValidator orderValidatorChain;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public FlightOrder createOrder(List<String> passengerNames, List<ScheduledFlight> flights, double price) {
        if (orderValidatorChain == null) {
            throw new IllegalStateException("Order validation chain is not initialized.");
        }

        if (!orderValidatorChain.validateOrder(passengerNames, flights)) {
            throw new IllegalStateException("Order is not valid");
        }

        // Proceed with order creation
        FlightOrder order = new FlightOrder(flights);
        order.setCustomer(this);
        order.setPrice(price);
        List<Passenger> passengers = passengerNames
                .stream()
                .map(Passenger::new)
                .collect(Collectors.toList());
        order.setPassengers(passengers);
        order.getScheduledFlights().forEach(scheduledFlight -> scheduledFlight.addPassengers(passengers));
        orders.add(order);
        return order;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void updateDepartureTime(Date newDepartureTime) {
        System.out.println("Dear " + name + ", the departure time of one of your flights has been updated to: "
                + newDepartureTime);
    }
    
    public void registerForFlightUpdates(ScheduledFlight flight) {
        flight.addObserver(this);
    }

    public void unregisterForFlightUpdates(ScheduledFlight flight) {
        flight.removeObserver(this);
    }

    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer("Anonymous", "anonymous@example.com");
        }
        return instance;
    }

    public void setOrderValidatorChain(OrderValidator orderValidatorChain) {
        this.orderValidatorChain = orderValidatorChain;
    }


}

