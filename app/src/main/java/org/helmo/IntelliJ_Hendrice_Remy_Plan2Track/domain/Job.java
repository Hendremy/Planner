package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo.PertCandidate;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Job implements PertCandidate<Job> {

    private final String name;
    private final String description;
    private final int duration;
    private final Map<String,Job> priorJobs;
    private Technician technician;

    public Job(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.priorJobs = new HashMap<>();
        this.technician = null;
    }

    public Job(String name){
        this(name, "",1);
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }

    public String getTechnicianName(){
        return technician == null ? null : technician.getFullName();
    }

    public void setTechnician(Technician technician){
        this.technician = technician;
    }


    @Override
    public boolean hasPredecessor(Job job){
        return hasPredecessor(job.getName());
    }

    private boolean hasPredecessor(String name){
        return priorJobs.containsKey(name);
    }


    @Override
    public void addPredecessor(Job job){
        if(job != null && (!hasPredecessor(job) || !job.getName().equals(this.name))){
            priorJobs.put(job.getName(), job);
        }
    }

    @Override
    public Iterable<Job> getPredecessors(){
        return new ArrayList<>(priorJobs.values());
    }

    @Override
    public boolean hasPredecessors(){
        return priorJobs.size() > 0;
    }

    @Override
    public void removePredecessor(Job job){
        if(job != null && hasPredecessor(job)){
            priorJobs.remove(job.getName());
        }
    }

}
