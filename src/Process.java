import java.awt.*;

public class Process {
    String name;
    Color color;
    int arrivalTime;
    int burstTime;
    int dummyBurstTime;
    int priorityNumber;
    int waitingTime;
    int turnaroundTime;
    boolean starved = false;
    int remainingTime;

    Process(String name, Color color, int arrivalTime, int burstTime, int priorityNumber) {
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.dummyBurstTime = burstTime;
        this.remainingTime = burstTime;
    } 
    }
}
