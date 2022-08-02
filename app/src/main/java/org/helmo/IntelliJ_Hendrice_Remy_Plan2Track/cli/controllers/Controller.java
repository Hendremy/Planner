package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas.PlanningRepository;

public abstract class Controller {

    protected final Console console;
    protected final Presenter presenter;
    protected final PlanningRepository repository;

    public Controller(Console console, Presenter presenter, PlanningRepository planningRepository){
        this.console = console;
        this.presenter = presenter;
        this.repository = planningRepository;
    }
}
