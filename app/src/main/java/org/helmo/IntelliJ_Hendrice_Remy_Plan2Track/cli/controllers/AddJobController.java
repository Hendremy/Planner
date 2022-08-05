package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AddJob;
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

    public void createJob(String name, String description, int duration){
        job = new Job(name, description, duration);
    }

    public void addPriorJob(String name){
        planning.addPriorToJob(name, job);
    }

    public void addJobToPlanning(){
        planning.addJob(job);
    }


}
