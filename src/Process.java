import java.awt.*;


public class Process{
    String name;
    Color color;
    int arrivalTime;
    int burstTime;
    int priorityNumber;
    int waitingTime;

    Process(String name, Color color, int arrivalTime, int burstTime ,int priorityNumber){
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.waitingTime = 0;
    }
}
