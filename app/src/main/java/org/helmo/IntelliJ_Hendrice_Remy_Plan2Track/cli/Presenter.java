package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

import java.util.Collection;

public class Presenter {

    public String presentPlanning(Planning planning){
        String name = planning.getName();
        String jobs = listJobs(planning.getJobs());
        return String.format("%s\n%s", name, jobs);
    }

    private String listJobs(Collection<Job> jobs){
        if(jobs.isEmpty()) return "(Aucune tâche)";
        return "";
    }
}
