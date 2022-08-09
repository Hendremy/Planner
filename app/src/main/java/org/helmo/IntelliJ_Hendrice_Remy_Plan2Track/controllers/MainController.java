package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;


public class MainController implements ManagePlanning {

    private final PlanningCreator creator;
    private final PlanningRepository repository;
    private final PertSchedulePlanner schedulePlanner;
    private Planning planning;

    public MainController (PlanningRepository repository, PlanningCreator creator, PertSchedulePlanner schedulePlanner){
        this.repository = repository;
        this.creator = creator;
        this.schedulePlanner = schedulePlanner;
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
    public PlanningRepository getRepository(){return repository;}

    @Override
    public PertSchedulePlanner getSchedulePlanner() {
        return schedulePlanner;
    }

    @Override
    public EditPlanning getEditPlanningController(){
        return new EditPlanningController( this);
    }

    @Override
    public AddJob getAddJobController(){
        return new AddJobController(this);
    }

    @Override
    public RemoveJob getRemoveJobController(){
        return new RemoveJobController( this);
    }

    @Override
    public AssignJobs getAssignJobsController(){
        return new AssignJobsController( this);
    }

    @Override
    public PlanSchedule getPlanScheduleController(){return new PlanScheduleController( this);}
}
