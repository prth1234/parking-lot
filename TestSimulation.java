import java.util.List;

public class TestSimulation{
    public static void main(String[] args){
        // Setup shared parking lot
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

        Car car = new Car("TEST1");

        // Test 1: 9:00 -> 10:30 (90 minutes) -> ceil(1.5) = 2 hours -> $10
        Time issuance1 = new Time(9, 0);
        Time exit1 = new Time(10, 30);
        ParkingTicket t1 = parkingService.parkVehicle(car, issuance1);
        if (t1 != null) {
            t1.setExitTime(exit1);
            double amount1 = parkingService.unParkVehicle(t1);
            System.out.println("Test1 expected $10.0, got: $" + amount1);
        }

        // Test 2: 9:00 -> 9:00 (0 minutes) -> $0
        Car car2 = new Car("TEST2");
        Time issuance2 = new Time(9, 0);
        Time exit2 = new Time(9, 0);
        ParkingTicket t2 = parkingService.parkVehicle(car2, issuance2);
        if (t2 != null) {
            t2.setExitTime(exit2);
            double amount2 = parkingService.unParkVehicle(t2);
            System.out.println("Test2 expected $0.0, got: $" + amount2);
        }
    }
}
