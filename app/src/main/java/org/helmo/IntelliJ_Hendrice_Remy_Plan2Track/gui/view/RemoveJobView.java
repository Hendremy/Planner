package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.RemoveJob;

public class RemoveJobView {

    private final RemoveJob controller;
    private final String jobName;

    public RemoveJobView(RemoveJob controller, String jobName){
        this.controller = controller;
        this.jobName = jobName;
    }

    private final Stage stage = new Stage();
    private final Label messageLabel = new Label();
    {
        messageLabel.setStyle("-fx-text-fill: red");
        messageLabel.setWrapText(false);
    }

    private final Button okBtn = new Button("Ok");
    {
        okBtn.setOnAction(e -> stage.close());
    }

    private final VBox root = new VBox(messageLabel, okBtn);
    {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setSpacing(8);
    }

    public RemoveJobView(String message){
        if(message != null) this.messageLabel.setText(message);
        showView();
    }

    private void showView(){
        stage.initStyle(StageStyle.UTILITY);
        stage.setWidth(400);
        stage.setTitle("Erreur");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
