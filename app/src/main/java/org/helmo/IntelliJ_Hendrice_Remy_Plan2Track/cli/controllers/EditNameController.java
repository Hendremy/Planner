package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class EditNameController extends Controller{
    public EditNameController(Console console, Presenter presenter, PlanningRepository repository) {
        super(console, presenter, repository);
    }

    public void editName(Planning planning){
        String name = console.askString("Nom du montage ?");
        if(name != null && !name.isBlank() && !name.isEmpty()){
            planning.setName(name);
        }
    }
}
