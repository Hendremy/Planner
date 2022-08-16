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
 * D�finit la vue JavaFX des t�ches du montage.
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

    private final TitledPane root = new TitledPane("T�ches", content);
    {
        root.setMinWidth(250);
        root.setMinHeight(500);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setCollapsible(false);
    }

    /**
     * Initialise la vue, les mod�les de vues des t�ches du montage et la vue parent de gestion des t�ches.
     * @param jobList les mod�les de vues des t�ches
     * @param manageJobsView les mod�les des vues des t�ches
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
     * Demande � la vue parent d'afficher le formulaire d'ajout d'une t�che.
     */
    private void addJob(){
        manageJobsView.showAddJob();
    }

    /**
     * Demande � la vue parent d'afficher la vue de suppresion d'une t�che.
     */
    private void removeJob(){
        String selectedName = getSelectedJob();
        if(selectedName != null){
            manageJobsView.showRemoveJob(selectedName);
        }
    }

    /**
     * Demande � la vue parent d'afficher le formulaire d'assignation d'une t�che.
     */
    private void assignJob(){
        String selectedName = getSelectedJob();
        if(selectedName != null){
            manageJobsView.showAssignJob(selectedName);
        }
    }

    /**
     * Retourne le nom de la t�che s�lectionn�e dans la liste.
     * @return le nom de la t�che s�lectionn�e dans la liste
     */
    private String getSelectedJob(){
        JobViewModel selectedItem = jobListView.getSelectionModel().getSelectedItem();
        return selectedItem != null ? selectedItem.getName() : null;
    }
}
