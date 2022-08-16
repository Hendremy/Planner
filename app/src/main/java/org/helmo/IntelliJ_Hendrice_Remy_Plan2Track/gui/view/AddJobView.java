package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AddJob;

/**
 * D�finit la vue JavaFX d'ajout d'une t�che.
 */
public class AddJobView {

    private final ObservableList<String> jobList;
    private final ManageJobsView manageJobsView;
    private final AddJob controller;

    private final Label jobInfoLabel = new Label("D�tails de la t�che");
    {
        jobInfoLabel.setUnderline(true);
    }
    private final Label nameLabel = new Label("Nom* :");
    private final TextField nameInput = new TextField();

    private final Label descriptionLabel = new Label("Description :");
    private final TextArea descriptionInput = new TextArea();

    private final Label durationLabel = new Label("Dur�e (en jours)* :");
    private final TextField durationInput = new TextField();
    {
        durationInput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    private final VBox jobForm = new VBox(
            jobInfoLabel,
            nameLabel, nameInput,
            durationLabel, durationInput,
            descriptionLabel, descriptionInput
            );

    private final Label priorJobsLabel = new Label("T�ches ant�rieures");
    {
        priorJobsLabel.setUnderline(true);
    }
    private final Label priorJobLabel = new Label("Nouvelle t�che :");
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

    private final Button createBtn = new Button("Cr�er la t�che");
    {
        createBtn.setOnAction(e -> addJob());
    }

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

    private final TitledPane root = new TitledPane("Cr�er une t�che", content);
    {
        root.minHeight(500);
        root.setCollapsible(false);
    }

    /**
     * Initialise la vue, la liste des t�ches, le controleur d'ajout de t�ches et la vue de gestion de montage parent.
     * @param jobList la liste des t�ches
     * @param controller le controleur d'ajout de t�ches
     * @param manageJobsView la vue de gestion de montage parent
     */
    public AddJobView(ObservableList<String> jobList, AddJob controller, ManageJobsView manageJobsView){
        this.manageJobsView = manageJobsView;
        this.jobList = jobList;
        this.controller = controller;
        priorJobsListView.setItems(this.jobList);
    }

    /**
     * Retourne la racine de la vue
     * @return la racine de la vue
     */
    public Parent getParent(){
        return root;
    }

    /**
     * Ajoute une t�che ant�rieure � la liste des t�ches ant�rieures.
     */
    private void addPriorJobToList(){
        String jobName = priorJobInput.getText().trim();
        if(!jobName.isBlank()){
            jobList.add(jobName);
            priorJobsListView.getSelectionModel().select(jobName);
            priorJobInput.clear();
        }
    }

    /**
     * Ajoute la t�che en cr�ation au montage.
     */
    private void addJob(){
        String name = nameInput.getText().trim();
        String description = descriptionInput.getText().trim();
        int duration = getDuration();
        Iterable<String> priorJobs = getSelectedJobs();

        if(!name.isBlank() && duration > 0){
            controller.addJobToPlanning(name, description, duration, priorJobs);
            manageJobsView.onJobAdded();
        }
    }

    /**
     * Retourne la dur�e encod�e par l'utilisateur.
     * @return la dur�e encod�e par l'utilisateur
     */
    private int getDuration(){
        try{
            return Integer.parseInt(durationInput.getText().trim());
        }catch(NumberFormatException ex){
            return -1;
        }
    }

    /**
     * Retourne les t�ches ant�rieures s�lectionn�es.
     * @return les t�ches ant�rieures s�lectionn�es
     */
    private Iterable<String> getSelectedJobs(){
        return priorJobsListView.getSelectionModel().getSelectedItems();
    }
}
