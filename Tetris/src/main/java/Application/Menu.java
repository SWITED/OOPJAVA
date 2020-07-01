package Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Menu {

        public static void show(){

        VBox root = new VBox(20);
        Scene menuScene = new Scene(root,Game.XMAX + 230,Game.YMAX);
        Tetris.mainStage.setScene(menuScene);
        root.setStyle("-fx-background-color: rgb(45, 45, 50), rgb(60, 60, 65);");

        Button game = new Button("New game");
        Button option = new Button("Option");
        Button score = new Button("Score tabel");
        Button exit = new Button("Exit");

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(game, option, score, exit);

        VBox.setVgrow(game, Priority.ALWAYS);
        VBox.setVgrow(option, Priority.ALWAYS);
        VBox.setVgrow(score, Priority.ALWAYS);
        VBox.setVgrow(exit, Priority.ALWAYS);

        game.setMaxSize(100, 40);
        option.setMaxSize(100, 40);
        score.setMaxSize(100, 40);
        exit.setMaxSize(100, 40);

        game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Game.init();
            }
        });

        option.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Option.show();
            }
        });

        score.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Score.show();
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Score.record();
                System.exit(0);
            }
        });
    }
}
