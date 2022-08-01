package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class EditNameController extends Controller{
    public EditNameController(Console console, Presenter presenter) {
        super(console, presenter);
    }

    public void editName(Planning planning){
        String name = console.askString("Nom du montage ?");
        if(name != null && !name.isBlank() && !name.isEmpty()){
            planning.setName(name);
        }
    }
}
