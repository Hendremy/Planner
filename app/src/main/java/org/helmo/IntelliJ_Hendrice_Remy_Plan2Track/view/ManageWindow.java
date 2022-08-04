package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.view;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;


public class ManageWindow {

    private ManagePlanning controller;

    public ManageWindow(ManagePlanning controller){
        this.controller = controller;
    }

    private BorderPane root = new BorderPane();

    public Parent getParent() {
        return root;
    }
}
