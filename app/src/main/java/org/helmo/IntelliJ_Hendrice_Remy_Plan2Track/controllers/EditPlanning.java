package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;

import java.util.Collection;

/**
 * D�finit l'interface de contr�leur de modification d'un montage.
 */
public interface EditPlanning {

    /**
     * Retourne le montage en cours.
     * @return le montage en cours
     */
    Planning getPlanning();

    /**
     * Retourne les mod�les de vues des t�ches du montage en cours.
     * @return les mod�les de vues des t�ches du montage en cours.
     */
    Collection<JobViewModel> getJobsViewModels();

    /**
     * Retourne les noms des t�ches du montage en cours.
     * @return les noms des t�ches du montage en cours.
     */
    Collection<String> getJobsNames();

    /**
     * Retourne le mod�le de vue de la t�che correspondant au nom en param�tres.
     * @param name le nom de la t�che
     * @return le mod�le de vue de la t�che correspondant au nom
     */
    JobViewModel getJobViewModel(String name);

    /**
     * Modifie le nom du montage en cours.
     * @param name le nouveau nom du montage
     */
    void editName(String name);

    /**
     * Retourne le contr�leur d'ajout de t�ches.
     * @return le contr�leur d'ajout de t�ches
     */
    AddJob getAddJobController();

    /**
     * Retourne le controleur de suppression de t�ches
     * @return le controleur dee suppression de t�ches
     */
    RemoveJob getRemoveJobController();

    /**
     * Retourne le controleur d'assignation des t�ches
     * @return le controleur d'assignation des t�ches
     */
    AssignJobs getAssignJobsController();
}
