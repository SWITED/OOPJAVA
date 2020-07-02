package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, root.getLayoutX(), root.getLayoutY()));

        Controller controller = loader.getController();

        Factory factory = new Factory(controller);

        controller.setValue(factory);
        factory.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
