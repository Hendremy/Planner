package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public interface ManagePlanning {
    void createPlanning(String name);

    Planning getPlanning();

    EditPlanning getEditPlanningController();

    AddJob getAddJobController();

    RemoveJob getRemoveJobController();

    AssignJobs getAssignJobsController();

}
