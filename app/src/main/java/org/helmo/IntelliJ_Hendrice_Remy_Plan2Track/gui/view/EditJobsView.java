package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

public class EditJobsView implements ManageJobsView {

    private final EditPlanning controller;
    private final JobListView jobListView;
    private final ObservableList<Job> jobsObservable;

    public EditJobsView(EditPlanning controller){
        this.controller = controller;
        this.jobsObservable = getJobsObservable();
        this.jobListView = new JobListView(jobsObservable, this);
        root.setLeft(jobListView.getParent());
    }

    private ObservableList<Job> getJobsObservable(){
        ObservableList<Job> jobsObservable = FXCollections.observableArrayList();
        Iterable<Job> jobsInPlanning = controller.getPlanning().getJobs();
        jobsInPlanning.forEach(jobsObservable::add);
        return jobsObservable;
    }

    private final BorderPane root = new BorderPane();
    {
        root.setPadding(new Insets(10));
    }

    public Parent getParent(){
        return root;
    }

    @Override
    public void addJob() {
        var ctrl = controller.getAddJobController();
        ctrl.createJob("n","n",1);
        ctrl.addJobToPlanning();

        jobsObservable.clear();
        var obs = getJobsObservable();
        jobsObservable.addAll(obs);
    }

    @Override
    public void removeJob(String name) {

    }
}
