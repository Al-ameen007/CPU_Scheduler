import java.sql.Array;
import java.util.ArrayList;

public class ShortestRemainingTime {

    private ArrayList<Process> processes;
    private ArrayList<Process> processesCompleted;
    private ArrayList<String> output;
    private boolean shouldSolveStarvation = false;
    int sumWaiting = 0;
    int sumTurnaround = 0;
    int minRemainingTime;
    int minIndex;

    ShortestRemainingTime (ArrayList<Process> processes) {
        this.processes = processes;
        processesCompleted = new ArrayList<Process>();
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
                continue;
            }

            Process ps = processes.get(i);
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
        System.out.print("Execution Order: ");
        for (String s: output) {
            System.out.print(s + " ");
        }

        System.out.println();

        for(Process p : processesCompleted)
        {
            System.out.print("Name: " + p.name + ". Waiting Time: " + p.waitingTime + ". Turnaround: " + p.turnaroundTime + "\n");
        }

        int n = processesCompleted.size();
        System.out.println("Avg Waiting Time: " + sumWaiting / n);
        System.out.println("Avg Turnaround Time: " + sumTurnaround / n);
    }
}
