package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Technician;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringJoiner;

public class Presenter {

    public String presentPlanning(Planning planning){
        String name = planning.getName();
        String jobs = listJobs(planning.getJobs());
        return String.format("\n%5s%s\n%5s%s"," ",name," ", jobs);
    }

    public String listJobs(Iterable<Job> jobs){
        if(!jobs.iterator().hasNext()) return "(Aucune tâche)";
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

    private String formatPriorJobs(Iterable<Job> jobs){
        if(!jobs.iterator().hasNext()) return "-";
        StringJoiner sj = new StringJoiner(",");
        for (Job job : jobs) {
            sj.add(job.getName());
        }
        return sj.toString();
    }

    public String listTechnicians(Iterable<Technician> technicians){
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(Technician tech : technicians){
            sb.append(String.format("%5s%d. %s\n"," ", count, tech.getFullName()));
            count++;
        }
        sb.append("\n");
        return sb.toString();
    }

    public String displayAssignedJobs(Iterable<Job> jobs){
        StringBuilder sb = new StringBuilder();
        for(Job job : jobs){
            String assignedTo = job.getTechnicianName();
            if(assignedTo == null){
                assignedTo = "tâche non assignée";
            }
            sb.append(String.format("%5s%s: %s\n"," ", job.getName(), assignedTo));
        }
        return sb.toString();
    }
}
