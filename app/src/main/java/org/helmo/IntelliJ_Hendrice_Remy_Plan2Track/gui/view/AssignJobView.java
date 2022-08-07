package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels.JobViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels.TechnicianViewModel;

public class AssignJobView {

    private final AssignJobs controller;
    private final JobViewModel jobViewModel;

    public AssignJobView(JobViewModel jobVM, AssignJobs controller){
        this.controller = controller;
        this.jobViewModel = jobVM;
    }

    private void setTexts(){
        nameLabel.setText(jobViewModel.getName());
        descriptionText.setText(jobViewModel.getDescription());
        durationText.setText(jobViewModel.getDuration());

        root.setText(String.format("%s - Assigner la tâche", jobViewModel.getName()));
    }

    private final Text nameLabel = new Text();
    private final Text descriptionText = new Text();
    private final Text durationText = new Text();

    private final ListView<TechnicianViewModel> techListView = new ListView<>();

    private final VBox content = new VBox();

    private final TitledPane root = new TitledPane("Assigner une tâche", content);
    {
        root.setCollapsible(false);
    }

    public Parent getParent(){
        return root;
    }
}
