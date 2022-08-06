package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.AddJobView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.AssignJobsView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.EditView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.RemoveJobView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.*;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;


public class MainController extends Controller implements ManagePlanning {

    private final PlanningCreator creator;
    private Planning planning;

    public MainController (PlanningRepository repository, PlanningCreator creator){
        super(repository);
        this.creator = creator;
    }

    @Override
    public void createPlanning(String name) {
        planning = creator.create(name);
    }

    @Override
    public Planning getPlanning() {
        return planning;
    }

    @Override
    public EditPlanning getEditPlanningController(){
        return new EditPlanningController(getRepository(), this);
    }

    @Override
    public AddJob getAddJobController(){
        return new AddJobController(getRepository(), getPlanning());
    }

    @Override
    public RemoveJob getRemoveJobController(){
        return new RemoveJobController(getRepository(), getPlanning());
    }

    @Override
    public AssignJobs getAssignJobsController(){
        return new AssignJobsController(getRepository(), getPlanning());
    }
}
