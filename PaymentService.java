// File: PaymentService.java
public class PaymentService {
    // Mock processing
    public boolean processPayment(String ticketId, double amount) {
        System.out.printf("Processing payment for ticket %s : amount=%.2f%n", ticketId, amount);
        return true; // success in this simple model
    }
}
