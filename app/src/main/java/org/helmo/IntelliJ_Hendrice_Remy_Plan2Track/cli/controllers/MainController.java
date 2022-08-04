package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.EditView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
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
        var editController = new EditController(repository,planning);
        new EditView(editController).loop();
    }
}
