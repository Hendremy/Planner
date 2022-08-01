package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;


public class MainController extends Controller{

    private final int exitNum = 0;
    private final CreateController createController;
    private final EditController editController;
    private Planning planning;

    public MainController (Console console, Presenter presenter){
        super(console, presenter);
        this.createController = new CreateController(console, presenter);
        this.editController = new EditController(console, presenter);
    }

    public void loop(){
        int choice = -1;
        while(choice != exitNum){
            displayPlanning();
            displayMenu();
            choice = this.console.readInt();
            handleChoice(choice);
        }
    }

    private void displayPlanning(){
        if(planning != null){
            console.println(presenter.presentPlanning(planning));
        }
    }

    private void displayMenu(){
        console.println("1. Créer un montage");
        console.println("2. Modifier le montage");
        console.println("0. Quitter");
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
        var newPlanning = createController.create(planning);
        if(newPlanning != null) planning = newPlanning;
    }

    private void editPlanning(){
        editController.edit(planning);
    }
}
