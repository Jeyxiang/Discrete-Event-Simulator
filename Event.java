package cs2030.simulator;

public abstract class Event {
    private final double time;
    private final Customer customer;
    
    /** Event is an abstract class, containing the default methods
    * that are found in all the Events classes.
    * @param time The time that the event occurred.
    * @param Customer The customer that is attached to the Event.
    */

    Event(double time,Customer customer) {
        this.customer = customer;
        this.time = time;
    }
    
    public double getTime() {
        return this.time;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Event getNextEvent() {
        return null;
    }

    public Statistics updateStats(Statistics stats) {
        return stats;
    }

    public boolean isTemp() {
        return false;
    }

    public boolean checkRest() {
        return false;
    }

    public void updateServer() {
    }

    public void updateServer(double value) {
    }


}
