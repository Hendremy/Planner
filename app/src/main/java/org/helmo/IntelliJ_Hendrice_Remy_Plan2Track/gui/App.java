/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Main;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanningController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.LoadPlanningController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.MainController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.StaticPlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view.LoadTab;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view.MainWindow;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view.EditTab;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PlanningRepository repo = new StaticPlanningRepository();
        PlanningCreator creator = new PlanningCreator();
        ManagePlanning mainController = new MainController(repo, creator);
        MainWindow view = new MainWindow(mainController);

        Parent root = view.getParent();
        Scene scene = new Scene(root, 1200,800);

        primaryStage.setTitle("Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}