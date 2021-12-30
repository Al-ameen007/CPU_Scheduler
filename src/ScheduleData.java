import java.util.ArrayList;

// used to return schedule data to GUI
public class ScheduleData {
    // Array of processes output
    int avgWaitingTime;
    int avgTurnaroundTime;
    ArrayList<ProcessGraphData> processGraphData;

    ScheduleData(int avgWaitingTime, int avgTurnaroundTime, ArrayList<ProcessGraphData> processGraphData)
    {
        this.avgWaitingTime = avgWaitingTime;
        this.avgTurnaroundTime = avgTurnaroundTime;
        this.processGraphData = processGraphData;
    }
}