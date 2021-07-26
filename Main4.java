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

class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int queueLength = sc.nextInt();
        int totalCust = sc.nextInt();
        int customerCount = 1;
        int servedCount = 0;
        int leaveCount = 0;
        Statistics stats = new Statistics();
        ArrayList<Double> resttimes = new ArrayList<>();
        PriorityQueue<Event> events = new PriorityQueue<Event>(new EComp());
        Server[] servers = new Server[numOfServers];

        for (int i = 0; i < servers.length; i++) {
            servers[i] = new Server(i + 1,queueLength);
        }
        for (int j = 0; j < totalCust; j++) {
            double arrivalTime = sc.nextDouble();
            double servingTime = sc.nextDouble();
            boolean isGreedy = false;
            Customer customer = new Customer(arrivalTime,customerCount,servingTime,isGreedy);
            customerCount++;
            events.add(new Arrives(arrivalTime,customer,servers));
        }
        while (sc.hasNextDouble()) {
            double serviceRestTime = sc.nextDouble();
            resttimes.add(serviceRestTime);
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

            if (processedEvent.checkRest()) { //for done events
                double temp = resttimes.get(0);
                int lastindex = resttimes.size() - 1;
                for (int i = 0; i < lastindex; i++) {
                    resttimes.set(i,resttimes.get(i + 1));
                }
                resttimes.set(lastindex,temp);
                processedEvent.updateServer(temp);
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
