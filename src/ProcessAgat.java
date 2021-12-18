import java.awt.Color;
public class ProcessAgat extends Process{
    int quantum;
    double agatFactor;
    double quantumFactor; //quantum * 0.4
    
    ProcessAgat(String name, Color color, int arrivalTime, int burstTime, int priorityNumber, int q) {
        super(name, color, arrivalTime, burstTime, priorityNumber);
        quantum = q;
        quantumFactor = Math.round(q * 0.4);
    }

    void update_agate(AgatScheduler agatScheduler){
        //all of them share the same V1, V2
        agatFactor = (10 - priorityNumber) + Math.ceil(arrivalTime / agatScheduler.v1) + Math.ceil(burstTime / agatScheduler.v2);
    }

}
