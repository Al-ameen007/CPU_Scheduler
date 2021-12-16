import java.util.ArrayList;

public class AgatScheduler {
    double last_arrival_time;
    double max_remaining_burst_time;
    ArrayList<ProcessAgat> processes;
    double v1;
    double v2;
    AgatScheduler(ArrayList<ProcessAgat> p){
        processes = p;
    }

    void update_statics(){
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

    public void agatS(){
        update_statics();
        int index = 0;
        //TODO the break condition
        while(true){
            index = (index + 1) % processes.size();

        }
    }
}
