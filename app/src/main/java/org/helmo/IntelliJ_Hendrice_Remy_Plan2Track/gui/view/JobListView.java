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

public class JobListView {

    private final ObservableList<JobViewModel> jobList;
    private final ManageJobsView manageJobsView;

    public JobListView(ObservableList<JobViewModel> jobList, ManageJobsView manageJobsView){
        this.manageJobsView = manageJobsView;
        this.jobList = jobList;
        jobListView.setItems(jobList);
    }

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

    public Parent getParent(){
        return root;
    }

    private void addJob(){
        manageJobsView.showAddJob();
    }

    private void removeJob(){
        String selectedName = getSelectedJob();
        if(selectedName != null){
            manageJobsView.showRemoveJob(selectedName);
        }
    }

    private void assignJob(){
        String selectedName = getSelectedJob();
        if(selectedName != null){
            manageJobsView.showAssignJob(selectedName);
        }
    }

    private String getSelectedJob(){
        JobViewModel selectedItem = jobListView.getSelectionModel().getSelectedItem();
        return selectedItem != null ? selectedItem.getName() : null;
    }
}
