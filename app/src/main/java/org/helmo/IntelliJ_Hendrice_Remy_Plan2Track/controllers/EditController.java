package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class EditController extends Controller implements EditPlanning {

    private Planning planning;
    private ManagePlanning mainCtrl;

    public EditController(PlanningRepository planningRepository, Planning planning, ManagePlanning mainCtrl) {
        super(planningRepository);
        this.planning = planning;
        this.mainCtrl = mainCtrl;
    }

    @Override
    public Planning getPlanning(){
        return planning;
    }

    @Override
    public void editName(String name) {
        planning.setName(name);
    }

    @Override
    public void addJob(){
        mainCtrl.launchAddJobView();
    }

    @Override
    public void removeJob(){
        mainCtrl.launchRemoveJobView();
    }

    @Override
    public void assignJobs(){
        mainCtrl.launchAssignJobsView();
    }
}
