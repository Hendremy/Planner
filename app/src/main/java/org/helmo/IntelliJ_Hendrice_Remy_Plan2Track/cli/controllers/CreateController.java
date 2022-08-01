package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class CreateController extends Controller{

    public CreateController(Console console, Presenter presenter){
        super(console, presenter);
    }

    public Planning create(){
        Planning planning = null;
        console.println("Nom du montage ?");
        String name = console.readLine();
        if(name != null && !name.isBlank() && !name.isEmpty()){
            planning = new Planning(name);
        }
        return planning;
    }
}
