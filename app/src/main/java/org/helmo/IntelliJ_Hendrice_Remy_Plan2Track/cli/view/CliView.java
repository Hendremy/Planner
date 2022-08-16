package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

/**
 * Définit une vue Command Line Interface héritée des autres vues CLI. Propose des méthode de bases pour l'affichage et la récupération des données.
 */
public abstract class CliView {

    protected final Console console;
    protected final Presenter presenter;

    /**
     * Initialise la console et le présenteur de la vue
     */
    public CliView(){
        this.console = Console.getInstance();
        this.presenter = Presenter.getInstance();
    }
}
