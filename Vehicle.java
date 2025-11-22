public abstract class Vehicle{
    private final String licenseNumber;
    public Vehicle(String licenseNumber){
        this.licenseNumber=licenseNumber;
    }
    public String getlicenseNumber(){
        return licenseNumber;
    }
    public abstract VehicleSize getSize();

}

enum VehicleSize { MOTORCYCLE, COMPACT, LARGE }

class Car extends Vehicle{
    public Car (String licenseNumber){
        super(licenseNumber);
    }   
    public VehicleSize getSize(){
        return VehicleSize.COMPACT;
    }
}

class Motorcycle extends Vehicle{
    public Motorcycle (String licenseNumber){
        super(licenseNumber);
    }   
    public VehicleSize getSize(){
        return VehicleSize.MOTORCYCLE;
    }
}   


class Bus extends Vehicle{
    public Bus (String licenseNumber){
        super(licenseNumber);
    }   
    public VehicleSize getSize(){
        return VehicleSize.LARGE;
    }
}   


class Truck extends Vehicle{
    public Truck (String licenseNumber){
        super(licenseNumber);
    }   
    public VehicleSize getSize(){
        return VehicleSize.LARGE;
    }
}   


class Van extends Vehicle{
    public Van (String licenseNumber){
        super(licenseNumber);
    }   
    public VehicleSize getSize(){
        return VehicleSize.LARGE;
    }
}
