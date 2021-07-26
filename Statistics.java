package cs2030.simulator;

public class Statistics {
    private final int leaveCount;
    private final int servedCount;
    private final double[] waitingCounts;

    /** The default constructor for the Statistic class.
    */

    public Statistics() {
        this.leaveCount = 0;
        this.servedCount = 0;
        this.waitingCounts = new double[0];
    }

    /** This Class performs the calculation for average wait time, total customers served
     * and the total customers that left without being served.This constructor will enable
     * the statistics class to be constantly updated.
     * @param leaveCount The number of customers that left without being served.
     * @param servedCount The number of customers that was served.
     * @param waitingCounts the array that stores all the waiting time.
     */

    public Statistics(int leaveCount,int servedCount,double[] waitingCounts) {
        this.leaveCount = leaveCount;
        this.servedCount = servedCount;
        this.waitingCounts = waitingCounts;
    }

    /** incrementLeave increment the number of customers that left without being
      served by 1.This method only runs when a Leave Event is polled out.
      */

    public Statistics incrementLeave() {
        return new Statistics(this.leaveCount + 1,this.servedCount,this.waitingCounts);
    }

    /** incrementWaitnServed adds the waiting time input into the existing array,
      and increment the number of serving customers by one.This method only runs
      when a serving Event is polled out.
      */

    public Statistics incrementWaitnServed(double time) {
        double[] waitingList = new double[this.waitingCounts.length + 1];
        for (int i = 0;i < this.waitingCounts.length;i++) {
            waitingList[i] = waitingCounts[i];
        }
        waitingList[this.waitingCounts.length] = time;
        return new Statistics(this.leaveCount,this.servedCount + 1,waitingList);
    }

    /** This method performs the calculation to obtain the average waiting time.
    */

    public double waitAvg() {
        double total = 0;
        for (double i : waitingCounts) {
            total += i;
        }
        if (servedCount == 0) {
            return 0.0;
        } else {
            return total / this.servedCount;
        }
    }

    public String toString() {
        return String.format("[%.3f %d %d]",this.waitAvg(),
                this.servedCount,this.leaveCount);
    }

}
