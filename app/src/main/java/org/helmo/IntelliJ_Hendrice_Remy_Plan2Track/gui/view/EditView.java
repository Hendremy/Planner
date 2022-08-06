package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class EditView {

    private final EditPlanning controller;

    public EditView(EditPlanning controller){
        this.controller = controller;
        this.title.setText(controller.getPlanning().getName());
    }

    public Text title = new Text();
    public Label nameLabel = new Label("Nom du planning");

    public BorderPane root = new BorderPane();
    {
        var center = new VBox();
        center.setSpacing(8);
        root.setPadding(new Insets(20));
    }

    public Parent getParent(){
        return root;
    }

}
