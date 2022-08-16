package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.ScheduleGenerator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

/**
 * Définit le contrôleur de gestion de montage.
 */
public class ManagePlanningController implements ManagePlanning {

    private final PlanningCreator creator;
    private final PlanningRepository repository;
    private final PertSchedulePlanner schedulePlanner;
    private final ScheduleGenerator scheduleGenerator;
    private Planning planning;

    /**
     * Initialise le controleur de gestion de montage avec l'objet de stockage de montage, le créateur de montage,
     * le plannificateur de montage et le générateur de programme de montage.
     * @param repository l'objet de stockage de montage
     * @param creator le créateur de montage
     * @param schedulePlanner le plannificateur de montage et le générateur
     * @param scheduleGenerator le générateur de programme de montage
     */
    public ManagePlanningController(PlanningRepository repository, PlanningCreator creator,
                                    PertSchedulePlanner schedulePlanner, ScheduleGenerator scheduleGenerator){
        this.repository = repository;
        this.creator = creator;
        this.schedulePlanner = schedulePlanner;
        this.scheduleGenerator = scheduleGenerator;
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
    public ScheduleGenerator getScheduleGenerator(){ return scheduleGenerator;}

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
    public PlanSchedule getPlanScheduleController(){ return new PlanScheduleController( this);}
}
