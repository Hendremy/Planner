package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

/**
 * Définit le modèle de vue d'une tâche PERT.
 */
public class PertTaskViewModel {

    private final String name;
    private final int duration;

    /**
     * Initialise le modèle de vue de la tâche PERT.
     * @param task la tâche PERT
     */
    public PertTaskViewModel (PertTask task){
        this.name = task.getName();
        this.duration = task.getDuration();
    }

    /**
     * Retourne le nom de la tâche.
     * @return le nom de la tâche
     */
    public String getName(){
        return name;
    }

    /**
     * Retourne la représentation en chaine de caractères de la tâche.
     * @return la représentation en chaine de caractères de la tâche
     */
    @Override
    public String toString(){
        return String.format("%s - %d j", name, duration);
    }
}
