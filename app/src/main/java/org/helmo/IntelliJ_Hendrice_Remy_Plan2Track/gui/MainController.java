package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.Controller;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

public class MainController extends Controller implements ManagePlanning {

    public MainController(PlanningRepository planningRepository) {
        super(planningRepository);
    }

    @Override
    public void createPlanning(String name) {

    }

    @Override
    public Planning getPlanning() {
        return null;
    }

    @Override
    public void editPlanning() {

    }

    @Override
    public void launchAddJobView() {

    }

    @Override
    public void launchRemoveJobView() {

    }

    @Override
    public void launchAssignJobsView() {

    }
}
