package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;

/**
 * Définit la vue CLI de la date de fin au plus tôt du montage
 */
public class EarliestEndTimeView extends CliView {

    private final PlanSchedule controller;

    /**
     * Initialise la vue avec son controleur de plannification du montage.
     * @param controller le controleur de plannification du montage
     */
    public EarliestEndTimeView(PlanSchedule controller){
        this.controller = controller;
    }

    /**
     * Récupère la date de fin au plus tôt du montage du controleur et l'affiche.
     */
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
