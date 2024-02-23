package flight.reservation.order;

public interface PaymentStrategy {
    boolean pay(double amount) throws IllegalStateException;
}