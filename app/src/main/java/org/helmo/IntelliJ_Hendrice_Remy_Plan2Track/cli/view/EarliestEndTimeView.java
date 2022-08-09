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
                console.println(String.format("Date de fin au plus tôt : %d jours après la date de début", planningDuration));
            }else{
                console.println("Aucune tâche à achever");
            }
        }catch(PertException ex){
            console.error(ex.getMessage());
        }
    }
}
