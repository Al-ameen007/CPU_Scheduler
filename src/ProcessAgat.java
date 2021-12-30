import java.awt.Color;
public class ProcessAgat extends Process{
    double quantum;
    double agatFactor;
    
    ProcessAgat(String name, Color color, int arrivalTime, int burstTime, int priorityNumber, int quantum_) {
        super(name, color, arrivalTime, burstTime, priorityNumber, quantum_);
        quantum = quantum_;
    }
    
}
