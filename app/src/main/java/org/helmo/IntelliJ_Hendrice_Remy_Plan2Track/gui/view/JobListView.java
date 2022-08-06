package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

public class JobListView {

    private final ObservableList<Job> jobList;
    private final ManageJobsView manageJobsView;

    public JobListView(ObservableList<Job> jobList, ManageJobsView manageJobsView){
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

    private final ListView<Job> jobListView = new ListView<>();

    private final VBox root = new VBox(buttonRow, jobListView);
    {
        root.setMinWidth(250);
        root.setSpacing(8);
        root.setAlignment(Pos.CENTER_LEFT);
    }

    public Parent getParent(){
        return root;
    }

    private void addJob(){
        manageJobsView.addJob();
    }

    private void removeJob(){
        manageJobsView.removeJob("");
    }
}
