package cs2030.simulator;

class Wait extends Event {
    private final int serverid;
    private final Server[] servers;

    public Wait(double time,Customer customer,Server[] servers,int serverid) {
        super(time,customer);
        this.servers = servers;
        this.serverid = serverid;
    }

    public int getServerID() {
        return this.serverid;
    }

    public String toString() {
        return String.format("%.3f %s waits at server %d",super.getTime(),
            super.getCustomer().toString(),this.serverid);
    }

    /** The Event will be converted into a Temporary dummy Event.
    */

    public Event getNextEvent() {
        Server currentServer = this.servers[this.serverid - 1];
        double newservetime = currentServer.getAvailtime();
        return new Temp(newservetime,super.getCustomer(),this.servers,this.serverid,true);
    }

    /** This method will update the Server's waiting queue.
    */
    public void updateServer() {
        int serverID = this.serverid;
        Server actualserver = this.servers[serverID - 1];
        servers[serverID - 1] = actualserver.addWaitingList();
        
    }
        

}


