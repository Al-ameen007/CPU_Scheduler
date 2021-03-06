import java.awt.*;
import java.util.ArrayList;

public class ShortestRemainingTime {

    private ArrayList<Process> processes;
    private ArrayList<Process> processesCompleted;
    private ArrayList<String> output;
    private ArrayList<ProcessGraphData> graphData = new ArrayList<ProcessGraphData>();
    private boolean shouldSolveStarvation = false;

    double avgWaitingTime = 0;
    double avgTurnaroundTime = 0;
    int sumWaiting = 0;
    int sumTurnaround = 0;
    int minRemainingTime;
    int minIndex;

    ShortestRemainingTime (ArrayList<Process> processes) {
        this.processes = new ArrayList<>();
        for (Process p : processes ) {
            this.processes.add(new Process(p));
        }
        processesCompleted = new ArrayList<Process>();
        System.out.println(this.processes.toString());
        output = new ArrayList<String>();
        minRemainingTime = processes.get(0).remainingTime;
        minIndex = 0;
    }

    public void setStarvation(boolean shouldSolveStarvation)
    {
        this.shouldSolveStarvation = shouldSolveStarvation;
    }

    private int starvationSteps = -1;
    private int starvedIndex = -1;

    private int getMinRemainingTime(int currentTime)
    {
        boolean foundValidProcess = false;

        if(starvationSteps >= 0 && shouldSolveStarvation) {
            starvationSteps--;
            return starvedIndex;
        }

        for(int i = 0; i < processes.size(); i++) {
            Process ps = processes.get(i);

            if(ps.arrivalTime > currentTime)
                continue;

            foundValidProcess = true;

            // Check if process starved, if so, give it some steps to be pushed in front
            if(shouldSolveStarvation) {
                if (currentTime - ps.arrivalTime >= 5 && ps.remainingTime > 6) {
                    starvedIndex = i;
                    starvationSteps = 2;
                    minIndex = starvedIndex;
                }
            }

            if (ps.remainingTime < minRemainingTime)
            {
                minRemainingTime = ps.remainingTime;
                minIndex = i;
            }
            else if(ps.remainingTime == minRemainingTime)
            {
                if(ps.arrivalTime < processes.get(minIndex).arrivalTime) {
                    minRemainingTime = ps.remainingTime;
                    minIndex = i;
                }
                else if(ps.arrivalTime ==  processes.get(minIndex).arrivalTime)
                {
                    if(i < minIndex) {
                        minRemainingTime = ps.remainingTime;
                        minIndex = i;
                    }
                }
            }

        }

        if(!foundValidProcess)
            return -1;

        return minIndex;
    }


    public void Schedule ()
    {
        int time = 0;
        while(!processes.isEmpty())
        {
            // At each iteration, we are going to check whether there's a process with less remainingTimeIsWaiting
            int i = getMinRemainingTime(time);

            if(i == -1) {
                time++;
                output.add("null");
                graphData.add(new ProcessGraphData("NULL", Color.gray));
                continue;
            }

            Process ps = processes.get(i);
            graphData.add(new ProcessGraphData(ps.name, ps.color));

            ps.remainingTime -= 1;
            output.add(ps.name);

            time++;

            if(ps.remainingTime <= 0) {
                minRemainingTime = processes.get(0).remainingTime;
                minIndex = 0;
                ps.turnaroundTime = time - ps.arrivalTime;
                ps.waitingTime = ps.turnaroundTime - ps.burstTime;
                sumWaiting += ps.waitingTime;
                sumTurnaround += ps.turnaroundTime;
                processes.remove(ps);
                processesCompleted.add(ps);
            }
        }

        displayOutput();
    }

    public void displayOutput()
    {
        for(Process p : processesCompleted)
        {
            System.out.println("Process name: " + p.name + " ,Process waiting time: " + p.waitingTime + " , Process turnaround time: " + p.turnaroundTime);
        }

        int n = processesCompleted.size();
        avgWaitingTime = sumWaiting / n;
        avgTurnaroundTime =  sumTurnaround / n;
        System.out.println("Average Waiting Time is: " + avgWaitingTime);
        System.out.println("Average Turnaround Time is: " + avgTurnaroundTime);
    }

    public ScheduleData getScheduleData ()
    {
        return new ScheduleData(avgWaitingTime, avgTurnaroundTime, graphData);
    }
}
