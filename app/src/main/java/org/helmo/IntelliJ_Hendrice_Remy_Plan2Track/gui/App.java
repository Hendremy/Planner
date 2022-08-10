/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.MainController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.ScheduleGenerator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraphBuilder;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertMarginCalculator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTimeCalculator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.StaticPlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view.MainWindow;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PlanningRepository repo = new StaticPlanningRepository();
        PlanningCreator creator = new PlanningCreator();
        PertSchedulePlanner schedulePlanner = new PertSchedulePlanner(new PertGraphBuilder(), new PertTimeCalculator(), new PertMarginCalculator());
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
        ManagePlanning mainController = new MainController(repo, creator, schedulePlanner, scheduleGenerator);
        MainWindow view = new MainWindow(primaryStage, mainController);
        view.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
