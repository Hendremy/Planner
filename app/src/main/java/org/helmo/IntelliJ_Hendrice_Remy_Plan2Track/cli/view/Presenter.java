package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.List;
import java.util.StringJoiner;

public class Presenter {

    public static Presenter singleton;

    public static Presenter getInstance(){
        if(singleton == null){
            singleton = new Presenter();
        }
        return singleton;
    }

    private Presenter(){}

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

    public String listTechnicians(List<TechnicianViewModel> technicians){
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(TechnicianViewModel tech : technicians){
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

    public String displayCriticalPath(List<PertTaskViewModel> criticalPath){
        if(criticalPath.isEmpty()) return "Aucune tâche critique";
        StringBuilder sb = new StringBuilder();
        PertTaskViewModel[] taskArray = criticalPath.toArray(PertTaskViewModel[]::new);
        sb.append("Tâches critiques :");
        int i;
        for(i = 0; i < taskArray.length - 1; ++i){
            PertTaskViewModel first = taskArray[i];
            PertTaskViewModel following = taskArray[i+1];
            sb.append(String.format("\n - %s, requise pour %s", first.getName(), following.getName()));
        }
        PertTaskViewModel last = taskArray[i];
        sb.append(String.format("\n - %s", last.getName()));
        return sb.toString();
    }
}
