package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class CreateController extends Controller{

    public CreateController(Console console, Presenter presenter){
        super(console, presenter);
    }

    public Planning create(Planning existingPlanning){
        if(existingPlanning != null){
            if(askWantsOverride().equals("N")){
                return existingPlanning;
            }
        }
        return createNewPlanning();
    }

    private String askWantsOverride(){
        String choice = "";
        while(!choice.equals("O") && !choice.equals("N")){
            console.println("Un planning est en cours de création, voulez-vous l'écraser ?" +
                    "\n (O)ui / (N)on");
            choice = console.readLine();
        }
        return choice;
    }

    private Planning createNewPlanning(){
        Planning planning = null;
        console.println("Nom du montage ?");
        String name = console.readLine();
        if(name != null && !name.isBlank() && !name.isEmpty()){
            planning = new Planning(name);
        }
        return planning;
    }
}
