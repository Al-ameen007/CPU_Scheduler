import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {

    public ArrayList<Process> sortByShortestTime(ArrayList<Process> processes) {
        ArrayList<Process> temp = new ArrayList<>();
        temp = processes;
        Collections.sort(temp);
        return temp;
    }

    public void shortestJobFirst(ArrayList<Process> processes){
        ArrayList<Process> shortestJobProcesses = new ArrayList<Process>();
        shortestJobProcesses = sortByShortestTime(processes);
        for (int i = 0, n = processes.size(); i < n; i++){
            System.out.println(shortestJobProcesses.get(i).name);
        }
    }

    public void priorityScheduling(ArrayList<Process> processes){
        ArrayList<Process> shortestJobProcesses = new ArrayList<Process>();
        shortestJobProcesses = sortByShortestTime(processes);
        for (int i = 0, n = processes.size(); i < n; i++){
            System.out.println(shortestJobProcesses.get(i).name);
        }
    }
}
