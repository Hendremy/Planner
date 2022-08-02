package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers.MainController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas.StaticPlanningRepository;

import java.io.IOException;

public class Main {

    private static Console console;
    private static Presenter presenter;
    private static MainController mainController;
    private static PlanningRepository repository;

    public static void main(String[] args) {
        init();
        sayHello();
        loop();
        sayGoodbye();
        close();
    }

    private static void init(){
        console =  new Console();
        presenter = new Presenter();
        repository = new StaticPlanningRepository();
        mainController = new MainController(console, presenter, repository);
    }

    private static void loop(){
        mainController.loop();
    }

    private static void sayHello(){
        console.println("IN-B2-UE11-Java : Planner\n" +
                "=======================\n");
    }

    private static void sayGoodbye(){
        console.println("Exiting ... \n Goodbye !");
    }

    private static void close(){
        try{
            console.close();
        }catch(IOException ex) {
            System.out.println("Console failed to close");
        }
    }
}
