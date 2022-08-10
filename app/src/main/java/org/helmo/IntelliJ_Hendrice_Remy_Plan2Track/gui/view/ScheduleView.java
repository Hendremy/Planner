package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;

public class ScheduleView {

    private final PlanSchedule controller;

    private final Label nameLabel = new Label();

    private final HBox nameArea = new HBox();
    {
        nameArea.setAlignment(Pos.CENTER);
        nameArea.getChildren().add(nameLabel);
        nameArea.setSpacing(8);
        nameArea.setPadding(new Insets(10));
    }

    private final Button criticalPathBtn = new Button("Trouver les tâches critiques");
    {
        criticalPathBtn.setOnAction(e -> findCriticalPath());
    }

    private final Button generateSchedBtn = new Button("Générer le planning");
    {
        generateSchedBtn.setOnAction(e -> generateSchedule());
    }

    private final VBox criticalTasks = new VBox(criticalPathBtn, generateSchedBtn);
    {

    }

    public ScheduleView(PlanSchedule controller){
        this.controller = controller;
        updateNameLabel();
    }

    private void updateNameLabel(){
        String name = controller.getPlanningName();
        try{
            setNameLabel(name, controller.getEarliestEndDate());
        } catch (PertException e) {
            new ErrorMessageView(e.getMessage());
        }
    }

    private void setNameLabel(String name, int duration){
        String durationText;
        if(duration == 0){
            durationText = "Aucune tâche à terminer";
        }else{
            durationText = String.format("Fin au plus tôt : %d jours après la date de début", duration);
        }
        nameLabel.setText(String.format("%s - %s", name, durationText));
    }

    public BorderPane root = new BorderPane();
    {
        root.setTop(nameArea);
        root.setLeft(criticalTasks);
        root.setPadding(new Insets(20));
    }

    public Parent getParent(){
        return root;
    }

    private void findCriticalPath(){

    }

    private void generateSchedule(){

    }
}
