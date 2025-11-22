import java.util.List;
public class ParkingLot{
    private String parkingLotId;
    private final List<Level> levels;
    private static volatile ParkingLot INSTANCE ; //singleton instance --> pattern
    // make a public constructor to match calls in Main (Main instantiates directly)
    public ParkingLot(String parkingLotId, List<Level> levels){
        this.parkingLotId = parkingLotId;
        this.levels = levels;
    }

    // keep a synchronized factory for a singleton if someone wants it
    public static synchronized ParkingLot createInstance(String parkingLotId, List<Level> levels){
        if (INSTANCE == null){
            INSTANCE = new ParkingLot(parkingLotId, levels);
        }
        return INSTANCE;
    }

    public static ParkingLot getInstance(){
        return INSTANCE;
    }

    public ParkingSpot findSpotAndPark(Vehicle vehicle){
        for(Level level: levels){
            ParkingSpot spot = level.parkVehicle(vehicle);
            if (spot!=null){    
                return spot;
            }
            
        }
        return null;
    }

    public void vacateSpot(String spotId){
        for(Level level: levels){
            level.vacateSpot(spotId);
        }
    }



}