package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

/**
 * D�finit l'interface de contr�leur d'ajout de t�che.
 */
public interface AddJob {

    /**
     * Ajoute une t�che au montage en cours.
     * @param name le nom de la t�che
     * @param description la description de la t�che
     * @param duration la dur�e de la t�che
     * @param priorJobs les noms des t�ches ant�rieures � la t�che � ajouter
     */
    void addJobToPlanning(String name, String description, int duration, Iterable<String> priorJobs);
}
