import java.awt.Color;
public class ProcessAgat extends Process{
    double quantum;
    double agatFactor;
    
    ProcessAgat(String name, Color color, int arrivalTime, int burstTime, int priorityNumber, int quantum_) {
        super(name, color, arrivalTime, burstTime, priorityNumber);
        quantum = quantum_;
    }
    double quantumFactor(){
        return Math.round(quantum * 0.4);
    }
    void info(){
        System.out.print("Name: "+ name + " BursetTime: " + burstTime + " Quantium: " + quantum);
    }
    void update_agate(AgatScheduler agatScheduler){
        //all of them share the same V1, V2
        agatFactor = Math.ceil((10 - priorityNumber) + Math.ceil(arrivalTime / agatScheduler.v1) + Math.ceil(burstTime / agatScheduler.v2));
    }
}
