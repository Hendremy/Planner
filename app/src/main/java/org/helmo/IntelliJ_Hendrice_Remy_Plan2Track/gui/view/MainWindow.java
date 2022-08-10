package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class MainWindow {

    private final ManagePlanning mainController;
    private final Stage primaryStage;

    public MainWindow(Stage primaryStage, ManagePlanning mainController){
        this.mainController = mainController;
        this.primaryStage = primaryStage;
    }

    public void show(){
        Scene scene = new Scene(root, 1200,800);

        primaryStage.setTitle("Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private final Button create = new Button("Nouveau planning +");{
        create.setOnAction(e -> createPlanning());
    }

    private final Button load = new Button("Charger un planning");{
        load.setOnAction(e -> loadPlanning());
    }

    private final ToolBar toolBar = new ToolBar(create, load);

    private final TabPane tabs = new TabPane();
    {
        tabs.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            if(newValue instanceof ScheduleTab){
                ScheduleTab tab = (ScheduleTab) newValue;
                tab.refresh();
            }
        });
    }

    private final VBox root = new VBox();
    {
        root.getChildren().add(toolBar);
        root.getChildren().add(tabs);
    }

    public Parent getParent(){
        return root;
    }

    private void createPlanning(){
        String message = null;
        if(mainController.getPlanning() != null){
            message = "Un montage est déjà en cours de création, vous allez l'écraser";
        }
        new CreatePlanningView(mainController, this, message);
        disable();
    }

    private void disable(){
        root.setDisable(true);
    }

    public void enable(){
        root.setDisable(false);
    }

    public void updateView(){
        if(mainController.getPlanning() != null){
            resetTabs();
            createTabs();
        }
    }

    private void createTabs(){
        EditTab editTab = new EditTab(mainController.getEditPlanningController());
        ScheduleTab scheduleTab = new ScheduleTab(mainController.getPlanScheduleController());

        addTab(editTab);
        addTab(scheduleTab);
    }

    private void addTab(Tab tab){
        tabs.getTabs().add(tab);
    }

    private void resetTabs(){
        tabs.getTabs().clear();
    }

    private void loadPlanning(){

    }
}
