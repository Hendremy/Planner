package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

/**
 * Définit le controleur de suppression de tâches.
 */
public class RemoveJobController implements RemoveJob {

    private final ManagePlanning manageController;

    /**
     * Initialise le controleur de suppression de tâches avec le controleur de gestion de montage.
     * @param manageController le controleur de gestion du montage
     */
    public RemoveJobController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    @Override
    public int findJobOccurences(String name) throws JobNotFoundException {
        return getPlanning().countPriorJob(name);
    }

    @Override
    public void removeJob(String name) throws JobNotFoundException {
        getPlanning().removeJob(name);
    }

    /**
     * Retourne le planning en cours.
     * @return le planning en cours
      */
    private Planning getPlanning(){
        return manageController.getPlanning();
    }

}
