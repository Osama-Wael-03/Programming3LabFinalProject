package JavaLabFinalProject;

import View.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class JavaLabFinalProject extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManager.openMainPage();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
