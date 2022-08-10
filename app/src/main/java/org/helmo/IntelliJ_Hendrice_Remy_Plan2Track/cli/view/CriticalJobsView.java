package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;

import java.util.List;

public class CriticalJobsView extends CliView{

    private final PlanSchedule controller;

    public CriticalJobsView(PlanSchedule controller){
        this.controller = controller;
    }

    public void show(){
        try{
            List<PertTaskViewModel> criticalPath = controller.getCriticalPath();
            console.println(presenter.displayCriticalPath(criticalPath));
        }catch(PertException ex){
            console.error(ex.getMessage());
        }
    }
}
