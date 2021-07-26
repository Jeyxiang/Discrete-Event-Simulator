package cs2030.simulator;

public class Done extends Event {
    private final int serverid;
    private final Server[] servers;

    /** This event will indicate when a Server has finished serving its assigned
    * customer.
    * @param time The time of the Event.
    * @param customer The customer that belongs to this event.
    * @param servers A server array that most Events point to.Constantly updated.
    * @param serverid The server that belongs to this event.
    */

    public Done(double time,Customer customer,Server[] servers,int serverid) {
        super(time,customer);
        this.servers = servers;
        this.serverid = serverid;
    }

    public String toString() {
        return String.format("%.3f %s done serving by server %d",this.getTime(),
            super.getCustomer().toString(), this.serverid);
    }

    /** A rest Event will be created,and it will be polled out at the server's next available time,
    * when it finishes resting. This ensures that the Server will rest.
    */

    public Event getNextEvent() {
        Server currentServer = servers[this.serverid - 1];
        return new Rest(currentServer.getAvailtime(),super.getCustomer(),
            this.servers,this.serverid);
    }

    /** Two overloaded methods: the first one presumes that rest time will always be 0
    * while the second method takes in a double rest Time.This will update the server
    * Such that the updated available time of that server accounts for the rest time.It
    * will only be called once the Done event is polled out.
    */

    public void updateServer() {
        Server currentServer = servers[this.serverid - 1];
        servers[this.serverid - 1] = currentServer.updateDone(0);
    }

    public void updateServer(double rest) {
        Server currentServer = servers[this.serverid - 1];
        servers[this.serverid - 1] = currentServer.updateDone(rest);
    }

    @Override
    public boolean checkRest() {
        return true;
    }
}

