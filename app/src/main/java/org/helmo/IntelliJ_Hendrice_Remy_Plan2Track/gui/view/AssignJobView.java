package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.Collection;

public class AssignJobView {

    private final AssignJobs controller;
    private final JobViewModel jobViewModel;

    public AssignJobView(JobViewModel jobVM, AssignJobs controller){
        this.controller = controller;
        this.jobViewModel = jobVM;
        setTexts();
        setPriorListView();
        trySetTechListView();
    }

    private void setTexts(){
        nameText.setText(jobViewModel.getName());
        descriptionText.setText(jobViewModel.getDescription());
        durationText.setText(jobViewModel.getDuration());
        root.setText(String.format("%s - Assigner la tâche", jobViewModel.getName()));
    }

    private void setPriorListView(){
        Collection<JobViewModel> priorJobVMs = jobViewModel.getPriorJobs();
        ObservableList<JobViewModel> priorJobList = FXCollections.observableArrayList(priorJobVMs);
        priorJobListView.setItems(priorJobList);
    }

    private void trySetTechListView(){
        try{
            setTechListView();
        }catch(PlanningRepositoryException ex){
            new ErrorMessageWindow(ex.getMessage());
        }
    }

    private void setTechListView() throws PlanningRepositoryException {
        Collection<TechnicianViewModel> techVMs = controller.getTechniciansViewModels();
        ObservableList<TechnicianViewModel> techObsList = FXCollections.observableArrayList(techVMs);
        techListView.setItems(techObsList);
        if(jobViewModel.getTechCode() != null){
            TechnicianViewModel tech = findTechnician(techVMs, jobViewModel.getTechCode());
            if(tech != null){
                techListView.getSelectionModel().select(tech);
            }
        }
    }

    private TechnicianViewModel findTechnician(Collection<TechnicianViewModel> techVms, String code){
        for(var techVm : techVms){
            if(techVm.getCode().equals(code)){
                return techVm;
            }
        }
        return null;
    }

    private final Label jobInfoLabel = new Label("Détails de la tâche");
    {
        jobInfoLabel.setUnderline(true);
    }
    private final Label nameLabel = new Label("Nom :");
    private final Text nameText = new Text();

    private final Label descriptionLabel = new Label("Description :");
    private final Text descriptionText = new Text();

    private final Label durationLabel = new Label("Durée (en jours) :");
    private final Text durationText = new Text();

    private final VBox jobInfo = new VBox(
            jobInfoLabel,
            nameLabel, nameText,
            descriptionLabel, descriptionText,
            durationLabel, durationText);
    {
        jobInfo.setSpacing(8);
    }

    private final Label priorJobsLabel = new Label("Tâches antérieures");
    {
        priorJobsLabel.setUnderline(true);
    }
    private final ListView<JobViewModel> priorJobListView = new ListView<>();
    {
        priorJobListView.setMouseTransparent(true);
        priorJobListView.setFocusTraversable(false);
    }

    private final VBox priorJobsCol = new VBox(priorJobsLabel, priorJobListView);
    {
        priorJobsCol.setSpacing(8);
    }

    private final Label techLabel = new Label("Chef d'équipe assigné");
    {
        techLabel.setUnderline(true);
    }

    private final Text techPH = new Text("Sélectionner le chef d'équipe");
    private final ComboBox<TechnicianViewModel> techListView = new ComboBox<>();
    {
        techListView.setPlaceholder(techPH);
    }
    private final Button assignBtn = new Button("Assigner le chef d'équipe");
    {
        assignBtn.setOnAction(e -> assignJob());
    }

    private final Label resultMsg = new Label();
    {
        resultMsg.setVisible(false);
    }

    private final VBox techList = new VBox(techLabel, techListView, assignBtn, resultMsg);
    {
        techList.setSpacing(8);
    }

    private final HBox content = new HBox(jobInfo, priorJobsCol, techList);
    {
        content.setSpacing(8);
        content.setPadding(new Insets(10));
    }

    private final TitledPane root = new TitledPane("Assigner une tâche", content);
    {
        root.setMinHeight(500);
        root.setCollapsible(false);
    }

    public Parent getParent(){
        return root;
    }

    private void assignJob(){
        TechnicianViewModel selected = techListView.getSelectionModel().getSelectedItem();
        if(selected != null){
            try{
                controller.assignJob(jobViewModel.getName(), selected.getCode());
                showMessageSuccess();
            }catch(PlanningRepositoryException ex){
                new ErrorMessageWindow(ex.getMessage());
            }
        }else{
            showMessageError();
        }
    }

    private void showMessageSuccess(){
        resultMsg.setText("Modification enregistrée !");
        resultMsg.setStyle("-fx-text-fill: green");
        resultMsg.setVisible(true);
    }

    private void showMessageError(){
        resultMsg.setText("Sélectionnez un chef d'équipe pour l'assigner à la tâche");
        resultMsg.setStyle("-fx-text-fill: red");
        resultMsg.setVisible(true);
    }
}
