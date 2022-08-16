package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * Définit l'avancement d'une tâche qui a été publiée.
 */
public class JobProgress {
    private final String name;
    private final TimeReport timeReport;

    /**
     * Initialise le nom de la tâche et ses échéances attendues & réelles
     * @param name le nom de la tâche
     * @param timeReport les échéances attendues & réelles
     */
    public JobProgress(String name, TimeReport timeReport){
        this.name = name;
        this.timeReport = timeReport;
    }

    /**
     * Retourne le nom de la tâche
     * @return le nom de la tâche
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la date de début prévue.
     * @return la date de début prévue
     */
    public LocalDate getExpectedStart(){
        return timeReport.getExpectedStart();
    }

    /**
     * Retourne la date de fin prévue.
     * @return la date de fin prévue.
     */
    public LocalDate getExpectedEnd(){
        return timeReport.getExpectedEnd();
    }

    /**
     * Retourne la date de début réelle.
     * @return la date de début réelle
     */
    public LocalDate getActualStart(){
        return timeReport.getActualStart();
    }

    /**
     * Retourne la date de fin réelle.
     * @return la date de fin relle
     */
    public LocalDate getActualEnd(){
        return timeReport.getActualEnd();
    }

    /**
     * Retourne le statut de la tâche
     * @return le statut de la tâche
     */
    public Status getStatus(){
        return timeReport.getStatus();
    }

    /**
     * Retourne vrai si la tâche est dans les temps, sinon faux.
     * @return vrai si la tâche est dans les temps, sinon faux
     */
    public boolean isOnTime(){
        return timeReport.isOnTime();
    }
}
