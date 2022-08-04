package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

import java.io.IOException;

public class MainView extends CliView {
    private final int exitNum = 0;
    private final ManagePlanning mainController;

    public MainView (ManagePlanning mainController){
        this.mainController = mainController;
    }

    public void start(){
        sayHello();
        loop();
        close();
    }

    private void loop(){
        int choice = -1;
        while(choice != exitNum){
            displayPlanning();
            choice = askMenu();
            handleChoice(choice);
        }
    }

    private void displayPlanning(){
        if(getPlanning() != null){
            console.println(presenter.presentPlanning(getPlanning()));
        }
    }

    private int askMenu(){
        return console.askPosInt(
                "1. Créer un montage"
                        + "\n2. Modifier le montage"
                        + "\n0. Quitter"
        );
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createPlanning();
                break;
            case 2:
                editPlanning();
                break;
            case exitNum:
                break;
            default:
                console.print("Choix invalide");
                break;
        }
    }

    private void createPlanning(){
        if(getPlanning() != null && wantsOverride()){

        }
        createNewPlanning();
    }

    private boolean wantsOverride(){
        return console.askYesNo("Un planning est en cours de création, voulez-vous l'écraser ?");
    }

    private void createNewPlanning(){
        String name = console.askString("Nom du montage ?");
        if(!name.isBlank()){
            mainController.createPlanning(name);
        }
    }

    private void editPlanning(){
        mainController.editPlanning();
    }

    private Planning getPlanning() {
        return mainController.getPlanning();
    }

    private void sayHello(){
        console.println("IN-B2-UE11-Java : Planner\n" +
                "=======================\n");
    }

    private void close(){
        console.println("Exiting ... \n Goodbye !");
        try{
            console.close();
        }catch(IOException ex) {
            System.out.println("Console failed to close");
        }
    }
}
