package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.MainView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.MainController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningCreator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.StaticPlanningRepository;

public class Main {

    private static ManagePlanning mainController;
    private static MainView mainView;

    public static void main(String[] args) {
        launch();
    }

    private static void init(){
        PlanningRepository repository = new StaticPlanningRepository();
        PlanningCreator creator = new PlanningCreator();
        mainController = new MainController(repository, creator);
        mainView = new MainView(mainController);
    }

    private static void loop(){
        mainView.start();
    }

    private static void launch(){
        init();
        loop();
    }

}
