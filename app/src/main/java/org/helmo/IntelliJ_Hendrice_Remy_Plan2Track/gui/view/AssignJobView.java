package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

public class AssignJobView {

    public AssignJobView(String jobName, Iterable<Technician> technicians){
        root.setText(jobName);
    }

    private final VBox content = new VBox();

    private final TitledPane root = new TitledPane("Assigner une tâche", content);
    {
        root.setCollapsible(false);
    }

    public Parent getParent(){
        return root;
    }
}
