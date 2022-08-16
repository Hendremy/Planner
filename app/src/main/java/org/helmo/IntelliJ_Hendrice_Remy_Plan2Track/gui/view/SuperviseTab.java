package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.control.Tab;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.SupervisePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;

/**
 * Définit l'onglet JavaFX de la consultation d'avancement de montage.
 */
public class SuperviseTab extends Tab {
    public SuperviseTab(SupervisePlanning controller, PlanningProgress planningProgress) {
        SuperviseView superviseView = new SuperviseView(controller, planningProgress);
        setContent(superviseView.getParent());
        setClosable(false);
        setText("Avancement");
    }
}
