package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

/**
 * D�finit une vue Command Line Interface h�rit�e des autres vues CLI. Propose des m�thode de bases pour l'affichage et la r�cup�ration des donn�es.
 */
public abstract class CliView {

    protected final Console console;
    protected final Presenter presenter;

    /**
     * Initialise la console et le pr�senteur de la vue
     */
    public CliView(){
        this.console = Console.getInstance();
        this.presenter = Presenter.getInstance();
    }
}
