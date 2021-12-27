import java.awt.*;


public class Process implements Comparable<Process>{
    String name;
    Color color;
    int arrivalTime;
    int burstTime;
    int priorityNumber;
    int waitingTime;
    int turnaroundTime;
    int remainingTime;

    Process(String name, Color color, int arrivalTime, int burstTime ,int priorityNumber){
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.remainingTime = burstTime;
    }


    public int compareTo(Process comparesTo) {
        int compareShortestTime = comparesTo.burstTime;
        return compareShortestTime - this.burstTime;
    }

}
