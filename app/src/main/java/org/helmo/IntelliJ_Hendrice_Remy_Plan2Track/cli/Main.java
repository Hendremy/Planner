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
 * D�finit la classe principale de l'application CLI
 */
public class Main {

    /**
     * D�marre l'application.
     * @param args param�tres �ventuels
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initialise les diff�rents objets services, le contr�leur principal et la vue principale et d�marre l'application.
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
