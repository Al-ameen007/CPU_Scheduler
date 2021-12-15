import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Process implements Comparable<Process>{
    String name;
    Color color;
    int arrivalTime;
    int burstTime;
    int priorityNumber;

    Process(String name, Color color, int arrivalTime, int burstTime ,int priorityNumber){
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
    }
    public int compareTo(Process comparesTo) {
        int compareShortestTime = ((Process) comparesTo).burstTime;
        return compareShortestTime - this.burstTime;
    }



}
