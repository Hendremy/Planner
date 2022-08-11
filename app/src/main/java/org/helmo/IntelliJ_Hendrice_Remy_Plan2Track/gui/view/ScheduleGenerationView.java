package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.ScheduleRowViewModel;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class ScheduleGenerationView {

    private final PlanSchedule controller;

    public ScheduleGenerationView(PlanSchedule controller){
        this.controller = controller;
    }

    private final Label startDateLabel = new Label("Date de début du montage :");
    private final DatePicker startDatePicker = new DatePicker(LocalDate.now());
    private final Button generateBtn = new Button("Générer le planning");
    {
        generateBtn.setOnAction(e -> generateSchedule());
    }
    private final Label errorMessage = new Label();
    {
        errorMessage.setAlignment(Pos.CENTER);
        errorMessage.setVisible(false);
        errorMessage.setStyle("-fx-text-fill: red");
    }

    private final HBox generateBar = new HBox(startDateLabel, startDatePicker, generateBtn);
    {
        generateBar.setSpacing(8);
        generateBar.setAlignment(Pos.CENTER);
    }

    private final TableColumn<ScheduleRowViewModel, String> taskNameCol = new TableColumn<>("Nom de la tâche");
    private final TableColumn<ScheduleRowViewModel, String> startDateCol = new TableColumn<>("Début prévu");
    private final TableColumn<ScheduleRowViewModel, String> techNameCol = new TableColumn<>("Assigné à");

    private final TableView<ScheduleRowViewModel> scheduleTableView = new TableView<>();
    {
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        taskNameCol.setResizable(false);
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateCol.setResizable(false);
        techNameCol.setCellValueFactory(new PropertyValueFactory<>("techName"));
        techNameCol.setResizable(false);

        taskNameCol.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.5));
        startDateCol.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.2));
        techNameCol.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.3));

        scheduleTableView.getColumns().add(taskNameCol);
        scheduleTableView.getColumns().add(startDateCol);
        scheduleTableView.getColumns().add(techNameCol);
    }

    private final Button saveScheduleBtn = new Button("Sauvegarder le planning");
    {
        saveScheduleBtn.setDisable(true);
        saveScheduleBtn.setOnAction(e -> saveSchedule());
    }

    private final VBox content = new VBox(generateBar, errorMessage, scheduleTableView, saveScheduleBtn);
    {
        content.setSpacing(8);
        content.setPadding(new Insets(20));
        content.setMinHeight(500);
        content.setAlignment(Pos.CENTER);
    }

    private final TitledPane root = new TitledPane("Planning", content);
    {
        root.setCollapsible(false);
    }

    public Parent getParent(){
        return root;
    }

    private void generateSchedule(){
        if(controller.planningIsEmpty()){
            showError("Aucune tâche à planifier");
        }else if(!controller.planningAllTasksAssigned()){
            showError("Toutes les tâches ne sont pas assignées");
        }else{
            getSchedule();
        }
    }

    private void getSchedule(){
        try{
            resetError();
            updateTableView();
            saveScheduleBtn.setDisable(false);
        }catch(DateTimeException ex){
            showError("Date invalide");
        }
    }

    private void updateTableView(){
        LocalDate startDate = getDatePickerValue();
        List<ScheduleRowViewModel> scheduleRows = controller.generateSchedule(startDate);
        ObservableList<ScheduleRowViewModel> scheduleRowsObservable = FXCollections.observableArrayList(scheduleRows);
        scheduleTableView.setItems(scheduleRowsObservable);
        scheduleTableView.getSortOrder().add(startDateCol);
    }

    private void showError(String message){
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }

    private void resetError(){
        errorMessage.setVisible(false);
    }

    private LocalDate getDatePickerValue(){
        String dateString = startDatePicker.getEditor().getText();
        return startDatePicker.getConverter().fromString(dateString);
    }

    private void saveSchedule(){

    }
}
