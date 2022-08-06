package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EditJobsView implements ManageJobsView {

    private final EditPlanning controller;
    private final JobListView jobListView;
    private final ObservableList<String> jobsObservable;

    public EditJobsView(EditPlanning controller){
        this.controller = controller;
        this.jobsObservable = FXCollections.observableArrayList(getJobs());
        this.jobListView = new JobListView(jobsObservable, this);
        root.setLeft(jobListView.getParent());
    }

    private Collection<String> getJobs(){
        Iterable<Job> jobsInPlanning = controller.getPlanning().getJobs();
        Set<String> jobsSet = new HashSet<>();
        jobsInPlanning.forEach(j -> jobsSet.add(j.getName()));
        return jobsSet;
    }

    private final BorderPane root = new BorderPane();
    {
        root.setPadding(new Insets(10));
    }

    public Parent getParent(){
        return root;
    }

    @Override
    public void addJob(String name, String description, int duration, Iterable<String> priorJobs) {
        controller.getAddJobController().addJobToPlanning(name, description, duration, priorJobs);
        refreshJobList();
        showAddJob();
    }

    private void refreshJobList(){
        jobsObservable.clear();
        jobsObservable.addAll(getJobs());
    }

    @Override
    public void showAddJob() {
        AddJobView addJobView = new AddJobView(jobsObservable, this);
        root.setCenter(addJobView.getParent());
    }

    @Override
    public void removeJob(String name) {
        try{
            controller.getRemoveJobController().removeJob(name);
            jobsObservable.remove(name);
        } catch(JobNotFoundException ex){
            //ErrorWindow
        }
    }

    @Override
    public void assignJob(String name) {

    }
}
