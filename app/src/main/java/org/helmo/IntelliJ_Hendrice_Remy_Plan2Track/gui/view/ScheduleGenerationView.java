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
import javafx.scene.paint.Paint;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.ScheduleRowViewModel;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

/**
 * Définit la vue JavaFX de la génération de programme d'un montage
 */
public class ScheduleGenerationView {

    private final PlanSchedule controller;

    /**
     * Initialise la vue et le controleur de plannification de montage.
     * @param controller le controleur de plannification de montage
     */
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
        setColumnsCellValueFactories();
        bindColumnWidths();
        addColumns();
    }

    private void setColumnsCellValueFactories(){
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        taskNameCol.setResizable(false);
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateCol.setResizable(false);
        techNameCol.setCellValueFactory(new PropertyValueFactory<>("techName"));
        techNameCol.setResizable(false);
    }

    private void bindColumnWidths(){
        taskNameCol.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.5));
        startDateCol.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.2));
        techNameCol.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.3));
    }

    private void addColumns(){
        scheduleTableView.getColumns().add(taskNameCol);
        scheduleTableView.getColumns().add(startDateCol);
        scheduleTableView.getColumns().add(techNameCol);
    }

    private final Button saveScheduleBtn = new Button("Sauvegarder le planning");
    {
        saveScheduleBtn.setDisable(true);
        saveScheduleBtn.setOnAction(e -> saveSchedule());
    }
    private final Label saveSuccess = new Label();{
        saveSuccess.setVisible(false);
        saveSuccess.setTextFill(Paint.valueOf("Green"));
    }

    private final VBox content = new VBox(generateBar, errorMessage, scheduleTableView, saveScheduleBtn, saveSuccess);
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

    /**
     * Retourne la racine de la vue.
     * @return la racine de la vue
     */
    public Parent getParent(){
        return root;
    }

    /**
     * Génère le programme du montage si possible ou affiche un message d'erreur.
     */
    private void generateSchedule(){
        if(controller.planningIsEmpty()){
            showError("Aucune tâche à planifier");
        }else if(!controller.planningAllTasksAssigned()){
            showError("Toutes les tâches ne sont pas assignées");
        }else{
            getSchedule();
        }
    }

    /**
     * Génère le programme du montage.
     */
    private void getSchedule(){
        try{
            resetSuccess();
            resetError();
            updateTableView();
            saveScheduleBtn.setDisable(false);
        }catch(DateTimeException ex){
            showError("Date invalide");
        }
    }

    /**
     * Met à jour le tableau du programme de montage.
     */
    private void updateTableView(){
        LocalDate startDate = getDatePickerValue();
        List<ScheduleRowViewModel> scheduleRows = controller.generateSchedule(startDate);
        ObservableList<ScheduleRowViewModel> scheduleRowsObservable = FXCollections.observableArrayList(scheduleRows);
        scheduleTableView.setItems(scheduleRowsObservable);
        scheduleTableView.getSortOrder().add(startDateCol);
    }

    /**
     * Montre le message d'erreur.
     * @param message message d'erreur
     */
    private void showError(String message){
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }

    /**
     * Cache le message d'erreur.
     */
    private void resetError(){
        errorMessage.setVisible(false);
    }

    /**
     * Retourne la date du choisisseur de date.
     * @return la date du choisisseur de date
     */
    private LocalDate getDatePickerValue(){
        String dateString = startDatePicker.getEditor().getText();
        return startDatePicker.getConverter().fromString(dateString);
    }

    /**
     * Demande au controleur de sauvegarder le programme et affiche la fenêtre d'erreur si il y en a une.
     */
    private void saveSchedule(){
        try{
            controller.saveSchedule();
            showSaveSuccess();
        }catch(PlanningRepositoryException ex){
            new ErrorMessageWindow(ex.getMessage());
        }
    }

    /**
     * Cache le message de succès de sauvegarde.
     */
    private void resetSuccess(){
        saveSuccess.setText("");
        saveSuccess.setVisible(false);
    }

    /**
     * Montre le message de succès de sauvegarde.
     */
    private void showSaveSuccess(){
        saveSuccess.setText("Planning enregistré !");
        saveSuccess.setVisible(true);
    }
}
