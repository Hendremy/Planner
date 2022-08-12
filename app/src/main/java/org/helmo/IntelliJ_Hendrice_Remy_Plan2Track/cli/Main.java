package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.MainView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.MainController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.ScheduleGenerator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraphBuilder;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertMarginCalculator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTimeCalculator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.JsonPlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;

public class Main {

    public static void main(String[] args) {
        launch();
    }

    private static void launch(){
        try{
            PlanningRepository repository = new JsonPlanningRepository();
            PlanningCreator creator = new PlanningCreator();
            PertSchedulePlanner schedulePlanner = new PertSchedulePlanner(new PertGraphBuilder(), new PertTimeCalculator(), new PertMarginCalculator());
            ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
            ManagePlanning mainController = new MainController(repository, creator, schedulePlanner, scheduleGenerator);
            MainView mainView = new MainView(mainController);
            mainView.start();
        }catch(PlanningRepositoryException ex){
            System.out.println(ex.getMessage());
        }
    }

}
