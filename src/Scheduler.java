import java.awt.*;
import java.util.ArrayList;


public class Scheduler {
    //TODO input must be sorted by Arrival time
    //TODO change the arraylist to queue ask the TA

    public static void main(String[] args) {

        ArrayList<Process> processes = new ArrayList<>(3);
        Process p1 = new Process("p1", Color.red, 0, 2, 3);
        Process p2 = new Process("p2", Color.blue, 2, 3, 1);
        Process p3 = new Process("p3", Color.gray, 5, 6, 4);
        Process p4 = new Process("p4", Color.cyan, 1, 21, 12);
        Process p5 = new Process("p5", Color.green, 4, 5, 2);
        Process p6 = new Process("p6", Color.black, 4, 6, 2);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        processes.add(p6);
        int contextSwitch = 1;

        /*
        ArrayList<Process> processes = new ArrayList<>(3);
        Process p1 = new Process("p1", Color.red, 0, 4, 1);
        Process p2 = new Process("p2", Color.blue, 1, 4, 3);
        Process p3 = new Process("p3", Color.gray, 1, 6, 2);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        */


        ArrayList<Process> sjf= ShortestJobFirst.solveStarve(processes);
        ArrayList<Process> ps = HighestPriority.solveStarve(processes, contextSwitch);
        ShortestJobFirst.display(sjf);
        HighestPriority.display(ps);


        /*      ArrayList<ProcessAgat> processAgats = new ArrayList<>(4);
=======
        /*ArrayList<ProcessAgat> processAgats = new ArrayList<>(4);

        ProcessAgat p1 = new ProcessAgat("P1", Color.red, 0, 17, 4, 4);
        ProcessAgat p2 = new ProcessAgat("P2", Color.red, 3, 6, 9, 3);
        ProcessAgat p3 = new ProcessAgat("P3", Color.red, 4, 10, 3, 5);
        ProcessAgat p4 = new ProcessAgat("P4", Color.red, 29, 4, 8, 2);
        processAgats.add(p1);
        processAgats.add(p2);
        processAgats.add(p3);

        processAgats.add(p4);
        AgatScheduler s = new AgatScheduler(processAgats);
        s.agatS();*/

        processAgats.add(p4);*/

        ArrayList<Process> processes = new ArrayList<>(5);
        Process p1 = new Process("p1", Color.red, 0, 8, 0);
        Process p2 = new Process("p2", Color.blue, 1, 4, 0);
        Process p3 = new Process("p3", Color.gray, 2, 2, 0);
        Process p4 = new Process("p4", Color.gray, 3, 1, 0);
        Process p5 = new Process("p5", Color.gray, 4, 3, 0);
        Process p6 = new Process("p6", Color.gray, 5, 2, 0);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        processes.add(p6);
        ShortestRemainingTime srtf = new ShortestRemainingTime(processes);
        srtf.Schedule();

        //AgatScheduler s = new AgatScheduler(processAgats);
        //s.agatS();
    }
}
