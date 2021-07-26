package cs2030.simulator;


public class Arrives extends Event {
    private final Server[] servers;

    public Arrives(double time,Customer customer,Server[] servers) {
        super(time,customer);
        this.servers = servers;
    }

    public String toString() {
        return String.format("%.3f %s arrives",super.getTime(),super.getCustomer().toString());
    }

    public Server[] getServer() {
        return this.servers;
    }

    /**Arrives will be the First event to be called.So it will dictate the paths
     * Arrives - Serve - Leave , Arrives - Wait - Serve - Leave, Arrive - Leave.This 
     * getNextEvent method that exist in Arrive will first check if any servers can take
     * in a customer.If nothing is returned,there will be a separate check for both greedy
     * and regular customers.
     * @return a either a Temporary dummy Event, a Waiting Event, or a Leave Event.
     */

    public Event getNextEvent() {
        for (Server serv:this.servers) {
            if (serv.canServe()) {
                Event tempEvent = new Temp(super.getTime(),super.getCustomer(),
                    this.servers,serv.getServerNo(),false);
                return tempEvent;
            }
        }

        /** If the customer is not greedy, he/she will look for the first Server 
         * available to accept waits.If nothing is returned, the customer has 
         * no other choice but to Leave.
         */

        if (!super.getCustomer().isGreedy()) { 
            for (Server serv:this.servers) {
                if (serv.canWait()) {
                    Event waitEvent = new Wait(super.getTime(),super.getCustomer(),
                        this.servers,serv.getServerNo());
                    return waitEvent;
                }
            }
            /** If the customer is greedy, we will first check if there any servers 
             * available to wait in using the boolean canFind.If not, the customer 
             * will leave.Once we found a suitable server, we will assign 'shortestServer' 
             * to it so it will serve as the default server.Afterwards, we will run another
             *  loop to find the server with the shortest queuelength.
            */
        } else {
            boolean canFind = false;
            Server shortestServer = servers[0];
            for (Server serv : servers) {
                if (serv.canWait()) {
                    shortestServer = serv;
                    canFind = true;
                    break;
                }
            }
            if (canFind) {
                double timeNow = super.getTime();
                for (Server serv : servers) {
                    if (serv.canWait() && serv.checkLength() < shortestServer.checkLength()) {
                        shortestServer = serv;
                    }
                }
                Event newWait = new Wait(super.getTime(),super.getCustomer(),
                    servers,shortestServer.getServerNo());
                return newWait;
            }
        }

        return new Leave(super.getTime(),super.getCustomer());
    }



}
