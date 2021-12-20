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
    void update_V1(){
        last_arrival_time = processes.get(processes.size()-1).arrivalTime;
        if (last_arrival_time > 10)
            v1 = last_arrival_time / 10;
        else
            v1 = 1;
    }
    void update_V2(){
        double hold = -1;
        for(ProcessAgat pg : processes){
            if(pg.burstTime > hold)
                hold = pg.burstTime;
        }
        if(hold > 10)
            v2 = hold / 10;
        else
            v2 = 1;
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
            if(processes.get(i).agatFactor < hold 
            && processes.get(i).arrivalTime < current_Time + (processes.get(index).quantum - processes.get(index).quantumFactor())){
                hold = processes.get(i).agatFactor;
                idx = i;
            }
        }
        // TODO if more than process got the same agatFactor we'll pick the lowest arrival time but is it Necessarly?
        if(idx == index) //you can't pick the last executed processes
            return -1; //No one is better in the agatFactor
        return idx;
    }
    public void agatS(){
        //The Vs computed before the execution
        int index = 0;
        update_V1();
        while(check()){
            update_V2();
            update_AgFactor();
            System.out.print(" " + current_Time + " ");
            processes.get(index).info();
            //System.out.print(processes.get(index).name);
            if(processes.get(index).quantumFactor() >= processes.get(index).burstTime){ //0.4Q > buresetTime => No need to work
                current_Time += processes.get(index).burstTime;
                processes.remove(index); //delete the process
                System.out.println(" " + current_Time + " ");
                continue;
            }
            current_Time += processes.get(index).quantumFactor(); //update the currentTime
            processes.get(index).burstTime -= processes.get(index).quantumFactor(); //update the burset time
            int otherIndex = better(index);
            if(otherIndex == -1){ //No one is better than me => continue working
                if(processes.get(index).burstTime > (processes.get(index).quantum - processes.get(index).quantumFactor())){
                    //if the remaining more than the remaining quantum
                    processes.get(index).burstTime -= (processes.get(index).quantum - processes.get(index).quantumFactor());
                    current_Time += (processes.get(index).quantum - processes.get(index).quantumFactor());
                    processes.get(index).quantum += 2;
                    ProcessAgat holder = processes.get(index);
                    processes.remove(index);
                    processes.add(holder);
                    System.out.println(" " + current_Time + " ");
                    continue;
                }else{
                    //The remaining quantum is enough
                    current_Time += processes.get(index).burstTime;
                    processes.remove(index);
                    System.out.println(" " + current_Time + " ");
                    continue;
                }
            }else{
                //some one is better than you
                //it'is not arrived betW 0, 0.4Q but it arrived betW 0.4Q, Q; Note currentTime is now 0.4Q
                if(!(processes.get(otherIndex).arrivalTime <= current_Time)){ //it is not already arrived, arrived during exec
                    if(processes.get(index).burstTime <= (processes.get(otherIndex).arrivalTime - current_Time)){
                        //if the remain quantum is enough
                        current_Time += (processes.get(index).burstTime);
                        processes.remove(index);
                        System.out.println(" " + current_Time + " ");
                        continue;
                    }
                    processes.get(index).burstTime -= (processes.get(otherIndex).arrivalTime - current_Time);
                    processes.get(index).quantum += (processes.get(otherIndex).arrivalTime - current_Time);
                    current_Time += (processes.get(otherIndex).arrivalTime - current_Time);
                }else{//it is already arrived only update the quanum
                    processes.get(index).quantum += (processes.get(index).quantum - processes.get(index).quantumFactor());
                }
                //adding to the top of the list
                ProcessAgat holder = processes.get(index);
                processes.set(index, processes.get(otherIndex)); 
                processes.remove(otherIndex);
                int flag = -1;
                for(int i = 0; i < processes.size(); i++){
                    if(processes.get(i).arrivalTime > current_Time){
                        flag = i;
                        i = processes.size();
                    }
                }
                if(flag == -1)
                    processes.add(holder); //TODO add it right
                else{
                    processes.add(holder);
                    for(int i = flag; i < processes.size(); i++){
                        ProcessAgat eat = processes.get(i);
                        processes.set(i, processes.get(processes.size() -1));
                        processes.set(processes.size()-1, eat);
                    }
                }
            }
            System.out.println(" " + current_Time + " ");
        }
    }
}
