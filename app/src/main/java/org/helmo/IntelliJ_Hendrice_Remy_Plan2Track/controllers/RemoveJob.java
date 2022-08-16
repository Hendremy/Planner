package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;

/**
 * Définit l'interface de controleur de suppression de tâche
 */
public interface RemoveJob {

    /**
     * Retourne le nombre de fois que la tâche correspondant au nom en paramètre est antérieure à d'autres.
     * @param name le nom de la tâche
     * @return le nombre de fois que la tâche correspondant au nom en paramètre est antérieure à d'autres.
     * @throws JobNotFoundException survient si le nom en paramètre ne correspond à aucune tâche du planning
     */
    int findJobOccurences(String name) throws JobNotFoundException;

    /**
     * Supprime la tâche dont le nom correspond à celui en paramètre
     * @param name le nom de la tâche
     * @throws JobNotFoundException survient si le nom en paramètre ne correspond à aucune tâche du planning
     */
    void removeJob(String name) throws JobNotFoundException;
}
