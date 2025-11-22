import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingService{
    private final ParkingLot parkingLot;
    private final FeeStrategy feeStrategy;
    private final PaymentService paymentService;
    public ParkingService(ParkingLot parkingLot, FeeStrategy feeStrategy, PaymentService paymentService){
        this.parkingLot=parkingLot;
        this.feeStrategy=feeStrategy;
        this.paymentService=paymentService;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle){
        ParkingSpot spot=    parkingLot.findSpotAndPark(vehicle);
        if(spot==null){
            // log.info("No available spot for vehicle with license number: " + vehicle.getlicenseNumber());
            System.err.println("No available spot for vehicle with license number: " + vehicle.getlicenseNumber());
            return null;
        }
        // create issuance time as our Time class (hours/minutes)
        LocalDateTime now = LocalDateTime.now();
        Time issuance = new Time(now.getHour(), now.getMinute());
        ParkingTicket ticket = new ParkingTicket(UUID.randomUUID().toString(), spot.getId(), vehicle.getlicenseNumber(), issuance, null);
        return ticket;
    }

    public double unParkVehicle(ParkingTicket ticket){
        if (ticket == null) return -1;
        LocalDateTime now = LocalDateTime.now();
        Time exitTime = new Time(now.getHour(), now.getMinute());
        ticket.setExitTime(exitTime);
        double amount = feeStrategy.calcFee(ticket);
        boolean paymentStatus = paymentService.processPayment(ticket.getTicketId(), amount);
        if(!paymentStatus){
            return -1;
             }
        // vacate the spot in the lot now that payment was successful
        parkingLot.vacateSpot(ticket.getSpotId());
        return amount;

    }
}
