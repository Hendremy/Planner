package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

/**
 * Définit l'interface de contrôleur d'ajout de tâche.
 */
public interface AddJob {

    /**
     * Ajoute une tâche au montage en cours.
     * @param name le nom de la tâche
     * @param description la description de la tâche
     * @param duration la durée de la tâche
     * @param priorJobs les noms des tâches antérieures à la tâche à ajouter
     */
    void addJobToPlanning(String name, String description, int duration, Iterable<String> priorJobs);
}
