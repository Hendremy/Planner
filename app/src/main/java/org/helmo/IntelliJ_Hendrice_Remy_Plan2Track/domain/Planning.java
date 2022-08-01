package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Planning {

    private String name;
    private Collection<Job> jobs;

    public Planning(String name){
        this.name = name;
        this.jobs = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public Collection<Job> getJobs() {
        return jobs;
    }
}
