package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

public class CriticalPathView {

    private final PlanSchedule controller;

    public CriticalPathView(PlanSchedule controller) throws PertException {
        this.controller = controller;
        ObservableList<PertTask> taskObservableList = FXCollections.observableArrayList(controller.getCriticalPath());
        criticalTasks.setItems(taskObservableList);
    }

    private ListView<PertTask> criticalTasks = new ListView<>();

    private final VBox root = new VBox();
    {
        root.setSpacing(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
    }

    public Parent getParent(){
        return root;
    }
}
