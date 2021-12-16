import java.awt.Color;
public class ProcessAgat extends Process{
    int quantum;
    double agatFactor;

    
    ProcessAgat(String name, Color color, int arrivalTime, int burstTime, int priorityNumber, int q) {
        super(name, color, arrivalTime, burstTime, priorityNumber);
        quantum = q;
    }

    void update_agate(AgatScheduler agatScheduler){
        agatFactor = (10 - priorityNumber) + Math.ceil(arrivalTime / agatScheduler.v1) + Math.ceil(burstTime / agatScheduler.v2);
    }

}
