package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;

public abstract class CliView {

    protected final Console console;
    protected final Presenter presenter;

    public CliView(){
        this.console = Console.getInstance();
        this.presenter = Presenter.getInstance();
    }
}
