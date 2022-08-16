package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;

import java.util.List;

/**
 * Définit la vue des tâches critiques du montage.
 */
public class CriticalJobsView extends CliView{

    private final PlanSchedule controller;

    /**
     * Initialise la vue avec le controleur de plannification du montage
     * @param controller le contrôleur de plannification du montage
     */
    public CriticalJobsView(PlanSchedule controller){
        this.controller = controller;
    }

    /**
     * Récupère le chemin critique de son controleur et l'affiche.
     */
    public void show(){
        try{
            List<PertTaskViewModel> criticalPath = controller.getCriticalPath();
            console.println(presenter.formatCriticalPath(criticalPath));
        }catch(PertException ex){
            console.error(ex.getMessage());
        }
    }
}
