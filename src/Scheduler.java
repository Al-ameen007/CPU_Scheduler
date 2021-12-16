import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scheduler {

<<<<<<< Updated upstream
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
=======
    public static ArrayList<Process> sortByShortestTime(ArrayList<Process> processes) {
        processes.sort(Comparator.comparingInt((Process p) -> p.burstTime));
        return processes;
    }

    public static ArrayList<Process> sortByHighestPriority(ArrayList<Process> processes){
        processes.sort(Comparator.comparingInt((Process p) -> p.priorityNumber));
        return processes;
    }

    public static void shortestJobFirst(ArrayList<Process> processes) {
        ArrayList<Process> shortestJobProcesses = sortByShortestTime(processes);
        Collections.reverse(shortestJobProcesses);
        int n = processes.size(), time = 0; double TotalWaitingTime = 0.0;
        ArrayList<Process> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < shortestJobProcesses.size(); j++) {
                if (shortestJobProcesses.get(j).arrivalTime <= time) {
                    shortestJobProcesses.get(j).waitingTime = time - shortestJobProcesses.get(j).arrivalTime;
                    TotalWaitingTime += shortestJobProcesses.get(j).waitingTime;
                    time += shortestJobProcesses.get(j).burstTime;
                    if (shortestJobProcesses.contains(shortestJobProcesses.get(j))) {
                        output.add(shortestJobProcesses.get(j));
                    }
                    shortestJobProcesses.remove(shortestJobProcesses.get(j));
                    break;
                }
            }
        double averageWaitingTime = TotalWaitingTime/output.size();
        for (Process process : output) {
            System.out.println("Process name: " + process.name + " ,Process waiting time: " + process.waitingTime);
        }
        System.out.println("Average Waiting Time is: " + averageWaitingTime);
    }

    public static void priorityScheduling(ArrayList<Process> processes) {
        ArrayList<Process> highestPriorityJob = sortByHighestPriority(processes);
        int n = processes.size(), time = 0; double TotalWaitingTime = 0.0;
        ArrayList<Process> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < highestPriorityJob.size(); j++) {
                if (highestPriorityJob.get(j).arrivalTime <= time) {
                    highestPriorityJob.get(j).waitingTime = time - highestPriorityJob.get(j).arrivalTime;
                    TotalWaitingTime += highestPriorityJob.get(j).waitingTime;
                    time += highestPriorityJob.get(j).burstTime;
                    if (highestPriorityJob.contains(highestPriorityJob.get(j))) {
                        System.out.println(highestPriorityJob.get(j).name);
                        output.add(highestPriorityJob.get(j));
                    }
                    highestPriorityJob.remove(highestPriorityJob.get(j));
                    break;
                }
            }

        double averageWaitingTime = TotalWaitingTime/output.size();
        for (Process process : output) {
            System.out.println("Process name: " + process.name + " ,Process waiting time: " + process.waitingTime);
        }
        System.out.println("Average Waiting Time is: " + averageWaitingTime);
    }


    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>(3);
        Process p1 = new Process("p1", Color.red, 0, 4, 1);
        Process p2 = new Process("p2", Color.blue, 1, 4, 3);
        Process p3 = new Process("p3", Color.gray, 1, 6, 2);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        shortestJobFirst(processes);
        priorityScheduling(processes);
>>>>>>> Stashed changes
    }
}
