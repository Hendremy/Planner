package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view.MainView;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.StaticPlanningRepository;

public class Main {

    private static MainController mainController;
    private static MainView mainView;

    public static void main(String[] args) {
        launch();
    }

    private static void init(){
        PlanningRepository repository = new StaticPlanningRepository();
        mainController = new MainController(repository);
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
