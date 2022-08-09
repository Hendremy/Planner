package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class AddJobController implements AddJob {

    private final ManagePlanning manageController;

    public AddJobController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    @Override
    public void addJobToPlanning(String name, String description, int duration, Iterable<String> priorJobs){
        getPlanning().addJob(name, description, duration, priorJobs);
    }

    private Planning getPlanning(){
        return manageController.getPlanning();
    }
}
