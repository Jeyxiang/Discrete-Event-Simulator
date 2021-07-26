package cs2030.simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.Supplier;

public class Simulator {
    private final int queueLength;
    private final double totalCust;
    private final RandomGenerator rand;
    private final Supplier<Double> arrivalTime;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restProb;
    private final Supplier<Double> restPeriod;
    private final Supplier<Double> typeCustomer;
    private final double probRest;
    private final double probGreed;
    private final Server[] servers;
    private final PriorityQueue<Event> events;

    /** This class simulates the entire simulation.It is able to access
    * the private class RandomGenerator, and create the necessary attributes.
    */
    public Simulator(int queuelength,double totalCust,int seed,
            double arrivalRate,double serviceRate, double restingRate,
            double probRest, double probGreed,int numOfservers) {
        this.rand = new RandomGenerator(seed,arrivalRate,serviceRate,restingRate);
        this.queueLength = queuelength;
        this.totalCust = totalCust;
        this.arrivalTime = () -> rand.genInterArrivalTime();
        this.serviceTime = () -> rand.genServiceTime();
        this.restProb = () -> rand.genRandomRest();
        this.restPeriod = () -> rand.genRestPeriod();
        this.typeCustomer = () -> rand.genCustomerType();
        this.probRest = probRest;
        this.probGreed = probGreed;
        this.servers = new Server[numOfservers];
        this.events = new PriorityQueue<Event>(new EComp());;
    }



    public void simulate() {
        double arrTime = 0;
        boolean isGreedy = false;
        int customerCount = 1;
        Statistics stats = new Statistics();
        Event newEvent;
        Event processedEvent;

        for (int i = 0; i < servers.length; i++) {
            servers[i] = new Server(i + 1,queueLength);
        }


        for (int j = 0; j < totalCust; j++) {
            if (typeCustomer.get() < this.probGreed) {
                isGreedy = true;
            } else {
                isGreedy = false;
            }

            Customer customer = new Customer(arrTime,customerCount,
                    isGreedy,this.serviceTime);
            customerCount++;
            events.add(new Arrives(arrTime,customer,servers));
            arrTime += arrivalTime.get();
        }

        while (!events.isEmpty()) { 
            processedEvent = events.poll();
            if (!processedEvent.isTemp()) {
                System.out.println(processedEvent);
            }
            if (processedEvent.checkRest()) {
                if (restProb.get() < this.probRest) {
                    processedEvent.updateServer(restPeriod.get());
                } else {
                    processedEvent.updateServer(0);
                }
            } else {
                processedEvent.updateServer();
            }

            newEvent = processedEvent.getNextEvent();
            if (newEvent != null) {
                events.add(newEvent);
                stats = newEvent.updateStats(stats);
            }
        }
        System.out.println(stats);     
    }


}
