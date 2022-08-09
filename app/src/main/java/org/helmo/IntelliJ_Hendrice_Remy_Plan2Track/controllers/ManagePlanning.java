package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

public interface ManagePlanning {
    void createPlanning(String name);

    Planning getPlanning();

    PlanningRepository getRepository();

    EditPlanning getEditPlanningController();

    AddJob getAddJobController();

    RemoveJob getRemoveJobController();

    AssignJobs getAssignJobsController();

    PlanSchedule getPlanScheduleController();

}
