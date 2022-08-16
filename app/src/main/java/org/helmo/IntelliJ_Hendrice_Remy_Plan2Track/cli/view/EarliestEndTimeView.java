package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;

/**
 * D�finit la vue CLI de la date de fin au plus t�t du montage
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
     * R�cup�re la date de fin au plus t�t du montage du controleur et l'affiche.
     */
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
