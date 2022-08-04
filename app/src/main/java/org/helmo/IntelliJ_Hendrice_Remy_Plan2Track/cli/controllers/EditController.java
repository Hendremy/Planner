package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class EditController extends Controller implements EditPlanning {

    private Planning planning;

    public EditController(PlanningRepository planningRepository, Planning planning) {
        super(planningRepository);
        this.planning = planning;
    }

    @Override
    public Planning getPlanning(){
        return planning;
    }

    @Override
    public void editName(String name) {
        planning.setName(name);
    }
}
