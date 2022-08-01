package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.Collection;
import java.util.Iterator;

public class Job {

    private String name;
    private String description;
    private int duration;
    private Collection<Job> priorJobs;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }

    public Iterator<Job> getPriorJobs(){
        return priorJobs.iterator();
    }
}
