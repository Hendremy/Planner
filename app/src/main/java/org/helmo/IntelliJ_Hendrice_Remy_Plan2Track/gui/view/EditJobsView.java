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
    private ObservableList<String> priorJobsObservable;

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
        priorJobsObservable = FXCollections.observableArrayList(jobsObservable);
        AddJobView addJobView = new AddJobView(priorJobsObservable, this);
        root.setCenter(addJobView.getParent());
    }

    @Override
    public void removeJob(String name) {
        try{
            controller.getRemoveJobController().removeJob(name);
            refreshJobList();
            if(priorJobsObservable != null){
                priorJobsObservable.remove(name);
            }
        } catch(JobNotFoundException ex){
            new ErrorMessageView("La t�che s�lectionn�e n'a pas pu �tre supprim�e");
        }
    }

    @Override
    public void showAssignJob(String name) {
        AssignJobView assignJobView = new AssignJobView(name, null);
        root.setCenter(assignJobView.getParent());
    }

    @Override
    public void assignJob(String name) {

    }
}
