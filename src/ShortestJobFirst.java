import java.util.ArrayList;
import java.util.Collections;

public class ShortestJobFirst {
    ArrayList<Process> processes;

    ShortestJobFirst(ArrayList<Process> processes){
        this.processes = processes;
    }

    public static ArrayList<Process> sortByShortestTime(ArrayList<Process> processes) {
        Collections.sort(processes);
        return processes;
    }

    public static void shortestJobFirst(ArrayList<Process> processes) {
        ArrayList<Process> shortestJobProcesses = new ArrayList<>(sortByShortestTime(processes));
        Collections.reverse(shortestJobProcesses);
        int n = processes.size(), time = 0; double totalWaitingTime = 0.0, totalTurnaroundTime = 0.0;
        ArrayList<Process> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < shortestJobProcesses.size(); j++) {
                if (shortestJobProcesses.get(j).arrivalTime <= time) {
                    shortestJobProcesses.get(j).waitingTime = time - shortestJobProcesses.get(j).arrivalTime;
                    shortestJobProcesses.get(j).turnaroundTime = shortestJobProcesses.get(j).waitingTime + shortestJobProcesses.get(j).burstTime;
                    totalWaitingTime += shortestJobProcesses.get(j).waitingTime;
                    totalTurnaroundTime += shortestJobProcesses.get(j).turnaroundTime;
                    time += shortestJobProcesses.get(j).burstTime;
                    if (shortestJobProcesses.contains(shortestJobProcesses.get(j))) {
                        output.add(shortestJobProcesses.get(j));
                    }
                    shortestJobProcesses.remove(shortestJobProcesses.get(j));
                    break;
                }
            }
        double averageWaitingTime = totalWaitingTime/output.size();
        double averageTurnaroundTime = totalTurnaroundTime/output.size();

        for (Process process : output) {
            System.out.println("Process name: " + process.name + " ,Process waiting time: " + process.waitingTime + " , Process turnaround time: " + process.turnaroundTime);
        }
        System.out.println("Average Waiting Time is: " + averageWaitingTime);
        System.out.println("Average Turnaround Time is: " + averageTurnaroundTime);
    }
}
