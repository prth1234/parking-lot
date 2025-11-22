
import java.util.List;

class Main{
    public static void main(String[] args) {
        List<ParkingSpot> level1Spots = List.of(
            new ParkingSpot("L1-S1", VehicleSize.COMPACT),
            new ParkingSpot("L1-S2", VehicleSize.LARGE),
            new ParkingSpot("L1-S3", VehicleSize.MOTORCYCLE)
        );
        Level l1 = new Level("L1", level1Spots);
        ParkingLot parkingLot = new ParkingLot("pl1",List.of(l1));
        FeeStrategy feeStrategy = new BaseFare(5.0); // $5 per hour
        PaymentService paymentService = new PaymentService();
        ParkingService parkingService = new ParkingService(parkingLot, feeStrategy, paymentService);
        Car car = new Car("ABC123");
        ParkingTicket ticket = parkingService.parkVehicle(car);
        if(ticket != null){
            System.out.println("Parked vehicle. Ticket ID: " + ticket.getTicketId());
            // Simulate some parking duration here if needed
            double amount = parkingService.unParkVehicle(ticket);
            if(amount != -1){
                System.out.println("Unparked vehicle. Amount paid: $" + amount);
            } else {
                System.out.println("Payment failed for ticket ID: " + ticket.getTicketId());        
            }
            

    }
}}