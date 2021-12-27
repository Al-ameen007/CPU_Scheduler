import java.util.ArrayList;

public class ShortestRemainingTime {

    private ArrayList<Process> processes;
    int minRemainingTime;
    int minIndex;

    ShortestRemainingTime (ArrayList<Process> processes) {
        this.processes = processes;
        minRemainingTime = processes.get(0).remainingTime;
        minIndex = 0;
    }

    private int getMinRemainingTime(int currentTime)
    {
        boolean foundValidProcess = false;

        for(int i = 0; i < processes.size(); i++) {
            // Take inconsideration arrival time
            Process ps = processes.get(i);

            if(ps.arrivalTime > currentTime)
                continue;

            foundValidProcess = true;

            //System.out.println(i + ". T: " + currentTime + " " + (ps.remainingTime <= minRemainingTime )+ " R1 : " + ps.remainingTime + " R2: " + minRemainingTime);

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

            //System.out.print("Time: " + time + " ");

            if(i == -1) {
                time++;
                continue;
            }

            Process ps = processes.get(i);
            ps.remainingTime -= 1;
            //System.out.println("Name: " + ps.name + ", Remaining: " + ps.remainingTime + ", Arrival: " + ps.arrivalTime);
            System.out.print(ps.name + " ");

            if(ps.remainingTime <= 0) {
                minRemainingTime = processes.get(0).remainingTime;
                minIndex = 0;
                processes.remove(ps);
            }

            time++;
        }
    }
}
