package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.ScheduleGenerator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

/**
 * D�finit l'interface du contr�leur de gestion de montage
 */
public interface ManagePlanning {

    /**
     * Cr�e un nouveau montage avec le nom en param�tres
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
     * Retourne le controleur d'ajout de t�che.
     * @return le controleur d'ajout de t�che
     */
    AddJob getAddJobController();

    /**
     * Retourne le controleur de suppression de t�che.
     * @return le controleur de suppression de t�che
     */
    RemoveJob getRemoveJobController();

    /**
     * Retourne le controleur d'assignation de t�che.
     * @return le controleur d'assignation de t�ches.
     */
    AssignJobs getAssignJobsController();

    /**
     * Retourne le controleur de plannification de montage.
     * @return le controleur de plannification de montage.
     */
    PlanSchedule getPlanScheduleController();

    /**
     * Retourne l'objet g�n�rateur de programme de montage.
     * @return l'objet g�n�rateur de programme de montage.
     */
    ScheduleGenerator getScheduleGenerator();

}
