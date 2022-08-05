package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

public class AssignJobsController extends Controller implements AssignJobs {

    private final Planning planning;

    public AssignJobsController(PlanningRepository planningRepository, Planning planning) {
        super(planningRepository);
        this.planning = planning;
    }

    @Override
    public Iterable<Technician> getTechnicians(){
        return getRepository().getTechnicians();
    }



}
