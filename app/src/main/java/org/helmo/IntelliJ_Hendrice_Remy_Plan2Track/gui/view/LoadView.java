package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.LoadPlanning;

public class LoadView {

    private final LoadPlanning controller;

    public LoadView(LoadPlanning controller){
        this.controller = controller;
    }

    public GridPane root = new GridPane();


    public Parent getParent(){
        return root;
    }
}
