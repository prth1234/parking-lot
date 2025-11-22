

public class ParkingSpot{
    private final String spotId;
    private final VehicleSize vehicleSize;
    private volatile Vehicle currentVehicleInSpot;
    private Boolean isFree = true;
    public ParkingSpot(String spotId, VehicleSize vehicleSize){
        this.spotId=spotId;
        this.vehicleSize=vehicleSize;
    }

    public String getId() { return spotId; }
    public VehicleSize getSize() { return vehicleSize; }

    public boolean canFitVehicle(Vehicle vehicle){
        VehicleSize vsize=vehicle.getSize();
        switch (vsize){
            case MOTORCYCLE:
                return true;
            case COMPACT:
                return vehicleSize==VehicleSize.COMPACT || vehicleSize==VehicleSize.LARGE;
            case LARGE:
                return vehicleSize==VehicleSize.LARGE;
            default:
                return false;
        }   
       
    }

    public boolean park (Vehicle vehicle){
        if (isFree && canFitVehicle(vehicle)){
            currentVehicleInSpot=vehicle;
            isFree=false;
            return true;
        }
        else{
            return false;
        }
    }

    public void vacateSpot(){
        currentVehicleInSpot=null;
        isFree=true;
    }

    public boolean isFree(){
        return isFree;
    }



}