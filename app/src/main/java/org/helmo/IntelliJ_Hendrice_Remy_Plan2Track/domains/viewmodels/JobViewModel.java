package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

public class JobViewModel {

    private final String name;
    private final String description;
    private final int duration;
    private final String techName;
    private final String techCode;

    public JobViewModel (Job job){
        this.name = job.getName();
        this.description = job.getDescription();
        this.duration = job.getDuration();
        this.techName = job.getTechnicianName();
        this.techCode = job.getTechnicianCode();
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

    @Override
    public String toString(){
        return String.format("%s - %d j - %s", name, duration, getTechName());
    }
}
