package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

/**
 * Définit le contrôleur d'ajout de tâches au montage
 */
public class AddJobController implements AddJob {

    private final ManagePlanning manageController;

    /**
     * Initialise le controleur d'ajout de tâches avec le controleur de gestion de montage.
     * @param manageController le controleur principal
     */
    public AddJobController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    @Override
    public void addJobToPlanning(String name, String description, int duration, Iterable<String> priorJobs){
        getPlanning().addJob(name, description, duration, priorJobs);
    }

    /**
     * Retourne le montage en cours.
     * @return le montage en cours
     */
    private Planning getPlanning(){
        return manageController.getPlanning();
    }
}
