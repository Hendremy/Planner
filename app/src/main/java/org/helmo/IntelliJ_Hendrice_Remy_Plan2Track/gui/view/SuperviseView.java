package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.SupervisePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobProgressViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.ScheduleRowViewModel;

public class SuperviseView {
    private final SupervisePlanning supervisePlanning;
    private final PlanningProgress planningProgress;
    private final ObservableList<JobProgressViewModel> jobPVMs;

    private final Label planningName = new Label();
    {
        planningName.setStyle("-fx-font-weight: bold");
        planningName.setStyle("-fx-font-size: 1.5em");
    }
    private final Label delay = new Label();
    private HBox title = new HBox(planningName, delay);
    {
        title.setPadding(new Insets(10));
        title.setSpacing(10);
        title.setAlignment(Pos.CENTER);
    }

    private Label jobsTableLabel = new Label("Avancement des tâches");

    private final TableView<JobProgressViewModel> jobsProgressTableView = new TableView<>();

    private final TableColumn<JobProgressViewModel, String> jobNameCol = new TableColumn<>("Tâche");
    {
        jobNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        jobNameCol.prefWidthProperty().bind(jobsProgressTableView.widthProperty().multiply(0.2));
        jobNameCol.setResizable(false);
        jobsProgressTableView.getColumns().add(jobNameCol);
    }
    private final TableColumn<JobProgressViewModel, String> expStartDateCol = new TableColumn<>("Début planifié");
    {
        expStartDateCol.setCellValueFactory(new PropertyValueFactory<>("expectedStartDate"));
        expStartDateCol.prefWidthProperty().bind(jobsProgressTableView.widthProperty().multiply(0.2));
        expStartDateCol.setResizable(false);
        jobsProgressTableView.getColumns().add(expStartDateCol);
    }
    private final TableColumn<JobProgressViewModel, String> expEndDateCol = new TableColumn<>("Fin planifiée");
    {
        expEndDateCol.setCellValueFactory(new PropertyValueFactory<>("expectedEndDate"));
        expEndDateCol.prefWidthProperty().bind(jobsProgressTableView.widthProperty().multiply(0.2));
        expEndDateCol.setResizable(false);
        jobsProgressTableView.getColumns().add(expEndDateCol);
    }
    private final TableColumn<JobProgressViewModel, String> statusCol = new TableColumn<>("Statut");
    {
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.prefWidthProperty().bind(jobsProgressTableView.widthProperty().multiply(0.2));
        statusCol.setResizable(false);
        jobsProgressTableView.getColumns().add(statusCol);
    }
    private final TableColumn<JobProgressViewModel, String> isOnTimeCol = new TableColumn<>("Retard ?");
    {
        isOnTimeCol.setCellValueFactory(new PropertyValueFactory<>("isOnTime"));
        isOnTimeCol.prefWidthProperty().bind(jobsProgressTableView.widthProperty().multiply(0.2));
        isOnTimeCol.setResizable(false);
        jobsProgressTableView.getColumns().add(isOnTimeCol);
    }

    private final VBox jobsBox = new VBox(jobsTableLabel, jobsProgressTableView);
    {
        jobsBox.setPadding(new Insets(20));
    }

    private final BorderPane root = new BorderPane();
    {
        root.setTop(title);
        root.setCenter(jobsBox);
    }

    public Parent getParent(){
        return root;
    }

    public SuperviseView(SupervisePlanning supervisePlanning, PlanningProgress planningProgress){
        this.supervisePlanning = supervisePlanning;
        this.planningProgress = planningProgress;
        jobPVMs = supervisePlanning.getJobProgressViewModels(planningProgress);
        jobsProgressTableView.setItems(jobPVMs);
        planningName.setText(planningProgress.getName());
        delay.setText(String.format("%d jour(s) de retard", planningProgress.getDelay()));
    }

}
