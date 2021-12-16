import java.awt.Color;
public class ProcessAgat extends Process{
    int quantum;
    double agatFactor;
    ProcessAgat(String name, Color color, int arrivalTime, int burstTime, int priorityNumber, int q) {
        super(name, color, arrivalTime, burstTime, priorityNumber);
        quantum = q;
    }    
}
