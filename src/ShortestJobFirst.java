import java.util.ArrayList;
import java.util.Comparator;

public class ShortestJobFirst {
    private static int threshold = 0;
    private static int k = 0;
    static double avgWaitingTime = 0;
    static double avgTurnaroundTime = 0;
    static int time = 0;
    ArrayList<ProcessGraphData> graphData = new ArrayList<ProcessGraphData>();
    ArrayList<Process> processes;

    ShortestJobFirst(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public static ArrayList<Process> sortByShortestTime(ArrayList<Process> processes) {
        processes.sort(Comparator.comparingInt((Process p) -> p.dummyBurstTime));
        return processes;
    }

    public static ArrayList<Process> shortestJobFirst(ArrayList<Process> processes) {
        ArrayList<Process> shortestJobProcesses = new ArrayList<>(sortByShortestTime(processes));
        int n = processes.size(), totalBurst = 0;
        for (Process process : shortestJobProcesses) {
            totalBurst += process.burstTime;
        }
        while (k == 0) {
            for (Process process : shortestJobProcesses) {
                totalBurst += process.burstTime;
            }
            threshold = totalBurst - shortestJobProcesses.get(n - 1).burstTime;
            k++;
        }
        ArrayList<Process> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < shortestJobProcesses.size(); j++) {
                if (shortestJobProcesses.get(j).arrivalTime <= time) {
                    shortestJobProcesses.get(j).starved = time - shortestJobProcesses.get(j).arrivalTime > threshold;
                    shortestJobProcesses.get(j).waitingTime = time - shortestJobProcesses.get(j).arrivalTime;
                    shortestJobProcesses.get(j).turnaroundTime = shortestJobProcesses.get(j).waitingTime + shortestJobProcesses.get(j).burstTime;
                    time += shortestJobProcesses.get(j).burstTime;
                    if (shortestJobProcesses.contains(shortestJobProcesses.get(j))) {
                        output.add(shortestJobProcesses.get(j));
                    }
                    shortestJobProcesses.remove(shortestJobProcesses.get(j));
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
        avgWaitingTime = totalWaitingTime / output.size();
        avgTurnaroundTime = totalTurnaroundTime / output.size();

        for (Process process : output) {
            System.out.println("Process name: " + process.name + " ,Process waiting time: " + process.waitingTime + " , Process turnaround time: " + process.turnaroundTime);
        }
        System.out.println("Average Waiting Time is: " + avgWaitingTime);
        System.out.println("Average Turnaround Time is: " + avgTurnaroundTime);
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

    public static ArrayList<Process> solveStarve(ArrayList<Process> input) {
        input = new ArrayList<>(shortestJobFirst(input));
        int i = 0;
        while (isStarved(input) && i < 50) {
            for (Process process : input) {
                if (process.starved) {
                    process.dummyBurstTime -= 1;
                    break;
                }
            }
            i++;
            input = new ArrayList<>(shortestJobFirst(input));
        }
        display(input);
        return (input);
    }

    public void Schedule(ArrayList<Process> input) {
        input = solveStarve(input);
        for (int i = 0; i < input.size(); i++) {
            Process ps = processes.get(i);
            for (int j = ps.startTime; j < ps.startTime + ps.burstTime; j++){
                graphData.add(new ProcessGraphData(ps.name, ps.color));
            }
        }
    }

    public ScheduleData getScheduleData() {
        return new ScheduleData(avgWaitingTime, avgTurnaroundTime, graphData);
    }

}
