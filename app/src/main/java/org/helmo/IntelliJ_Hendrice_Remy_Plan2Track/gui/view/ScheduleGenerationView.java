package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;

import java.time.LocalDate;

public class ScheduleGenerationView {

    private final PlanSchedule controller;

    public ScheduleGenerationView(PlanSchedule controller){
        this.controller = controller;
    }

    private final Label startDateLabel = new Label("Date de début du montage :");
    private final DatePicker startDate = new DatePicker(LocalDate.now());
    private final Button generateBtn = new Button("Générer le planning");
    {
        generateBtn.setOnAction(e -> generateSchedule());
    }

    private final TableView<JobViewModel> schedule = new TableView<>();

    private final Button saveScheduleBtn = new Button("Sauvegarder le planning");
    {
        saveScheduleBtn.setOnAction(e -> saveSchedule());
    }

    private final GridPane root = new GridPane();
    {
        root.setHgap(5);
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);
    }

    public Parent getParent(){
        return root;
    }

    private void generateSchedule(){

    }

    private void saveSchedule(){

    }
}
