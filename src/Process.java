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
    int quantum;
    double agatFactor;
    Process(String name, Color color, int burstTime, int arrivalTime, int priorityNumber, int quantum)
    {
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.dummyBurstTime = burstTime;
        this.remainingTime = burstTime;
        this.quantum = quantum;
    }

    Process(Process other)
    {
        this.name = other.name;
        this.color = other.color;
        this.arrivalTime = other.arrivalTime;
        this.burstTime = other.burstTime;
        this.priorityNumber = other.priorityNumber;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.dummyBurstTime = burstTime;
        this.remainingTime = burstTime;
        this.quantum = other.quantum;
        this.agatFactor = other.agatFactor;
    }
    double quantumFactor(){
        return Math.round(quantum * 0.4);
    }
    void info(){
        System.out.print("Name: "+ name + " BurstTime: " + burstTime + " Quantum: " + quantum);
    }
    void update_agate(AgatScheduler agatScheduler){
        //all of them share the same V1, V2
        agatFactor = Math.ceil((10 - priorityNumber) + Math.ceil(arrivalTime / agatScheduler.v1) + Math.ceil(burstTime / agatScheduler.v2));
    }


}
