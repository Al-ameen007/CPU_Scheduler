import java.awt.*;
import java.util.ArrayList;

public class Scheduler {

    public enum ScheduleType {
        AGAT,
        SRTF,
        SJF,
        Priority
    }

    private int contextSwitch = 1;
    private ArrayList<Process> processes;
    private ScheduleType scheduleType;

    Scheduler() {
        processes = new ArrayList<>();
        scheduleType = ScheduleType.AGAT;
    }

    // TODO Schedule based on current type
    public ScheduleData schedule() {
        if (processes.isEmpty()) {
            System.out.println("Null data");
            throw new RuntimeException();
        }

        switch (scheduleType) {
            case AGAT:
                AgatScheduler agatScheduler = new AgatScheduler(processes);
                System.out.println("Scheudling...");
                return agatScheduler.agatS();

            case SRTF:
                ShortestRemainingTime srtf = new ShortestRemainingTime(processes);
                System.out.println("Scheudling...");
                srtf.Schedule();
                return srtf.getScheduleData();
            case SJF:
                ShortestJobFirst shortestJobFirst = new ShortestJobFirst(processes);
                System.out.println("Scheudling...");
                shortestJobFirst.Schedule(processes);
                return shortestJobFirst.getScheduleData();
            case Priority:
                HighestPriority highestPriority = new HighestPriority(processes);
                System.out.println("Scheudling...");
                highestPriority.Schedule(processes, contextSwitch);
                return highestPriority.getScheduleData();
        }
        return new ScheduleData(0, 0, null);
    }

    public void createProcess(String name, Color color, int burstTime, int arrivalTime, int priority, int quantumTime) {
        processes.add(new Process(name, color, burstTime, arrivalTime, priority, quantumTime));
        System.out.println("created Process" + name);
    }

    public void setScheduleType(String type) {
        switch (type) {
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

    public int getSize() {
        return processes.size();
    }

    //TODO input is here Modified to test my agat
    void addTestData() {
        Process p1 = new Process("P1", Color.red, 17, 0, 4, 4);
        Process p2 = new Process("p2", Color.green, 6, 3, 9, 3);
        Process p3 = new Process("p3", Color.yellow, 10, 4, 3, 5);
        Process p4 = new Process("p4", Color.black, 4, 29, 8, 2);
        //ArrayList<Process> processes = new ArrayList<Process>();
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);


        /*processes = new ArrayList<>(5);
        Process p1 = new Process("p1", Color.red, 8, 0, 0, 0);
        Process p2 = new Process("p2", Color.blue, 4, 1, 0,0 );
        Process p3 = new Process("p3", Color.green, 2, 2, 0, 0);
        Process p4 = new Process("p4", Color.yellow, 1, 3, 0, 0);
        Process p5 = new Process("p5", Color.black, 3, 4, 0, 0);
        Process p6 = new Process("p6", Color.pink, 2, 5, 0, 0);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        processes.add(p6);*/
        System.out.println(processes.toString());
    }
}
