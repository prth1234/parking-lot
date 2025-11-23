
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
        ParkingTicket ticket;
        // Allow passing manual times via command-line for simulation: issuanceHour issuanceMinute exitHour exitMinute
        if (args.length == 4) {
            try {
                int isHour = Integer.parseInt(args[0]);
                int isMin = Integer.parseInt(args[1]);
                int exHour = Integer.parseInt(args[2]);
                int exMin = Integer.parseInt(args[3]);
                Time issuance = new Time(isHour, isMin);
                Time exit = new Time(exHour, exMin);
                ticket = parkingService.parkVehicle(car, issuance);
                if (ticket != null) {
                    ticket.setExitTime(exit);
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid time arguments. Expected 4 integers: issuanceHour issuanceMinute exitHour exitMinute");
                return;
            }
        } else {
            ticket = parkingService.parkVehicle(car);
        }
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