import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.Supplier;
import cs2030.simulator.Simulator;
import cs2030.simulator.Event;
import cs2030.simulator.Server;
import cs2030.simulator.EComp;

/** Responsible for parsing in the user inputs.

*/

class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int queueLength = sc.nextInt();
        int totalCust = sc.nextInt();
        int seed = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble();
        double probRest = sc.nextDouble();
        double probGreed = sc.nextDouble();

        
        Simulator sim = new Simulator(queueLength,totalCust,seed,
            arrivalRate,serviceRate,restingRate,probRest,probGreed,numOfServers);

        sim.simulate(); 
   
    }
}
