import java.util.ArrayList;
import java.util.Comparator;

public class HighestPriority {
    ArrayList<Process> processes;

    HighestPriority(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public static ArrayList<Process> sortByHighestPriority(ArrayList<Process> processes) {
        processes.sort(Comparator.comparingInt((Process p) -> p.priorityNumber));
        return processes;
    }

    public static ArrayList<Process> priorityScheduling(ArrayList<Process> processes, int contextSwitch) {
        ArrayList<Process> highestPriorityJob = new ArrayList<>(sortByHighestPriority(processes));
        int n = processes.size(), time = 0, totalBurst = 0;
        for (Process process : highestPriorityJob) {
            totalBurst += process.burstTime;
        }
        ArrayList<Process> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < highestPriorityJob.size(); j++) {
                if (highestPriorityJob.get(j).arrivalTime <= time) {
                    highestPriorityJob.get(j).starved = time - highestPriorityJob.get(j).arrivalTime > 11;
                    highestPriorityJob.get(j).waitingTime = time - highestPriorityJob.get(j).arrivalTime;
                    highestPriorityJob.get(j).turnaroundTime = highestPriorityJob.get(j).waitingTime + highestPriorityJob.get(j).burstTime;
                    time += highestPriorityJob.get(j).burstTime + contextSwitch;
                    if (highestPriorityJob.contains(highestPriorityJob.get(j))) {
                        output.add(highestPriorityJob.get(j));
                    }
                    highestPriorityJob.remove(highestPriorityJob.get(j));
                    break;
                }
            }
        return output;
    }

    public static void display(ArrayList<Process> output) {
        double totalWaitingTime = 0.0, totalTurnaroundTime = 0.0;
        for (Process value : output) {
            totalWaitingTime += value.waitingTime;
            totalTurnaroundTime += value.turnaroundTime;
        }
        double averageWaitingTime = totalWaitingTime / output.size();
        double averageTurnaroundTime = totalTurnaroundTime / output.size();

        for (Process process : output) {
            System.out.println("Process name: " + process.name + " ,Process waiting time: " + process.waitingTime + " , Process turnaround time: " + process.turnaroundTime);
        }
        System.out.println("Average Waiting Time is: " + averageWaitingTime);
        System.out.println("Average Turnaround Time is: " + averageTurnaroundTime);
    }

    public static boolean isStarved(ArrayList<Process> processes) {
        boolean flag = false;
        for (int i = 0, n = processes.size() - 1; i <= n; i++) {
            if (processes.get(i).starved) {
                flag = true;
                break;
            }
        }
        return flag && (processes.size() > 5);
    }

    public static void solveStarve(ArrayList<Process> input, int contextSwitch) {
        input = new ArrayList<>(priorityScheduling(input, contextSwitch));
        int i = 0;
        while (isStarved(input) && i < 50) {
            for (Process process : input) {
                if (process.starved) {
                    process.priorityNumber -= 1;
                    break;
                }
            }
            i++;
            input = new ArrayList<>(priorityScheduling(input, contextSwitch));
        }
        display(input);
    }
}
