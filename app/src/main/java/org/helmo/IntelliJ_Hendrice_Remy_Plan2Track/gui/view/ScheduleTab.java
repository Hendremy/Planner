package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.control.Tab;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;

/**
 * Définit l'onglet de plannification de montage.
 */
public class ScheduleTab extends Tab{

    private final PlanSchedule controller;

    /**
     * Initialise l'onglet et le controleur de plannification du montage.
     * @param controller le controleur de plannification du montage
     */
    public ScheduleTab(PlanSchedule controller){
        this.controller =controller;
        ScheduleView scheduleView = new ScheduleView(controller);
        setContent(scheduleView.getParent());
        setClosable(false);
        setText("Plannification");
    }

    /**
     * Rafraîchit l'onglet.
     */
    public void refresh(){
        ScheduleView scheduleView = new ScheduleView(controller);
        setContent(scheduleView.getParent());
    }
}
