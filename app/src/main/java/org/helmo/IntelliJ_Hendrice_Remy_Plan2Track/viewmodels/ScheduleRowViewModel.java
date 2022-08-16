package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlannedJob;

/**
 * Définit le modèle de vue d'une rangée du programme de motnage.
 */
public class ScheduleRowViewModel {

    private final PlannedJob plannedJob;

    /**
     * Initialise le modèle de vue à partir d'une tâche plannifiée.
     * @param plannedJob la tâche plannifiée
     */
    public ScheduleRowViewModel(PlannedJob plannedJob){
        this.plannedJob = plannedJob;
    }

    /**
     * Retourne le nom de la tâche.
     * @return le nom de la tâche
     */
    public String getTaskName(){
        return plannedJob.getName();
    }

    /**
     * Retourne le nom du chef d'équipe assigné.
     * @return le nom du chef d'équipe assigné
     */
    public String getTechName(){
        return plannedJob.getTechName();
    }

    /**
     * Retourne la date de début de la tâche.
     * @return la date de début de la tâche
     */
    public String getStartDate(){
        return plannedJob.getStartDate().toString();
    }
}
