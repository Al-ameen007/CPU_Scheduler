import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {

    public static ArrayList<Process> sortByShortestTime(ArrayList<Process> processes) {
        Collections.sort(processes);
        return processes;
    }

    public static void shortestJobFirst(ArrayList<Process> processes) {
        ArrayList<Process> shortestJobProcesses = sortByShortestTime(processes);
        Collections.reverse(shortestJobProcesses);
        int n = processes.size(), time = 0, averageWaitingTime = 0;
        ArrayList<Process> output = new ArrayList<Process>(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < shortestJobProcesses.size(); j++) {
                if (shortestJobProcesses.get(j).arrivalTime <= time) {
                    shortestJobProcesses.get(j).waitingTime = time - shortestJobProcesses.get(j).arrivalTime;
                    averageWaitingTime += shortestJobProcesses.get(j).waitingTime;
                    time += shortestJobProcesses.get(j).burstTime;
                    if (shortestJobProcesses.contains(shortestJobProcesses.get(j))) {
                        output.add(shortestJobProcesses.get(j));
                    }
                    shortestJobProcesses.remove(shortestJobProcesses.get(j));
                    break;
                }
            }
        }

        for (Process process : output) {
            System.out.println("Process name: " + process.name + " ,Process waiting time: " + process.waitingTime);
        }
        System.out.println("Average Waiting Time is: " + averageWaitingTime/output.size());
    }


    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>(3);
        Process p1 = new Process("p1", Color.red, 0, 4, 10);
        Process p2 = new Process("p2", Color.blue, 1, 4, 10);
        Process p3 = new Process("p3", Color.gray, 3, 2, 10);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        shortestJobFirst(processes);
    }
}
