package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class EditView {

    private final EditPlanning controller;
    private final EditJobsView editJobsView;

    public EditView(EditPlanning controller){
        this.controller = controller;
        String planName = controller.getPlanning().getName();
        editJobsView = new EditJobsView(controller);
        nameInput.setText(planName);
        root.setCenter(editJobsView.getParent());
    }

    private final Label nameLabel = new Label("Nom du planning");
    private final TextField nameInput = new TextField();
    private final Button changeNameBtn = new Button("Changer le nom");
    {
        changeNameBtn.setOnAction(e -> changeName());
    }

    private final HBox nameArea = new HBox();
    {
        nameArea.setAlignment(Pos.CENTER);
        nameArea.getChildren().add(nameLabel);
        nameArea.getChildren().add(nameInput);
        nameArea.getChildren().add(changeNameBtn);
        nameArea.setSpacing(8);
        nameArea.setPadding(new Insets(10));
    }

    private final BorderPane root = new BorderPane();
    {
        root.setTop(nameArea);
        root.setPadding(new Insets(20));
    }

    public Parent getParent(){
        return root;
    }

    private void changeName(){
        String name = nameInput.getText().trim();
        if(!name.isBlank()){
            controller.editName(name);
        }
    }

}
