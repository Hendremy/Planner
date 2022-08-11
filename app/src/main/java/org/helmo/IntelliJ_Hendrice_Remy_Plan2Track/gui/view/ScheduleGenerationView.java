package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.ScheduleRowViewModel;

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

    private final HBox generateBar = new HBox(startDateLabel, startDate, generateBtn);
    {
        generateBar.setSpacing(8);
        generateBar.setAlignment(Pos.CENTER);
    }

    private final TableView<ScheduleRowViewModel> scheduleTableView = new TableView<>();
    {
        TableColumn<ScheduleRowViewModel, String> taskNameCol = new TableColumn<>("Nom de la tâche");
        TableColumn<ScheduleRowViewModel, String> startDateCol = new TableColumn<>("Début prévu");
        TableColumn<ScheduleRowViewModel, String> techNameCol = new TableColumn<>("Nom de la tâche");

        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        techNameCol.setCellValueFactory(new PropertyValueFactory<>("techName"));

    }

    private final Button saveScheduleBtn = new Button("Sauvegarder le planning");
    {
        saveScheduleBtn.setOnAction(e -> saveSchedule());
    }

    private final VBox root = new VBox(generateBar, scheduleTableView, saveScheduleBtn);
    {
        root.setSpacing(8);
        root.setPadding(new Insets(20));
        root.setMinHeight(500);
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
