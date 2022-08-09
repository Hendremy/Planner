package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.Queue;

public class PlanScheduleController implements PlanSchedule{

    private final ManagePlanning manageController;

    public PlanScheduleController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    @Override
    public Queue<PertTask> getCriticalPath() {
        return null;
    }
}
