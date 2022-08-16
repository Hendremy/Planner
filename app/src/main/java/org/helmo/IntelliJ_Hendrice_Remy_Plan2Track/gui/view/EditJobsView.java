package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;

/**
 * Définit la vue JavaFX de la modification des tâches d'un planning.
 */
public class EditJobsView implements ManageJobsView {

    private final EditPlanning controller;
    private final JobListView jobListView;
    private final ObservableList<JobViewModel> jobsObservable;
    private ObservableList<String> priorJobsObservable;

    private final BorderPane root = new BorderPane();
    {
        root.setPadding(new Insets(10));
    }

    /**
     * Initialise la vue, le controleur de modification de montage.
     * @param controller le controleur de modification de montage
     */
    public EditJobsView(EditPlanning controller){
        this.controller = controller;
        this.jobsObservable = FXCollections.observableArrayList(controller.getJobsViewModels());
        this.priorJobsObservable = FXCollections.observableArrayList(controller.getJobsNames());
        this.jobListView = new JobListView(jobsObservable, this);
        root.setLeft(jobListView.getParent());
        showAddJob();
    }

    /**
     * Retourne la racine de la vue.
     * @return la racine de la vue
     */
    public Parent getParent(){
        return root;
    }

    /**
     * Rafraîchit la liste des tâches du montage et montre le formulaire d'ajout d'une tâche.
     */
    @Override
    public void onJobAdded() {
        refreshJobList();
        showAddJob();
    }

    /**
     * Rafraîchit la liste des tâches du montage, retire la tâche supprimée et montre le formulaire d'ajout d'une tâche.
     * @param name le nom de la tâche à retirer
     */
    @Override
    public void onJobRemoved(String name){
        refreshJobList();
        if(name != null){
            priorJobsObservable.remove(name);
        }
        showAddJob();
    }

    /**
     * Rafraichît la liste des tâches du montage.
     */
    private void refreshJobList(){
        jobsObservable.clear();
        jobsObservable.addAll(controller.getJobsViewModels());
    }

    /**
     * Montre le formulaire d'ajout d'une tâche au montage.
     */
    @Override
    public void showAddJob() {
        priorJobsObservable = FXCollections.observableArrayList(controller.getJobsNames());
        AddJobView addJobView = new AddJobView(priorJobsObservable, controller.getAddJobController(), this);
        root.setCenter(addJobView.getParent());
    }

    /**
     * Montre la vue de suppression d'une tâche du montage.
     * @param name le nom de la tâche à supprimer
     */
    @Override
    public void showRemoveJob(String name) {
        new RemoveJobView(controller.getRemoveJobController(), name, this);
    }

    /**
     * Montre la vue d'assignation d'une tâche du montage.
     * @param name le nom de la tâche à assigner
     */
    @Override
    public void showAssignJob(String name) {
        JobViewModel jobViewModel = controller.getJobViewModel(name);
        if(jobViewModel != null){
            AssignJobView assignJobView = new AssignJobView(jobViewModel, controller.getAssignJobsController());
            root.setCenter(assignJobView.getParent());
        }
    }
}
