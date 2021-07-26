package cs2030.simulator;

public class Leave extends Event {
    /** leave Event that indicates that a customer left without being served.
    * @param time The time of the Event.
    * @param customer The customer that belongs to this event.
    */
    public Leave(double time,Customer customer) {
        super(time,customer);
    }

    public String toString() {
        return String.format("%.3f %s leaves",super.getTime(),super.getCustomer().toString());
    }

    /** This method will update the statistic class of the number of customers that left
    * without being served.
    * @param stat The updated Statistics class.
    */
    
    @Override
    public Statistics updateStats(Statistics stat) {
        return stat.incrementLeave();
    }
}

