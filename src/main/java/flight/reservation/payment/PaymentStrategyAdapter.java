package flight.reservation.payment;

public class PaymentStrategyAdapter implements PaymentStrategy {
    private final Object paymentStrategy;

    // Constructor for CreditCardPaymentStrategy
    public PaymentStrategyAdapter(CreditCard creditCard) {
        this.paymentStrategy = new CreditCardPaymentStrategy(creditCard);
    }

    // Constructor for PayPalPaymentStrategy
    public PaymentStrategyAdapter(String email, String password) {
        this.paymentStrategy = new PayPalPaymentStrategy(email, password);
    }

    @Override
    public boolean pay(double amount) throws IllegalStateException {
        // Delegate the payment processing to the adapted payment strategy
        try {
            return ((PaymentStrategy) paymentStrategy).pay(amount);
        } catch (IllegalStateException e) {
            System.err.println("Error occurred during payment: " + e.getMessage());
            return false;
        }
    }
}

