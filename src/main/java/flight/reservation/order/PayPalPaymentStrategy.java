package flight.reservation.order;
import flight.reservation.payment.Paypal;

public class PayPalPaymentStrategy implements PaymentStrategy {
    private final String email;
    private final String password;

    public PayPalPaymentStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) throws IllegalStateException {
        if (email.equals(Paypal.DATA_BASE.get(password))) {
            System.out.println("Paying " + amount + " using PayPal.");
            return true;
        } else {
            return false;
        }
    }
}

