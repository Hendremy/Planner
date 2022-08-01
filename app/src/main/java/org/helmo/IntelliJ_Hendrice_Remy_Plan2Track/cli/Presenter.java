package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

import java.util.Collection;
import java.util.Iterator;

public class Presenter {

    public String presentPlanning(Planning planning){
        String name = planning.getName();
        String jobs = listJobs(planning.getJobs());
        return String.format("%s\n%s", name, jobs);
    }

    private String listJobs(Collection<Job> jobs){
        if(jobs.isEmpty()) return "(Aucune tâche)";
        StringBuilder sb = new StringBuilder();
        for (var job : jobs) {
            sb.append("\n");
            sb.append(formatJob(job));
        }
        return sb.toString();
    }

    private String formatJob(Job job){
        String name = job.getName();
        String description = formatDescription(job.getDescription());
        String duration = String.format("%dj",job.getDuration());
        String priorJobs = formatPriorJobs(job.getPriorJobs());
        return String.format("%s : %s %s Requis : %s", name, description, duration, priorJobs);
    }

    private String formatDescription(String description){
        if(description == null || description.isEmpty() || description.isBlank()){
            return "/";
        }
        if(description.length() > 25){
            return String.format("%s...",description.substring(0,25));
        }
        return description;
    }

    private String formatPriorJobs(Iterator<Job> jobs){
        if(!jobs.hasNext()) return "-";
        Job job;
        StringBuilder sb = new StringBuilder();
        while(jobs.hasNext()){
            job = jobs.next();
            sb.append(job.getName());
        }
        return sb.toString();
    }
}
