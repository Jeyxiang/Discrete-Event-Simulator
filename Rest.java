package cs2030.simulator;

public class Rest extends Event {
    private final int serverid;
    private final Server[] servers;

    /** The Rest is a dummy event that will not visible to the client.
    * The main role of this event is to allow the servers the rest,and 
    * update the server when it finishes resting.
    * @param time The time of the Event.
    * @param customer The customer that belongs to this event.
    * @param servers A server array that most Events point to.Constantly updated.
    * @param serverid The server that belongs to this event.
    */

    public Rest(double time,Customer customer,Server[] servers,int serverid) {
        super(time,customer);
        this.servers = servers;
        this.serverid = serverid;
    }

    @Override
    public boolean isTemp() {
        return true;
    }

    /** This method is called when the Rest is polled out.That is the
    * time where the server finishes resting.This method will open up the
    * server such that it can start serving again.
    */

    public void updateServer() {
        Server currentServer = servers[this.serverid - 1];
        servers[this.serverid - 1] = currentServer.openServer();
    }
}