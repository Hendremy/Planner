package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.List;
import java.util.StringJoiner;

/**
 * Définit le présentateur qui se charge de formatter les objets pour l'affichage en console.
 */
public class Presenter {

    public static Presenter singleton;

    /**
     * Retourne l'unique instance de cet objet et le crée s'il n'est pas encore créé.
     * @return l'unique instance de cet objet
     */
    public static Presenter getInstance(){
        if(singleton == null){
            singleton = new Presenter();
        }
        return singleton;
    }

    /**
     * Initialise le présentateur
     */
    private Presenter(){}

    /**
     * Formatte un montage en chaîne de caractères.
     * @param planning le montage à formatter
     * @return le montage formatté en chaîne de caractères
     */
    public String presentPlanning(Planning planning){
        String name = planning.getName();
        String jobs = listJobs(planning.getJobs());
        return String.format("\n%5s%s\n%5s%s"," ",name," ", jobs);
    }

    /**
     * Formatte une collection de tâches en chaîne de caractères.
     * @param jobs la collection de tâches à formatter
     * @return la collection de tâches formatté en chaîne de caractères
     */
    public String listJobs(Iterable<Job> jobs){
        if(!jobs.iterator().hasNext()) return "(Aucune tâche)";
        StringBuilder sb = new StringBuilder();
        for (var job : jobs) {
            sb.append("\n");
            sb.append(formatJob(job));
        }
        return sb.toString();
    }

    /**
     * Formatte une tâche en chaîne de caractères
     * @param job la tâche à formatter
     * @return la tâche formattée en chaine de caractères
     */
    private String formatJob(Job job){
        String name = job.getName();
        String description = formatDescription(job.getDescription());
        String duration = String.format("%dj",job.getDuration());
        String priorJobs = formatPriorJobs(job.getPriorJobs());
        return String.format("%s : %s %s Requis : %s", name, description, duration, priorJobs);
    }

    /**
     * Formatte la description d'une tâche.
     * @param description la description de la tâche à formatter
     * @return la description formattée en chaîne de caractères
     */
    private String formatDescription(String description){
        if(description == null || description.isEmpty() || description.isBlank()){
            return "/";
        }
        if(description.length() > 25){
            return String.format("%s...",description.substring(0,25));
        }
        return description;
    }

    /**
     * Formatte les tâches antérieures d'une tâche en chaine de caractères.
     * @param jobs les tâches antérieures d'une tâche à formatter
     * @return les tâches antérieures d'une tâche formattés en chaîne de caractères
     */
    private String formatPriorJobs(Iterable<Job> jobs){
        if(!jobs.iterator().hasNext()) return "-";
        StringJoiner sj = new StringJoiner(",");
        for (Job job : jobs) {
            sj.add(job.getName());
        }
        return sj.toString();
    }

    /**
     * Formatte la liste des chefs d'équipes en chaîne de caractères.
     * @param technicians la liste des chefs d'équipes à formatter
     * @return la liste des chefs d'équipes formattée en chaîne de caractères
     */
    public String formatTechnicians(List<TechnicianViewModel> technicians){
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(TechnicianViewModel tech : technicians){
            sb.append(String.format("%5s%d. %s\n"," ", count, tech.getFullName()));
            count++;
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Formatte la liste des tâches avec leur chef assigné en chaîne de caractères
     * @param jobs la liste des tâches avec leur chef assigné à formatter
     * @return la liste des tâches avec leur chef assigné formattée en chaîne de caractères
     */
    public String formatAssignJobs(Iterable<Job> jobs){
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

    /**
     * Formatte le chemin critique d'un montage en chaîne de caractères
     * @param criticalPath le chemin critique d'un montage à formatter
     * @return le chemin critique d'un montage formatté en chaîne de caractères
     */
    public String formatCriticalPath(List<PertTaskViewModel> criticalPath){
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
