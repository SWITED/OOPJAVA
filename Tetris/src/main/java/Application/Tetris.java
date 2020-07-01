package Application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Tetris extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        stage.setResizable(false);
        stage.setTitle("Tetris");
        stage.show();
        stage.setX(400);
        stage.setY(20);
        stage.setOnCloseRequest(e -> {
            Score.record();
            System.exit(0);
            });
        Menu.show();
    }
}