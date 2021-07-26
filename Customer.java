package cs2030.simulator;

import java.util.Optional;
import java.util.function.Supplier;

public class Customer {
    private final double time;
    private final int customerid;
    private final Optional<Double> servingTime;
    private final Supplier<Double> serviceGen;
    private final boolean greedy;

    /** Customer class has several attributes.It has multiple overloaded methods that
    * satisifies different levels.
    * @param time The time when the customer arrived.
    * @param customerid The pre-assigned, unique ID that each customer has.
    * @param servingTime An optional that contains the serving time that is manually inserted.
    */

    public Customer(double time,int customerid,double servingTime) {
        this.time = time;
        this.customerid = customerid;
        this.servingTime = Optional.ofNullable(servingTime);
        this.serviceGen = () -> 0.0;
        this.greedy = false;
    }
    /**
    * This overloaded constructor is responsible for handling the existence of Greedy
    * customers.
    * @param greedy A boolean that indicates with a customer is greedy or not.
    */

    public Customer(double time,int customerid,double servingTime,boolean greedy) {
        this.time = time;
        this.customerid = customerid;
        this.servingTime = Optional.ofNullable(servingTime);
        this.serviceGen = () -> 0.0;
        this.greedy = greedy;
    }

    /**
     * This overloaded constructor is responsible for handling the 5th level,where the
     * serving time is only known when the Customer is getting Served.
     * @param serviceGen A supplier that generates the serving time when called upon.
     */

    public Customer(double time,int customerid,boolean greedy,Supplier<Double> serviceGen) {
        this.time = time;
        this.customerid = customerid;
        this.servingTime = Optional.empty();
        this.serviceGen = serviceGen;
        this.greedy = greedy;
    }

    public double getArrivaltime() {
        return this.time;
    }

    public int getID() {
        return this.customerid;
    }

    /** This method will return the total serving time in the optional,
    * and if the optional is empty the supplier will be called instead.
    */

    public double getServingTime() {
        return servingTime.orElseGet(serviceGen);
    }

    public boolean isGreedy() {
        return this.greedy;
    }

    public String toString() {
        if (this.greedy) {
            return String.format("%d(greedy)",this.customerid);
        }
        return String.format("%d",this.customerid);
    }
}
