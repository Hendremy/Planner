package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

public class JobCreateWindow {

    private Label title = new Label("Créer une tâche");
    private Label nameLabel = new Label("Nom");
    private TextField name = new TextField();

    private Label durationLabel = new Label("Durée");
    private TextField duration = new TextField();
    {
        duration.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    private Label descriptionLabel = new Label("Description");
    private TextArea description = new TextArea();

    private Label jobsLabel = new Label("Tâches antérieures");
    private ListView<Job> jobs = new ListView<>();

    private Button createBtn = new Button("Créer");
    {
        createBtn.setOnAction(event -> create());
    }

    private BorderPane root = new BorderPane();
    {
        var left = new VBox();
        left.getChildren().add(nameLabel);
        left.getChildren().add(name);
        left.getChildren().add(durationLabel);
        left.getChildren().add(duration);
        left.getChildren().add(descriptionLabel);
        left.getChildren().add(description);
        root.setLeft(left);

        var right = new HBox();
        right.getChildren().add(jobsLabel);
        right.getChildren().add(jobs);
        root.setRight(right);
    }

    private void create(){

    }

}
