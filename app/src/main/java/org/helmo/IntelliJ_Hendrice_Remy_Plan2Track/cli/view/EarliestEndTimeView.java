package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;

public class EarliestEndTimeView extends CliView {

    private final PlanSchedule controller;

    public EarliestEndTimeView(PlanSchedule controller){
        this.controller = controller;
    }

    public void show(){
        try{
            int planningDuration = controller.getEarliestEndDate();
            if(planningDuration > 0 ){
                console.println(String.format("Date de fin au plus t�t : %d jours apr�s la date de d�but", planningDuration));
            }else{
                console.println("Aucune t�che � achever");
            }
        }catch(PertException ex){
            console.error(ex.getMessage());
        }
    }
}
