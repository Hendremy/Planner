package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class AddJobView {

    private final ObservableList<String> jobList;
    private final ManageJobsView manageJobsView;

    public AddJobView(ObservableList<String> jobList, ManageJobsView manageJobsView){
        this.manageJobsView = manageJobsView;
        this.jobList = FXCollections.observableArrayList(jobList);
        priorJobsListView.setItems(this.jobList);
    }

    private final Label nameLabel = new Label("Nom* :");
    private final TextField nameInput = new TextField();

    private final Label descriptionLabel = new Label("Description :");
    private final TextField descriptionInput = new TextField();

    private final Label durationLabel = new Label("Durée (en jours)* :");
    private final TextField durationInput = new TextField();
    {
        durationInput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    private final Label priorJobsLabel = new Label("Tâches antérieures :");
    private final Label priorJobLabel = new Label("Nouvelle tâche :");
    private final TextField priorJobInput = new TextField();
    private final Button addJobBtn = new Button("Ajouter +");
    {
        addJobBtn.setOnAction(e -> addPriorJobToList());
    }

    private final ListView<String> priorJobsListView = new ListView<>();
    {
        MultipleSelectionModel<String> selectModel = priorJobsListView.getSelectionModel();
        selectModel.setSelectionMode(SelectionMode.MULTIPLE);
    }

    private final Button createBtn = new Button("Créer la tâche");
    {
        createBtn.setOnAction(e -> addJob());
    }

    private final VBox jobForm = new VBox(
            nameLabel, nameInput,
            descriptionLabel, descriptionInput,
            durationLabel, durationInput);

    private final HBox newPriorJobRow = new HBox(priorJobLabel, priorJobInput, addJobBtn);
    {
        newPriorJobRow.setSpacing(8);
    }

    private final VBox priorJobsForm = new VBox(
            priorJobsLabel,
            newPriorJobRow,
            priorJobsListView,
            createBtn
    );

    private final HBox content = new HBox(jobForm, priorJobsForm);
    {
        jobForm.setSpacing(8);
        priorJobsForm.setSpacing(8);
        content.setSpacing(8);
        content.setPadding(new Insets(8));
    }

    private final TitledPane root = new TitledPane("Créer une tâche", content);
    {
        root.setCollapsible(false);
    }

    public Parent getParent(){
        return root;
    }

    private void addPriorJobToList(){
        String jobName = priorJobInput.getText().trim();
        if(!jobName.isBlank()){
            jobList.add(jobName);
            priorJobsListView.getSelectionModel().select(jobName);
            priorJobInput.clear();
        }
    }

    private void addJob(){
        String name = nameInput.getText().trim();
        String description = descriptionInput.getText().trim();
        int duration = getDuration();
        Iterable<String> priorJobs = getSelectedJobs();

        if(!name.isBlank() && duration > 0){
            manageJobsView.addJob(name, description, duration, priorJobs);
        }
    }

    private int getDuration(){
        try{
            return Integer.parseInt(durationInput.getText().trim());
        }catch(NumberFormatException ex){
            return -1;
        }
    }

    private Iterable<String> getSelectedJobs(){
        return priorJobsListView.getSelectionModel().getSelectedItems();
    }
}
