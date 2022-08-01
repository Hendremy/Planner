package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;

public abstract class Controller {

    protected final Console console;
    protected final Presenter presenter;

    public Controller(Console console, Presenter presenter){
        this.console = console;
        this.presenter = presenter;
    }
}
