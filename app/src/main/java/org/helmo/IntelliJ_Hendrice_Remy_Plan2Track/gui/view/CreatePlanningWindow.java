package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

/**
 * D�finit la fen�tre JavaFX de cr�ation d'un montage.
 */
public class CreatePlanningWindow {

    private final ManagePlanning mainController;
    private final MainWindow mainWindow;

    private final Stage stage = new Stage();
    private final Label messageLabel = new Label();
    {
        messageLabel.setStyle("-fx-text-fill: red");
        messageLabel.setWrapText(false);
    }
    private final Label nameLabel = new Label("Nom du planning");
    private final TextField nameInput = new TextField();
    private final Button createBtn = new Button("Cr�er");
    {
        createBtn.setOnAction(e -> createPlanning());
    }
    private final Button cancelBtn = new Button("Annuler");
    {
        cancelBtn.setOnAction(e -> cancel());
    }

    private final HBox buttonRow = new HBox();
    {
        buttonRow.getChildren().add(createBtn);
        buttonRow.getChildren().add(cancelBtn);

        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(100);
    }

    private final VBox root = new VBox();
    {
        root.getChildren().add(messageLabel);
        root.getChildren().add(nameLabel);
        root.getChildren().add(nameInput);
        root.getChildren().add(buttonRow);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setSpacing(8);
    }

    /**
     * Initialise la fen�tre, le contr�leur de gestion du planning, la fen�tre principale parent et le message �ventuel.
     * @param mainController le controleur de gestion du planning
     * @param mainWindow la fen�tre principale parent
     * @param message le message �ventuel
     */
    public CreatePlanningWindow(ManagePlanning mainController, MainWindow mainWindow, String message){
        this.mainController = mainController;
        this.mainWindow = mainWindow;
        if(message != null) this.messageLabel.setText(message);
        showView();
    }


    /**
     * Montre la fen�tre.
     */
    private void showView(){
        stage.initStyle(StageStyle.UTILITY);
        stage.setOnCloseRequest(e -> cancel());
        stage.setWidth(400);
        stage.setTitle("Cr�er un planning");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    /**
     * Cr�e le montage et r�tablit la fen�tre principale.
     */
    private void createPlanning(){
        String name = nameInput.getText().trim();
        if(!name.isBlank()){
            mainController.createPlanning(name);
            stage.hide();
            mainWindow.enable();
            mainWindow.setManageTabs();
        }
    }

    /**
     * Annule la cr�ation de montage et r�tablit la fen�tre principale.
     */
    private void cancel(){
        stage.hide();
        mainWindow.enable();
    }

}
