package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers.MainController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Console console;
    private static Presenter presenter;
    private static MainController mainController;

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
        mainController = new MainController(console, presenter);
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
