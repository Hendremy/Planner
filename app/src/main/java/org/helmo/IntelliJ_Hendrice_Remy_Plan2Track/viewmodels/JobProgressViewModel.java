package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobProgress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * D�finit le mod�le de vue de l'avancement d'une t�che.
 */
public class JobProgressViewModel {
    private final JobProgress jobProgress;
    private final DateTimeFormatter dateTimeFormatter;

    /**
     * Initialise le mod�le de vue � partir de l'avancement d'une t�che.
     * @param jobProgress l'avancement d'une t�che
     */
    public JobProgressViewModel(JobProgress jobProgress){
        this.jobProgress = jobProgress;
        this.dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    /**
     * Retourne le nom de la t�che.
     * @return le nom de la t�che
     */
    public String getName(){
        return jobProgress.getName();
    }

    /**
     * Retourne la date de d�but pr�vue.
     * @return la date de d�but pr�vue
     */
    public String getExpectedStartDate(){
        return formatDate(jobProgress.getExpectedStart());
    }

    /**
     * Retourne la date de fin pr�vue.
     * @return la date de fin pr�vue
     */
    public String getExpectedEndDate(){
        return formatDate(jobProgress.getExpectedEnd());
    }

    /**
     * Retourne la date de d�but r�elle.
     * @return la date de d�but r�elle
     */
    public String getActualStartDate(){
        return formatDate(jobProgress.getActualStart());
    }

    /**
     * Retourne la date de fin r�elle.
     * @return la date de fin r�elle
     */
    public String getActualEndDate(){
        return formatDate(jobProgress.getActualEnd());
    }

    /**
     * Retourne le statut de la t�che.
     * @return le statut de la t�che
     */
    public String getStatus(){
        return jobProgress.getStatus().getRepresentation();
    }

    /**
     * Retourne l'�tat de retard de la t�che.
     * @return l'�tat de retard de la t�che
     */
    public String getIsOnTime(){
        return jobProgress.isOnTime() ? "" : "En retard !";
    }

    /**
     * Formate la date.
     * @param date la date � formatter
     * @return la date formatt�e
     */
    private String formatDate(LocalDate date){
        return date.format(dateTimeFormatter);
    }
}
