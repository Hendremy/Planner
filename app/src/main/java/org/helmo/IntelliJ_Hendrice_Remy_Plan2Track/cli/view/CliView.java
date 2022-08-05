package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

public abstract class CliView {

    protected final Console console;
    protected final Presenter presenter;

    public CliView(){
        this.console = Console.getInstance();
        this.presenter = Presenter.getInstance();
    }
}
