package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.ScheduleGenerator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

/**
 * Définit l'interface du contrôleur de gestion de montage
 */
public interface ManagePlanning {

    /**
     * Crée un nouveau montage avec le nom en paramètres
     * @param name le nom du nouveau montage
     */
    void createPlanning(String name);

    /**
     * Retourne le montage en cours s'il existe, sinon null.
     * @return le montage en cours s'il existe, sinon null
     */
    Planning getPlanning();

    /**
     * Retourne l'objet de stockage de montage.
     * @return l'objet de stockage de montage.
     */
    PlanningRepository getRepository();

    /**
     * Retourne le plannificateur de montage.
     * @return le plannificateur de montage
     */
    PertSchedulePlanner getSchedulePlanner();

    /**
     * Retourne le controleur de modification du montage.
     * @return le controleur de modification du montage
     */
    EditPlanning getEditPlanningController();

    /**
     * Retourne le controleur d'ajout de tâche.
     * @return le controleur d'ajout de tâche
     */
    AddJob getAddJobController();

    /**
     * Retourne le controleur de suppression de tâche.
     * @return le controleur de suppression de tâche
     */
    RemoveJob getRemoveJobController();

    /**
     * Retourne le controleur d'assignation de tâche.
     * @return le controleur d'assignation de tâches.
     */
    AssignJobs getAssignJobsController();

    /**
     * Retourne le controleur de plannification de montage.
     * @return le controleur de plannification de montage.
     */
    PlanSchedule getPlanScheduleController();

    /**
     * Retourne l'objet générateur de programme de montage.
     * @return l'objet générateur de programme de montage.
     */
    ScheduleGenerator getScheduleGenerator();

}
