package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.control.Tab;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;

public class ScheduleTab extends Tab{

    private final PlanSchedule controller;

    public ScheduleTab(PlanSchedule controller){
        this.controller =controller;
        ScheduleView scheduleView = new ScheduleView(controller);
        setContent(scheduleView.getParent());
        setClosable(false);
        setText("Plannification");
    }

    public void refresh(){
        ScheduleView scheduleView = new ScheduleView(controller);
        setContent(scheduleView.getParent());
    }
}
