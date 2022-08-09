package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class RemoveJobController implements RemoveJob {

    private final ManagePlanning manageController;

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

    private Planning getPlanning(){
        return manageController.getPlanning();
    }

}
