package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;

/**
 * D�finit l'interface de controleur de suppression de t�che
 */
public interface RemoveJob {

    /**
     * Retourne le nombre de fois que la t�che correspondant au nom en param�tre est ant�rieure � d'autres.
     * @param name le nom de la t�che
     * @return le nombre de fois que la t�che correspondant au nom en param�tre est ant�rieure � d'autres.
     * @throws JobNotFoundException survient si le nom en param�tre ne correspond � aucune t�che du planning
     */
    int findJobOccurences(String name) throws JobNotFoundException;

    /**
     * Supprime la t�che dont le nom correspond � celui en param�tre
     * @param name le nom de la t�che
     * @throws JobNotFoundException survient si le nom en param�tre ne correspond � aucune t�che du planning
     */
    void removeJob(String name) throws JobNotFoundException;
}
