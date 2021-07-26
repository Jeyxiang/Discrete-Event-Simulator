import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import cs2030.simulator.Server;
import cs2030.simulator.Event;
import cs2030.simulator.EComp;
import cs2030.simulator.Arrives;
import cs2030.simulator.Customer;
import cs2030.simulator.Statistics;

class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int queueLength = sc.nextInt();
        int customerCount = 1;
        int servedCount = 0;
        int leaveCount = 0;
        Statistics stats = new Statistics();

        PriorityQueue<Event> events = new PriorityQueue<Event>(new EComp());
        Server[] servers = new Server[numOfServers];

        for (int i = 0; i < servers.length; i++) {
            servers[i] = new Server(i + 1,queueLength);
        }
        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double servingTime = sc.nextDouble();
            boolean isGreedy = sc.nextBoolean();
            Customer customer = new Customer(arrivalTime,customerCount,servingTime,isGreedy);
            customerCount++;
            events.add(new Arrives(arrivalTime,customer,servers));
        }
        /** next event will be added into the priority queue.The
        event will then call the method updateStats() to update the 
        statistics class if necessary.
        */

        while (!events.isEmpty()) { 
          
            Event newEvent;
            Event processedEvent = events.poll();
            if (!processedEvent.isTemp()) {
                System.out.println(processedEvent);
            }
            processedEvent.updateServer();
            newEvent = processedEvent.getNextEvent();
            if (newEvent != null) {
                events.add(newEvent);
                stats = newEvent.updateStats(stats);
            }
        }
        System.out.println(stats);       
    }
}

