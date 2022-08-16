package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;

/**
 * Définit la vue JavaFX des tâches du montage.
 */
public class JobListView {

    private final ObservableList<JobViewModel> jobList;
    private final ManageJobsView manageJobsView;

    private final Button addButon = new Button("Ajouter +");
    {
        addButon.setOnAction(e -> addJob());
    }

    private final Button removeButton = new Button("Supprimer X");
    {
        removeButton.setOnAction(e -> removeJob());
    }

    private final HBox buttonRow = new HBox(addButon, removeButton);
    {
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(50);
    }

    private final ListView<JobViewModel> jobListView = new ListView<>();
    {
        jobListView.getSelectionModel().selectedItemProperty().addListener(e -> {
            assignJob();
        });
    }

    private final VBox content = new VBox(buttonRow, jobListView);
    {
        content.setSpacing(8);
        content.setPadding(new Insets(20));
    }

    private final TitledPane root = new TitledPane("Tâches", content);
    {
        root.setMinWidth(250);
        root.setMinHeight(500);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setCollapsible(false);
    }

    /**
     * Initialise la vue, les modèles de vues des tâches du montage et la vue parent de gestion des tâches.
     * @param jobList les modèles de vues des tâches
     * @param manageJobsView les modèles des vues des tâches
     */
    public JobListView(ObservableList<JobViewModel> jobList, ManageJobsView manageJobsView){
        this.manageJobsView = manageJobsView;
        this.jobList = jobList;
        jobListView.setItems(jobList);
    }


    /**
     * Retourne la racine de la vue.
     * @return la racine de la vue
     */
    public Parent getParent(){
        return root;
    }

    /**
     * Demande à la vue parent d'afficher le formulaire d'ajout d'une tâche.
     */
    private void addJob(){
        manageJobsView.showAddJob();
    }

    /**
     * Demande à la vue parent d'afficher la vue de suppresion d'une tâche.
     */
    private void removeJob(){
        String selectedName = getSelectedJob();
        if(selectedName != null){
            manageJobsView.showRemoveJob(selectedName);
        }
    }

    /**
     * Demande à la vue parent d'afficher le formulaire d'assignation d'une tâche.
     */
    private void assignJob(){
        String selectedName = getSelectedJob();
        if(selectedName != null){
            manageJobsView.showAssignJob(selectedName);
        }
    }

    /**
     * Retourne le nom de la tâche sélectionnée dans la liste.
     * @return le nom de la tâche sélectionnée dans la liste
     */
    private String getSelectedJob(){
        JobViewModel selectedItem = jobListView.getSelectionModel().getSelectedItem();
        return selectedItem != null ? selectedItem.getName() : null;
    }
}
