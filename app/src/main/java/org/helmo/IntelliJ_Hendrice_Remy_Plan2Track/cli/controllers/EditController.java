package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class EditController extends Controller{

    private final int exitNum = 0;
    private Planning planning;

    public EditController(Console console, Presenter presenter) {
        super(console, presenter);
    }

    public void edit(Planning planning){
        if(planning != null){
            this.planning = planning;
            loop();
        }
    }

    private void loop(){
        int choice = -1;
        while(choice != exitNum){
            displayMenu();
            choice = console.readInt();
            handleChoice(choice);
        }
    }

    private void displayMenu(){
        console.println(
                    "1. Modifier le nom du montage"
                + "\n2. Ajouter une nouvelle tâche"
                + "\n3. Supprimer une tâche existante"
                + "\n0. Arrêter la modification"
        );
    }

    private void handleChoice(int choice){
        switch(choice){
            case 1:
                editName();
            case 2:
                addJob();
            case 3:
                removeJob();
            case exitNum:
                break;
            default:
                console.println("Choix invalide");
                break;
        }
    }

    private void editName(){

    }

    private void addJob(){

    }

    private void removeJob(){

    }
}
