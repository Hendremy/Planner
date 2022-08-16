package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.MainView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanningController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.ScheduleGenerator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraphBuilder;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertMarginCalculator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTimeCalculator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.*;

/**
 * Définit la classe principale de l'application CLI
 */
public class Main {

    /**
     * Démarre l'application.
     * @param args paramètres éventuels
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initialise les différents objets services, le contrôleur principal et la vue principale et démarre l'application.
     */
    private static void launch(){
        UserParser userParser = new JSONUserParser();
        PlanningSerializer planningParser = new JSONPlanningSerializer();
        PlanningRepository repo = new JSONPlanningRepository(userParser, planningParser
                , "../json","users.json", "plannings");
        PlanningCreator creator = new PlanningCreator();
        PertSchedulePlanner schedulePlanner = new PertSchedulePlanner(new PertGraphBuilder(), new PertTimeCalculator(), new PertMarginCalculator());
        ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
        ManagePlanning mainController = new ManagePlanningController(repo, creator, schedulePlanner, scheduleGenerator);
        MainView mainView = new MainView(mainController);
        mainView.start();
    }

}
