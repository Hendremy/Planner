package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class CriticalJobsController extends Controller{
    public CriticalJobsController(Console console, Presenter presenter, PlanningRepository planningRepository) {
        super(console, presenter, planningRepository);
    }

    public void findCriticalJobs(Planning planning){

    }
}
