import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighestPriority {
    ArrayList<Process> processes;

    HighestPriority(ArrayList<Process> processes){
        this.processes = processes;
    }

    public static ArrayList<Process> sortByHighestPriority(ArrayList<Process> processes){
        processes.sort(Comparator.comparingInt((Process p) -> p.priorityNumber));
        return processes;
    }

    public static void priorityScheduling(ArrayList<Process> processes) {
        ArrayList<Process> highestPriorityJob = new ArrayList<>(sortByHighestPriority(processes));
        Collections.reverse(highestPriorityJob);
        int n = processes.size(), time = 0; double totalWaitingTime = 0.0, totalTurnaroundTime = 0.0;
        ArrayList<Process> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < highestPriorityJob.size(); j++) {
                if (highestPriorityJob.get(j).arrivalTime <= time) {
                    highestPriorityJob.get(j).waitingTime = time - highestPriorityJob.get(j).arrivalTime;
                    highestPriorityJob.get(j).turnaroundTime = highestPriorityJob.get(j).waitingTime + highestPriorityJob.get(j).burstTime;
                    totalWaitingTime += highestPriorityJob.get(j).waitingTime;
                    totalTurnaroundTime += highestPriorityJob.get(j).turnaroundTime;
                    time += highestPriorityJob.get(j).burstTime;
                    if (highestPriorityJob.contains(highestPriorityJob.get(j))) {
                        output.add(highestPriorityJob.get(j));
                    }
                    highestPriorityJob.remove(highestPriorityJob.get(j));
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
