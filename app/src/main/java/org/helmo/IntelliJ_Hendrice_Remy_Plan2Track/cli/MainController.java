package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.AddJobView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.AssignJobsView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.EditView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.RemoveJobView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.*;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;


public class MainController extends Controller implements ManagePlanning {

    private Planning planning;

    public MainController (PlanningRepository repository){
        super(repository);
    }

    @Override
    public void createPlanning(String name) {
        planning = new Planning(name);
    }

    @Override
    public Planning getPlanning() {
        return planning;
    }

    @Override
    public void editPlanning(){
        EditPlanning editController = new EditController(getRepository(),planning, this);
        new EditView(editController).loop();
    }

    @Override
    public void launchAddJobView(){
        AddJob ctrl = new AddJobController(getRepository(), getPlanning());
        new AddJobView(ctrl).addJob();
    }

    @Override
    public void launchRemoveJobView(){
        RemoveJob ctrl = new RemoveJobController(getRepository(), getPlanning());
        new RemoveJobView(ctrl).removeJob();
    }

    @Override
    public void launchAssignJobsView(){
        AssignJobs ctrl = new AssignJobsController(getRepository(), getPlanning());
        new AssignJobsView(ctrl).assignJobs();
    }
}
