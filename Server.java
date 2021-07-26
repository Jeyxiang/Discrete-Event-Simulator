package cs2030.simulator;

public class Server {
    private final int serverNo;
    private final double nextAvailtime;
    private final boolean open;
    private final int inQueue;
    private final int queuelength;

    /** A server has a default constructor that accepts two parameters.
    * @param serverNo The unique server number.
    * @param queuelength The maximum number of customers that can wait at this server.
    */

    public Server(int serverNo,int queuelength) {
        this.serverNo = serverNo;
        this.nextAvailtime = 0;
        this.open = true;
        this.inQueue = 0;
        this.queuelength = queuelength;
    }

    /** This overloaded constructor consist of more parameters so that the servers can be
    * constantly updated according to its status.
    * @param open A boolean type that is true if the customer is open to server customers.
    * @param inQueue The number of customers currently waiting in the queue.
    * @param nextAvailtime The next Available time of the server to accept customers.
    */

    public Server(int serverNo,int queuelength,boolean open,int inQueue,double nextAvailtime) {
        this.serverNo = serverNo;
        this.queuelength = queuelength;
        this.open = open;
        this.inQueue = inQueue;
        this.nextAvailtime = nextAvailtime;
    }

    public int getServerNo() {
        return this.serverNo;
    }

    public double getAvailtime() {
        return this.nextAvailtime;
    }

    public boolean canWait() {
        return this.inQueue < this.queuelength;
    }

    public int checkLength() {
        return this.inQueue;
    }

    public boolean canServe() {
        return this.open;
    }

    /** This method is activated in the Serving Event.The next available time 
    * of the server will be updated.
    * @param time The serving time of the customer.
    */
    public Server updateServing(double time) {
        return new Server(this.serverNo,this.queuelength,false,this.inQueue,time);
    }

    /** This method is also activated in the Serving Event.It removes a
    * customer from the waiting queue.
    */
    public Server removeWaitingList(double time) {
        return new Server(this.serverNo,this.queuelength,
            false,this.inQueue - 1,time);
    }

    /** This method is activated in the Waiting Event.It will add a customer
    * into the waiting queue.
    */
    public Server addWaitingList() {
        return new Server(this.serverNo,this.queuelength,
            this.open,this.inQueue + 1,this.nextAvailtime);
    }

    /** This method will update server with its rest time.
    */
    public Server updateDone(double restTime) {
        return new Server(this.serverNo,this.queuelength,false,this.inQueue,
            this.nextAvailtime + restTime);
    }

    /** This method will open up a Server so that it can continue to accept customers
    * once again.
    */
    public Server openServer() {
        return new Server(this.serverNo,this.queuelength,true,this.inQueue,
            this.nextAvailtime);
    }


}
