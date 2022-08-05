package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class ManageView {

    private final ManagePlanning controller;

    public ManageView(ManagePlanning controller){
        this.controller = controller;
    }

    private final Label nameLabel = new Label("Nom du planning :");
    private final TextField nameInput = new TextField();

    private final Button createBtn = new Button("Créer un planning");
    {
        createBtn.setOnAction(event -> {
            createPlanning();
        });
    }

    public GridPane root = new GridPane();
    {
        var center = new HBox();
        center.getChildren().add(nameLabel);
        center.getChildren().add(nameInput);
        center.getChildren().add(createBtn);
        center.setSpacing(8);

        root.setAlignment(Pos.TOP_CENTER);
        root.add(center,0,1);
        root.setPadding(new Insets(20));
    }

    public Parent getParent(){
        return root;
    }

    private void createPlanning(){
        String name = nameInput.getText();
        if(!name.isBlank()){
            controller.createPlanning(name);
        }
    }
}
