import java.awt.*;
import java.util.ArrayList;


public class Scheduler {

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>(3);
        Process p1 = new Process("p1", Color.red, 0, 4, 1);
        Process p2 = new Process("p2", Color.blue, 1, 4, 3);
        Process p3 = new Process("p3", Color.gray, 1, 6, 2);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        ShortestJobFirst sjf = new ShortestJobFirst(processes);
        HighestPriority hp = new HighestPriority(processes);


        sjf.shortestJobFirst(processes);
        hp.priorityScheduling(processes);
    }
}
