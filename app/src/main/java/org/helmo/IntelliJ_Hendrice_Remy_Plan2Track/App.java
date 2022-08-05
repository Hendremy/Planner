/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.MainController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.LoadController;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.StaticPlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view.MainWindow;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PlanningRepository repo = new StaticPlanningRepository();
        MainWindow view = new MainWindow(new MainController(repo), new LoadController());
        Parent root = view.getParent();
        Scene scene = new Scene(root, 300,275);

        primaryStage.setTitle("Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
