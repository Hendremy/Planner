package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

public class AddJobController extends Controller implements AddJob {

    private final Planning planning;
    private Job job;

    public AddJobController(PlanningRepository planningRepository, Planning planning) {
        super(planningRepository);
        this.planning = planning;
    }

    @Override
    public void createJob(String name, String description, int duration){
        job = new Job(name, description, duration);
    }

    @Override
    public void addPriorJob(String name){
        planning.addPriorToJob(name, job);
    }

    @Override
    public void addJobToPlanning(){
        planning.addJob(job);
    }

}
