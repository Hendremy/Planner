package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JobViewModel {

    private final String name;
    private final String description;
    private final int duration;
    private final String techName;
    private final String techCode;
    private final Set<JobViewModel> priorJobs;

    public JobViewModel(Job job){
        this.name = job.getName();
        this.description = job.getDescription();
        this.duration = job.getDuration();
        this.techName = job.getTechnicianName();
        this.techCode = job.getTechnicianCode();
        this.priorJobs = initPriorJobs(job.getPriorJobs());
    }

    private JobViewModel(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.techName = null;
        this.techCode = null;
        this.priorJobs = null;
    }

    private Set<JobViewModel> initPriorJobs(Iterable<Job> priorJobs){
        Set<JobViewModel> priorJobSet = new HashSet<>();
        for(Job job : priorJobs){
            priorJobSet.add(new JobViewModel(job.getName(), job.getDescription(), job.getDuration()));
        }
        return priorJobSet;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getDuration(){
        return Integer.toString(duration);
    }

    public String getTechCode(){
        return techCode == null ? "non assigné" : techCode;
    }

    private String getTechName(){
        return techName == null ? "non assigné" : techName;
    }

    public Collection<JobViewModel> getPriorJobs(){
        return new HashSet<>(priorJobs);
    }

    @Override
    public String toString(){
        return String.format("%s - %d j - %s", name, duration, getTechName());
    }
}
