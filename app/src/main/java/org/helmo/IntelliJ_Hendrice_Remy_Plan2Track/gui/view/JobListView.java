package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JobListView {

    private final ObservableList<String> jobList;
    private final ManageJobsView manageJobsView;

    public JobListView(ObservableList<String> jobList, ManageJobsView manageJobsView){
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

    private final ListView<String> jobListView = new ListView<>();

    private final VBox content = new VBox(buttonRow, jobListView);
    {
        content.setSpacing(8);
    }

    private final TitledPane root = new TitledPane("Tâches", content);
    {
        root.setMinWidth(250);
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
        manageJobsView.removeJob(getSelectedJob());
    }

    private void assignJob(){
        manageJobsView.assignJob(getSelectedJob());
    }

    private String getSelectedJob(){
        return jobListView.getSelectionModel().getSelectedItem();
    }
}
