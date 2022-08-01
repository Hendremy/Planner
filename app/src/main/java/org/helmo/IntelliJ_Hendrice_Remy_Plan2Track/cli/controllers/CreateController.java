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
            if(!wantsOverride()){
                return existingPlanning;
            }
        }
        return createNewPlanning();
    }

    private boolean wantsOverride(){
        return console.askYesNo("Un planning est en cours de création, voulez-vous l'écraser ?");
    }

    private Planning createNewPlanning(){
        Planning planning = null;
        String name = console.askString("Nom du montage ?");
        if(name != null && !name.isBlank()){
            planning = new Planning(name);
        }
        return planning;
    }
}
