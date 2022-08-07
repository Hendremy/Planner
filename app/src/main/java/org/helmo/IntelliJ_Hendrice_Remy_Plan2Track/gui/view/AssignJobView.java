package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels.JobViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels.TechnicianViewModel;

import java.util.Collection;

public class AssignJobView {

    private final AssignJobs controller;
    private final JobViewModel jobViewModel;

    public AssignJobView(JobViewModel jobVM, AssignJobs controller){
        this.controller = controller;
        this.jobViewModel = jobVM;
        setTexts();
        setTechListView();
    }

    private void setTexts(){
        nameText.setText(jobViewModel.getName());
        descriptionText.setText(jobViewModel.getDescription());
        durationText.setText(jobViewModel.getDuration());
        root.setText(String.format("%s - Assigner la tâche", jobViewModel.getName()));
    }

    private void setTechListView(){
        Collection<TechnicianViewModel> techVMs = controller.getTechniciansViewModels();
        ObservableList<TechnicianViewModel> techObsList = FXCollections.observableArrayList(techVMs);
        techListView.setItems(techObsList);
        if(jobViewModel.getTechCode() != null){
            techListView.getSelectionModel().select(findTechnician(techVMs, jobViewModel.getTechCode()));
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

    private final Text nameText = new Text();
    private final Text descriptionText = new Text();
    private final Text durationText = new Text();

    private final VBox jobInfo = new VBox(nameText, descriptionText, durationText);

    private final Label techLabel = new Label("Chef d'équipe assigné : ");
    private final ComboBox<TechnicianViewModel> techListView = new ComboBox<>();

    private final VBox techList = new VBox(techLabel, techListView);

    private final BorderPane content = new BorderPane();
    {
        content.setLeft(jobInfo);
        content.setRight(techList);
    }

    private final TitledPane root = new TitledPane("Assigner une tâche", content);
    {
        root.setCollapsible(false);
    }

    public Parent getParent(){
        return root;
    }
}
