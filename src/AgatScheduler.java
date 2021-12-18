import java.util.ArrayList;

public class AgatScheduler {
    double last_arrival_time;
    double max_remaining_burst_time;
    double current_Time;
    ArrayList<ProcessAgat> processes; //all the processes at the ready queue
    double v1;
    double v2;
    AgatScheduler(ArrayList<ProcessAgat> p){
        processes = p;
        current_Time = 0;

    }
    Boolean check(){
        for(ProcessAgat ag : processes){
            if(ag.burstTime > 0)
                return true;
        }
        return false;
    }
    void update_Vs(){
        last_arrival_time = processes.get(processes.size()-1).arrivalTime;
        max_remaining_burst_time = -1;
        for(ProcessAgat pg: processes){
            if(pg.burstTime > max_remaining_burst_time){
                max_remaining_burst_time = pg.burstTime;
            }
        }
        if (last_arrival_time > 10){
            v1 = last_arrival_time / 10;
        }else{
            v1 = 1;
        }
        if(max_remaining_burst_time > 10){
            v2 = max_remaining_burst_time / 10;
        }else{
            v2 = 1;
        }
    }
    void update_AgFactor(){
        for(ProcessAgat pr: processes){
            pr.update_agate(this);
        }
    }
    int better(int index){
        double hold = 1e9;
        int idx = -1;
        for(int i = 0; i < processes.size();i++){
            if(processes.get(i).agatFactor < hold){
                hold = processes.get(i).agatFactor;
                idx = i;
            }
        }
        if(idx == index)
            return -1; //No one is better in the agatFactor
        return idx;
    }
    public void agatS(){
        int index = 0;
        while(check()){
            if(processes.get(index).quantumFactor >= processes.get(index).burstTime){ //0.4Q > buresetTime => No need to work
                current_Time += processes.get(index).burstTime;
                processes.remove(index); //delete the process
                continue;
            }
            current_Time += processes.get(index).quantumFactor; //update the currentTime
            processes.get(index).burstTime -= processes.get(index).quantumFactor; //update the burset time
            update_Vs();
            update_AgFactor();
            int otherIndex = better(index);
            if(otherIndex != -1){
                if(processes.get(index).burstTime > (processes.get(index).quantum - processes.get(index).quantumFactor)){
                    processes.get(index).burstTime -= (processes.get(index).quantum - processes.get(index).quantumFactor);
                    current_Time += (processes.get(index).quantum - processes.get(index).quantumFactor);
                    processes.get(index).quantum += 2;
                    ProcessAgat holder = processes.get(index);
                    processes.remove(index);
                    processes.add(holder);
                    continue;
                }else if(processes.get(index).burstTime <= (processes.get(index).quantum - processes.get(index).quantumFactor)){
                    current_Time += processes.get(index).burstTime;
                    processes.remove(index);
                }
            }else{
                //some one is better than you;
                //TODO update the quantum here
                ProcessAgat holder = processes.get(index);
                processes.set(index, processes.get(otherIndex));
                processes.add(holder);
                continue;
            }
        }
    }
}
