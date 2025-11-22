import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Level{
    private final String levelNumber;
    private final List<ParkingSpot> parkingSpots;
    private final ReentrantLock lock = new ReentrantLock();

    public Level(String levelNumber, List<ParkingSpot> parkingSpots){
        this.levelNumber=levelNumber;
        this.parkingSpots=parkingSpots;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle){
        lock.lock();
        try {
            for(ParkingSpot spot: parkingSpots){
                if(spot.park(vehicle)){
                    return spot;
                }
            }
        } finally{
            lock.unlock();
        }
    
        return null;}

    public void vacateSpot(String spotId){
        lock.lock();
        try{
            for(ParkingSpot spot: parkingSpots){
                if(spot.getId().equals(spotId)){
                    spot.vacateSpot();
                    break;
                }
            }
        }
        finally{
            lock.unlock();
        }
    }


}