package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;

/**
 * Définit la vue JavaFX du chemin critique d'un montage.
 */
public class CriticalPathView {

    private final PlanSchedule controller;

    private final Label errorMessage = new Label("Une erreur est survenue lors du calcul du chemin critique");
    private final Label emptyPathMessage = new Label("Aucune tâche critique");
    {
        errorMessage.setAlignment(Pos.CENTER);
        emptyPathMessage.setAlignment(Pos.CENTER);
    }

    private final ListView<PertTaskViewModel> critTasksListView = new ListView<>();
    {
        critTasksListView.setMouseTransparent(true);
        critTasksListView.setFocusTraversable(false);
    }

    private final VBox content = new VBox();
    {
        content.setSpacing(10);
        content.setMinHeight(500);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(20));
    }

    private final TitledPane root = new TitledPane("Tâches critique", content);
    {
        root.setCollapsible(false);
    }

    /**
     * Initialise la vue, le controleur de plannification de montage.
     * @param controller le controleur de plannification de montage.
     */
    public CriticalPathView(PlanSchedule controller) {
        this.controller = controller;
        setCriticalPathItems();
    }

    /**
     * Définit les éléments de la liste du chemin critique.
     */
    private void setCriticalPathItems(){
        try{
            ObservableList<PertTaskViewModel> taskObservableList = FXCollections.observableArrayList(controller.getCriticalPath());
            if(!taskObservableList.isEmpty()){
                critTasksListView.setItems(taskObservableList);
                content.getChildren().add(critTasksListView);
            }else{
                content.getChildren().add(emptyPathMessage);
            }
        }catch(PertException ex){
            content.getChildren().add(errorMessage);
        }
    }

    /**
     * Retourne la racine de la vue.
     * @return la racine de la vue
     */
    public Parent getParent(){
        return root;
    }
}
