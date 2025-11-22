// Using the project-defined Time class on ParkingTicket, so avoid java.time conversions here

public interface FeeStrategy{
    double calcFee(ParkingTicket ticket);
}

class BaseFare implements FeeStrategy{
    private final double baseFare;
    public BaseFare (double baseFare){
        this.baseFare=baseFare;
    }
    public double calcFee(ParkingTicket ticket){
        // compute minutes using Time getters added to ParkingTicket.Time
        if (ticket.getIssuanceTime() == null || ticket.getExTime() == null) return 0.0;
        int startMinutes = ticket.getIssuanceTime().getHours() * 60 + ticket.getIssuanceTime().getMinutes();
        int endMinutes = ticket.getExTime().getHours() * 60 + ticket.getExTime().getMinutes();
        long time = Math.max(0, endMinutes - startMinutes);
        // If there's no elapsed time, charge 0. Otherwise, charge at least 1 hour for any non-zero time.
        if (time == 0) return 0.0;
        double hrs = Math.max(1.0, Math.ceil(time / 60.0));
        return baseFare * hrs;
    }
}