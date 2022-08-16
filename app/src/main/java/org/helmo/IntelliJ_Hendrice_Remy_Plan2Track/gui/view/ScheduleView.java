package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;

/**
 * Définit la vue JavaFX de plannification du montage.
 */
public class ScheduleView {

    private final PlanSchedule controller;

    /**
     * Initialise la vue et le controleur de plannification du montage.
     * @param controller le controleur de plannification du montage
     */
    public ScheduleView(PlanSchedule controller){
        this.controller = controller;
        criticalPathView = new CriticalPathView(controller);
        scheduleGenerationView = new ScheduleGenerationView(controller);
        root.setLeft(criticalPathView.getParent());
        root.setCenter(scheduleGenerationView.getParent());
        updateNameLabel();
    }

    private final Label nameLabel = new Label();

    private final HBox nameArea = new HBox();
    {
        nameArea.setAlignment(Pos.CENTER);
        nameArea.getChildren().add(nameLabel);
        nameArea.setSpacing(8);
        nameArea.setPadding(new Insets(10));
    }

    private final CriticalPathView criticalPathView;
    private final ScheduleGenerationView scheduleGenerationView;

    private void updateNameLabel(){
        String name = controller.getPlanningName();
        try{
            setNameLabel(name, controller.getEarliestEndDate());
        } catch (PertException e) {
            new ErrorMessageWindow(e.getMessage());
        }
    }

    private void setNameLabel(String name, int duration){
        String durationText;
        if(duration == 0){
            durationText = "Aucune tâche à terminer";
        }else{
            durationText = String.format("Fin au plus tôt : %d jours après la date de début", duration);
        }
        nameLabel.setText(String.format("%s - %s", name, durationText));
    }

    public BorderPane root = new BorderPane();
    {
        root.setTop(nameArea);
        root.setPadding(new Insets(20));
    }

    /**
     * Retourne la racine de la vue.
     * @return la racine de la vue
     */
    public Parent getParent(){
        return root;
    }
}
