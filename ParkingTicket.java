public class ParkingTicket{
    private final String ticketId;
    private final String spotId;
    private final String licenseNumber;
    private final Time issuanceTime;
    private  Time exTime;

    public ParkingTicket(String ticketId, String spotId, String licenseNumber, Time issuanceTime, Time exTime){
        this.ticketId=ticketId;
        this.spotId=spotId;
        this.licenseNumber=licenseNumber;
        this.issuanceTime=issuanceTime;
        this.exTime=exTime;
    }

    public String getTicketId(){
        return ticketId;
    }
    public String getSpotId(){
        return spotId;
    }
    public String getLicenseNumber(){       
        return licenseNumber;      }

    public Time getIssuanceTime(){
        return issuanceTime;    }
    
    public Time getExTime(){
        return exTime;  }
    public void setExitTime(Time exTime){
        this.exTime=exTime; 
    }   


}


class Time{
    private final int hours;
    private final int minutes;

    public Time(int hours, int minutes){
        this.hours=hours;
        this.minutes=minutes;
    }

    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }
}