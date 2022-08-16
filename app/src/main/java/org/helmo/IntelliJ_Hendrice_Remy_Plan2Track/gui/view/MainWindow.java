package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.SupervisePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;

import java.io.File;

/**
 * Définit la fenêtre JavaFX principale de l'application.
 */
public class MainWindow {

    private final ManagePlanning mainController;
    private final SupervisePlanning superviseController;
    private final Stage primaryStage;

    /**
     * Affiche la fenêtre
     */
    public void show(){
        Scene scene = new Scene(root, 1200,800);
        primaryStage.setTitle("Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private final Button create = new Button("Nouveau montage +");{
        create.setOnAction(e -> createPlanning());
    }

    private final Button load = new Button("Charger un montage");{
        load.setOnAction(e -> loadPlanning());
    }

    private final ToolBar toolBar = new ToolBar(create, load);

    private final Label noPlanningErrorMessage = new Label("Aucun montage en cours");
    {
        noPlanningErrorMessage.setAlignment(Pos.CENTER);
        noPlanningErrorMessage.setFont(new Font(30));
        noPlanningErrorMessage.setStyle("-fx-text-fill: gray");
    }
    private final VBox messageBox = new VBox(noPlanningErrorMessage);
    {
        messageBox.setPadding(new Insets(300));
        messageBox.setAlignment(Pos.CENTER);
    }

    private final TabPane tabs = new TabPane();
    {
        tabs.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            if(newValue instanceof ScheduleTab){
                ScheduleTab tab = (ScheduleTab) newValue;
                tab.refresh();
            }
        });
    }

    private final StackPane content = new StackPane(messageBox, tabs);

    private final VBox root = new VBox(toolBar, content);

    /**
     * Initialise la scène, le controleur de gestion de montage et le controleur de consultation de montage.
     * @param primaryStage la scène
     * @param manageController le controleur de gestion de montage
     * @param superviseController le controleur de consultation de montage
     */
    public MainWindow(Stage primaryStage, ManagePlanning manageController, SupervisePlanning superviseController){
        this.mainController = manageController;
        this.primaryStage = primaryStage;
        this.superviseController = superviseController;
    }

    /**
     * Retourne la racine de la vue
     * @return la racine de la vue
     */
    public Parent getParent(){
        return root;
    }

    /**
     * Demande à l'utilisateur d'encoder un nom pour le nouveau montage.
     * S'il y en a déjà un en cours, il prévient l'utilisateur qu'il va écraser le montage précédent
     */
    private void createPlanning(){
        String message = null;
        if(mainController.getPlanning() != null){
            message = "Un montage est déjà en cours de création, vous allez l'écraser";
        }
        new CreatePlanningWindow(mainController, this, message);
        disable();
    }

    /**
     * Désactive la fenêtre
     */
    private void disable(){
        root.setDisable(true);
    }

    /**
     * Active la fenêtre
     */
    public void enable(){
        root.setDisable(false);
    }

    /**
     * Etablit les onglets de gestion de montage.
     */
    public void setManageTabs(){
        resetTabs();
        if(mainController.getPlanning() != null){
            createManageTabs();
        }
    }

    /**
     * Crée les onglets de gestion de montage.
     */
    private void createManageTabs(){
        EditTab editTab = new EditTab(mainController.getEditPlanningController());
        ScheduleTab scheduleTab = new ScheduleTab(mainController.getPlanScheduleController());
        addTab(editTab);
        addTab(scheduleTab);
    }

    /**
     * Ajoute un onglet.
     * @param tab l'onglet à ajouter
     */
    private void addTab(Tab tab){
        tabs.getTabs().add(tab);
    }

    /**
     * Retire tous les onglets.
     */
    private void resetTabs(){
        tabs.getTabs().clear();
    }

    /**
     * Demande à l'utilisateur de sélectionner un fichier à charger.
     */
    private void loadPlanning(){
        Stage fileStage = new Stage();
        File selectedFile = selectJsonFile(fileStage);
        try{
            if(selectedFile != null){
                PlanningProgress planningProgress = superviseController.loadPlanning(selectedFile);
                setSuperviseTabs(planningProgress);
            }
        }catch(PlanningRepositoryException ex){
            new ErrorMessageWindow(ex.getMessage());
        }
    }

    /**
     * Etablit les onglets de consultation de montage.
     * @param planningProgress l'avancement de montage
     */
    private void setSuperviseTabs(PlanningProgress planningProgress){
        resetTabs();
        if(planningProgress != null){
            createSuperviseTabs(planningProgress);
        }
    }

    /**
     * Crée les onglets de consultation de montage
     * @param planningProgress l'avancement de montage
     */
    private void createSuperviseTabs(PlanningProgress planningProgress){
        SuperviseTab superviseTab = new SuperviseTab(superviseController, planningProgress);
        addTab(superviseTab);
    }

    /**
     * Ouvre un explorateur de fichier pour choisir le montage à charger.
     * @param fileStage la scène
     * @return le fichier choisi ou null si aucun choisi
     */
    private File selectJsonFile(Stage fileStage){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jsonExtensionFilter = new FileChooser.ExtensionFilter("JSON files(*.json)","*.json");
        fileChooser.getExtensionFilters().add(jsonExtensionFilter);
        fileChooser.setInitialDirectory(superviseController.getPlanningFilesDirectory());
        return fileChooser.showOpenDialog(fileStage);
    }
}
