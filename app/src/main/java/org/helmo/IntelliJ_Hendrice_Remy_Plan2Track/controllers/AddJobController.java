package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

public class AddJobController extends Controller implements AddJob {

    private final Planning planning;

    public AddJobController(PlanningRepository planningRepository, Planning planning) {
        super(planningRepository);
        this.planning = planning;
    }

    @Override
    public void addJobToPlanning(String name, String description, int duration, Iterable<String> priorJobs){
        planning.addJob(name, description, duration, priorJobs);
    }
}
