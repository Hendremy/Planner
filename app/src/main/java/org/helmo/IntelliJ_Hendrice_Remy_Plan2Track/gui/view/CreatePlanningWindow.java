package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class CreatePlanningWindow {

    private final ManagePlanning mainController;
    private final MainWindow mainWindow;

    private final Stage stage = new Stage();
    private final Label messageLabel = new Label();
    {
        messageLabel.setStyle("-fx-text-fill: red");
        messageLabel.setWrapText(false);
    }
    private final Label nameLabel = new Label("Nom du planning");
    private final TextField nameInput = new TextField();
    private final Button createBtn = new Button("Créer");
    {
        createBtn.setOnAction(e -> createPlanning());
    }
    private final Button cancelBtn = new Button("Annuler");
    {
        cancelBtn.setOnAction(e -> cancel());
    }

    private final HBox buttonRow = new HBox();
    {
        buttonRow.getChildren().add(createBtn);
        buttonRow.getChildren().add(cancelBtn);

        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(100);
    }

    private final VBox root = new VBox();
    {
        root.getChildren().add(messageLabel);
        root.getChildren().add(nameLabel);
        root.getChildren().add(nameInput);
        root.getChildren().add(buttonRow);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setSpacing(8);
    }

    public CreatePlanningWindow(ManagePlanning mainController, MainWindow mainWindow, String message){
        this.mainController = mainController;
        this.mainWindow = mainWindow;
        if(message != null) this.messageLabel.setText(message);
        showView();
    }

    private void showView(){
        stage.initStyle(StageStyle.UTILITY);
        stage.setOnCloseRequest(e -> cancel());
        stage.setWidth(400);
        stage.setTitle("Créer un planning");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    private void createPlanning(){
        String name = nameInput.getText().trim();
        if(!name.isBlank()){
            mainController.createPlanning(name);
            stage.hide();
            mainWindow.enable();
            mainWindow.updateView();
        }
    }

    private void cancel(){
        stage.hide();
        mainWindow.enable();
    }

}
