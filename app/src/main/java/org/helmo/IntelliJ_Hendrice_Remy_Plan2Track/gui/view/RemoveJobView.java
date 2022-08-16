package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.RemoveJob;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;

public class RemoveJobView {

    private final RemoveJob controller;
    private final String jobName;
    private final ManageJobsView parentView;

    private final Stage stage = new Stage();
    private final Label messageLabel = new Label();
    {
        messageLabel.setStyle("-fx-text-fill: red");
        messageLabel.setWrapText(false);
    }

    private final Button confirmBtn = new Button("Confirmer");
    {
        confirmBtn.setOnAction(e -> removeJob());
    }

    private final Button cancelBtn = new Button("Annuler");
    {
        cancelBtn.setOnAction(e -> stage.close());
    }

    private final HBox buttonRow = new HBox(confirmBtn, cancelBtn);
    {
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(20);
    }

    private final VBox root = new VBox(messageLabel, buttonRow);
    {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setSpacing(8);
    }

    public RemoveJobView(RemoveJob controller, String jobName, ManageJobsView parentView){
        this.controller = controller;
        this.jobName = jobName;
        this.parentView = parentView;
        stage.setTitle(String.format("%s - Supprimer la tâche", jobName));
        setMessage();
        showView();
    }

    private void setMessage(){
        try{
            int occ = controller.findJobOccurences(jobName);
            String message = String.format("La tâche \"%s\" est requise pour %d autre(s) tâche(s)\nConfirmez-vous sa suppression ?", jobName, occ);
            messageLabel.setText(message);
        }catch(JobNotFoundException ex){
            new ErrorMessageWindow(String.format("La tâche à supprimer \"%s\" n'a pas été trouvée", jobName));
        }
        stage.close();
    }


    private void showView(){
        stage.initStyle(StageStyle.UTILITY);
        stage.setWidth(400);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    private void removeJob(){
        try{
            controller.removeJob(jobName);
            parentView.onJobRemoved(jobName);
        }catch(JobNotFoundException ex){
            new ErrorMessageWindow(String.format("La tâche à supprimer \"%s\" n'a pas été trouvée", jobName));
        }
        stage.close();
    }
}
