package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class MainWindow {

    private ManagePlanning control;


    public MainWindow(ManagePlanning control){
        this.control = control;
    }

    private TextField planningName = new TextField();

    private GridPane root = new GridPane();
    {
        root.setAlignment(Pos.CENTER);
        //root.setGridLinesVisible(true);
        root.setPadding(new Insets(20));
        root.setHgap( 4 ); root.setVgap( 8 );
        for (Node n:root.getChildren()) {
            GridPane.setHalignment(n, HPos.CENTER );
        }
    }

    public Parent getParent(){
        return root;
    }
}
