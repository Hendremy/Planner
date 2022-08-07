package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
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
        setTechListView();
    }

    private void setTexts(){
        nameText.setText(jobViewModel.getName());
        descriptionText.setText(jobViewModel.getDescription());
        durationText.setText(jobViewModel.getDuration());
        root.setText(String.format("%s - Assigner la t�che", jobViewModel.getName()));
    }

    private void setPriorListView(){
        Collection<JobViewModel> priorJobVMs = jobViewModel.getPriorJobs();
        ObservableList<JobViewModel> priorJobList = FXCollections.observableArrayList(priorJobVMs);
        priorJobListView.setItems(priorJobList);
    }

    private void setTechListView(){
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

    private final Label jobInfoLabel = new Label("D�tails de la t�che");
    {
        jobInfoLabel.setUnderline(true);
    }
    private final Label nameLabel = new Label("Nom :");
    private final Text nameText = new Text();

    private final Label descriptionLabel = new Label("Description :");
    private final Text descriptionText = new Text();

    private final Label durationLabel = new Label("Dur�e (en jours) :");
    private final Text durationText = new Text();

    private final VBox jobInfo = new VBox(
            jobInfoLabel,
            nameLabel, nameText,
            descriptionLabel, descriptionText,
            durationLabel, durationText);
    {
        jobInfo.setSpacing(8);
    }

    private final Label priorJobsLabel = new Label("T�ches ant�rieures");
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

    private final Label techLabel = new Label("Chef d'�quipe assign�");
    {
        techLabel.setUnderline(true);
    }

    private final ComboBox<TechnicianViewModel> techListView = new ComboBox<>();
    private final Button assignBtn = new Button("Assigner le chef d'�quipe");
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
        content.setSpacing(12);
        content.setPadding(new Insets(10));
        content.setAlignment(Pos.TOP_LEFT);
    }

    private final TitledPane root = new TitledPane("Assigner une t�che", content);
    {
        root.setCollapsible(false);
    }

    public Parent getParent(){
        return root;
    }

    private void assignJob(){
        TechnicianViewModel selected = techListView.getSelectionModel().getSelectedItem();
        if(selected != null){
            controller.assignJob(jobViewModel.getName(), selected.getCode());
            showMessageSuccess();
        }else{
            showMessageError();
        }
    }

    private void showMessageSuccess(){
        resultMsg.setText("Modification enregistr�e !");
        resultMsg.setStyle("-fx-text-fill: green");
        resultMsg.setVisible(true);
    }

    private void showMessageError(){
        resultMsg.setText("S�lectionnez un chef d'�quipe pour l'assigner � la t�che");
        resultMsg.setStyle("-fx-text-fill: red");
        resultMsg.setVisible(true);
    }
}
