package cs2030.simulator;

public class Serving extends Event {
    private final int serverid; 
    private final Server[] servers;
    private final boolean wasWaiting;
    private final double finishingtime;

    /** This serving event indicates that the customer is served by a particular server.
    * @param time The time of the Event.
    * @param customer The customer that belongs to this event.
    * @param servers A server array that most Events point to.Constantly updated.
    * @param serverid The server that belongs to this event.
    * @param wasWaiting an attribute to check if the customer was previously waiting or not.
    */

    Serving(double time,Customer customer,Server[] servers,int serverid,boolean wasWaiting) {
        super(time,customer);
        this.servers = servers;
        this.serverid = serverid;
        this.wasWaiting = wasWaiting;
        this.finishingtime = time + customer.getServingTime();
    }

    public int getServerID() {
        return this.serverid;
    }

    public String toString() {
        return String.format("%.3f %s serves by server %d",super.getTime(),
            super.getCustomer().toString(),this.serverid);
    }

    public double getFinishingTime() {
        return this.finishingtime;
    }

    public Event getNextEvent() {
        double newtime = this.getFinishingTime();
        return new Done(newtime,super.getCustomer(),this.servers,this.serverid);
    }

    /** This method will update the statistic class. It will calculate the customer's
    * total waiting time, and count the number of customer that has been served.
    */

    @Override
    public Statistics updateStats(Statistics stat) {
        double time = super.getTime() - super.getCustomer().getArrivaltime();
        return stat.incrementWaitnServed(time);
    }

    /** The updateServer() will close the server such that it can no longer serve any
    * customer. At the same time, if the customer was previously waiting at the server,
    * the waiting queue in the server will automatically remove a customer,else it will
    * only close the server.
    */

    public void updateServer() {
        Server actualServer = this.servers[this.serverid - 1];
        if (this.wasWaiting) {
            servers[this.serverid - 1] = actualServer.removeWaitingList(this.finishingtime);
        } else {
            servers[this.serverid - 1] = actualServer.updateServing(this.finishingtime); 
        }
    }
}
