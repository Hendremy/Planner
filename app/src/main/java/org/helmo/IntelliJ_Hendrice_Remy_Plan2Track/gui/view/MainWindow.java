package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class MainWindow {

    private final ManagePlanning mainController;

    public MainWindow(ManagePlanning mainController){
        this.mainController = mainController;
    }

    private Button create = new Button("Nouveau planning +");{
        create.setOnAction(e -> createPlanning());
    }

    private Button load = new Button("Charger un planning");{
        load.setOnAction(e -> loadPlanning());
    }

    private ToolBar toolBar = new ToolBar(create, load);

    private TabPane tabs = new TabPane();

    private VBox root = new VBox();
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
        if(mainController.getPlanning() != null){
            resetTabs();
            createTabs();
        }
    }

    private void createTabs(){
        EditTab editTab = new EditTab(mainController.getEditPlanningController());
        //LoadTab laodTab = new LoadTab(mainController.getLoadPlanningController));

        addTab(editTab.getTab());
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
