package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobProgress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Définit le modèle de vue de l'avancement d'une tâche.
 */
public class JobProgressViewModel {
    private final JobProgress jobProgress;
    private final DateTimeFormatter dateTimeFormatter;

    /**
     * Initialise le modèle de vue à partir de l'avancement d'une tâche.
     * @param jobProgress l'avancement d'une tâche
     */
    public JobProgressViewModel(JobProgress jobProgress){
        this.jobProgress = jobProgress;
        this.dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    /**
     * Retourne le nom de la tâche.
     * @return le nom de la tâche
     */
    public String getName(){
        return jobProgress.getName();
    }

    /**
     * Retourne la date de début prévue.
     * @return la date de début prévue
     */
    public String getExpectedStartDate(){
        return formatDate(jobProgress.getExpectedStart());
    }

    /**
     * Retourne la date de fin prévue.
     * @return la date de fin prévue
     */
    public String getExpectedEndDate(){
        return formatDate(jobProgress.getExpectedEnd());
    }

    /**
     * Retourne la date de début réelle.
     * @return la date de début réelle
     */
    public String getActualStartDate(){
        return formatDate(jobProgress.getActualStart());
    }

    /**
     * Retourne la date de fin réelle.
     * @return la date de fin réelle
     */
    public String getActualEndDate(){
        return formatDate(jobProgress.getActualEnd());
    }

    /**
     * Retourne le statut de la tâche.
     * @return le statut de la tâche
     */
    public String getStatus(){
        return jobProgress.getStatus().getRepresentation();
    }

    /**
     * Retourne l'état de retard de la tâche.
     * @return l'état de retard de la tâche
     */
    public String getIsOnTime(){
        return jobProgress.isOnTime() ? "" : "En retard !";
    }

    /**
     * Formate la date.
     * @param date la date à formatter
     * @return la date formattée
     */
    private String formatDate(LocalDate date){
        return date.format(dateTimeFormatter);
    }
}
