package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.RemoveJob;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class RemoveJobController extends Controller implements RemoveJob {

    private final Planning planning;

    public RemoveJobController(PlanningRepository planningRepository, Planning planning) {
        super(planningRepository);
        this.planning = planning;
    }

    @Override
    public int findJobOccurences(String name) throws JobNotFoundException {
        return planning.countPriorJob(name);
    }

    @Override
    public void removeJob(String name) throws JobNotFoundException {
        planning.removeJob(name);
    }

}
