package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;

import java.util.Collection;

/**
 * Définit l'interface de contrôleur de modification d'un montage.
 */
public interface EditPlanning {

    /**
     * Retourne le montage en cours.
     * @return le montage en cours
     */
    Planning getPlanning();

    /**
     * Retourne les modèles de vues des tâches du montage en cours.
     * @return les modèles de vues des tâches du montage en cours.
     */
    Collection<JobViewModel> getJobsViewModels();

    /**
     * Retourne les noms des tâches du montage en cours.
     * @return les noms des tâches du montage en cours.
     */
    Collection<String> getJobsNames();

    /**
     * Retourne le modèle de vue de la tâche correspondant au nom en paramètres.
     * @param name le nom de la tâche
     * @return le modèle de vue de la tâche correspondant au nom
     */
    JobViewModel getJobViewModel(String name);

    /**
     * Modifie le nom du montage en cours.
     * @param name le nouveau nom du montage
     */
    void editName(String name);

    /**
     * Retourne le contrôleur d'ajout de tâches.
     * @return le contrôleur d'ajout de tâches
     */
    AddJob getAddJobController();

    /**
     * Retourne le controleur de suppression de tâches
     * @return le controleur dee suppression de tâches
     */
    RemoveJob getRemoveJobController();

    /**
     * Retourne le controleur d'assignation des tâches
     * @return le controleur d'assignation des tâches
     */
    AssignJobs getAssignJobsController();
}
