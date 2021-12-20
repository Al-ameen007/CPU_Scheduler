import java.awt.*;
import java.util.ArrayList;


public class Scheduler {
    //TODO input must be sorted by Arrival time
    //TODO change the arraylist to queue
    public static void main(String[] args) {
        /*ArrayList<Process> processes = new ArrayList<>(3);
        Process p1 = new Process("p1", Color.red, 0, 4, 1);
        Process p2 = new Process("p2", Color.blue, 1, 4, 3);
        Process p3 = new Process("p3", Color.gray, 1, 6, 2);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        //ShortestJobFirst sjf = new ShortestJobFirst(processes);
        //HighestPriority hp = new HighestPriority(processes);


        //ShortestJobFirst.shortestJobFirst(processes);
        //HighestPriority.priorityScheduling(processes);*/
        //ProcessAgat(String name, Color color, int arrivalTime, int burstTime, int priorityNumber, int quantum_)

        ArrayList<ProcessAgat> processAgats = new ArrayList<>(4);
        ProcessAgat p1 = new ProcessAgat("P1", Color.red, 0, 17, 4, 4);
        ProcessAgat p2 = new ProcessAgat("P2", Color.red, 3, 6, 9, 3);
        ProcessAgat p3 = new ProcessAgat("P3", Color.red, 4, 10, 3, 5);
        ProcessAgat p4 = new ProcessAgat("P4", Color.red, 29, 4, 8, 2);
        processAgats.add(p1);
        processAgats.add(p2);
        processAgats.add(p3);
        processAgats.add(p4);
        AgatScheduler s = new AgatScheduler(processAgats);
        s.agatS();
    }
}
