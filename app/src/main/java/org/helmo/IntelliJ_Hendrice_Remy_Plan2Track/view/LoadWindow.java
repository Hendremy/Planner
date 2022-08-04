package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.view;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.LoadPlanning;

public class LoadWindow {

    private LoadPlanning controller;

    public LoadWindow(LoadPlanning controller){
        this.controller = controller;
    }

    private BorderPane root = new BorderPane();

    public Parent getParent() {
        return root;
    }
}
