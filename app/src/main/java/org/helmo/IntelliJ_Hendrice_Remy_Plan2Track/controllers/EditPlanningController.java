package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class EditPlanningController extends Controller implements EditPlanning {

    private ManagePlanning mainCtrl;

    public EditPlanningController(PlanningRepository planningRepository, ManagePlanning mainCtrl) {
        super(planningRepository);
        this.mainCtrl = mainCtrl;
    }

    @Override
    public Planning getPlanning(){
        return mainCtrl.getPlanning();
    }

    @Override
    public void editName(String name) {
        getPlanning().setName(name);
    }

    @Override
    public AddJob getAddJobController(){
        return mainCtrl.getAddJobController();
    }

    @Override
    public RemoveJob getRemoveJobController(){
        return mainCtrl.getRemoveJobController();
    }

    @Override
    public AssignJobs getAssignJobsController(){
        return mainCtrl.getAssignJobsController();
    }
}
