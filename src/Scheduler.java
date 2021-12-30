import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Scheduler {
    // used to return schedule data to GUI
    public class ScheduleData {
        // Array of processes output
        int avgWaitingTime;
        int avgTurnaroundTime;

        ScheduleData(int avgWaitingTime, int avgTurnaroundTime)
        {
            this.avgWaitingTime = avgWaitingTime;
            this.avgTurnaroundTime = avgTurnaroundTime;
        }
    }
    //TODO input must be sorted by Arrival time
    //TODO change the arraylist to queue ask the TA
    public enum ScheduleType {
        AGAT,
        SRTF,
        SJF,
        Priority
    }

    private ArrayList<Process> processes;
    private ScheduleType scheduleType;

    Scheduler()
    {
        processes = new ArrayList<>();
        scheduleType = ScheduleType.AGAT;
    }

    // TODO Schedule based on current type
    public ArrayList<ProcessGraphData> schedule()
    {
        System.out.println("Scheduling...");
        srtf.Schedule();
        return srtf.getGraphData();
    }

    public ScheduleData getScheduleOutput()
    {
        // TODO get actual data from scheduler
        Random random = new Random();
        return new ScheduleData(random.nextInt(2000),random.nextInt(2000));
    }

    public void createProcess(String name, int burstTime, int arrivalTime, int priority,  int quantumTime) {
        processes.add(new Process(name, Color.black, burstTime, arrivalTime,priority,quantumTime));
        System.out.println("created Process" + name);
    }

    public void setScheduleType(String type) {
        switch(type)
        {
            case "AGAT":
                scheduleType = ScheduleType.AGAT;
                break;
            case "SRTF":
                scheduleType = ScheduleType.SRTF;
                break;
            case "SJF":
                scheduleType = ScheduleType.SJF;
                break;
            case "Priority":
                scheduleType = ScheduleType.Priority;
                break;
        }
    }

    public int getSize()
    {
        return processes.size();
    }

    ShortestRemainingTime srtf;

    void addTestData()
    {
        ArrayList<Process> processes = new ArrayList<>(5);
        Process p1 = new Process("p1", Color.red, 0, 8, 0, 0);
        Process p2 = new Process("p2", Color.blue, 1, 4, 0,0 );
        Process p3 = new Process("p3", Color.green, 2, 2, 0, 0);
        Process p4 = new Process("p4", Color.yellow, 3, 1, 0, 0);
        Process p5 = new Process("p5", Color.black, 4, 3, 0, 0);
        Process p6 = new Process("p6", Color.pink, 5, 2, 0, 0);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        processes.add(p6);
        srtf = new ShortestRemainingTime(processes);
    }
}
