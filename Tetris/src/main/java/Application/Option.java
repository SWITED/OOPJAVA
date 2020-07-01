package Application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Option {
    public static KeyCode rotate = KeyCode.UP;
    public static KeyCode left = KeyCode.LEFT;
    public static KeyCode right = KeyCode.RIGHT;
    public static KeyCode down = KeyCode.DOWN;

    public static void show(){
        AnchorPane root = new AnchorPane();
        Text header = new Text("Option");
        header.setFill(Color.WHITE);
        header.setStyle("-fx-font: 60 arials;");
        AnchorPane.setTopAnchor(header, 30.0);
        AnchorPane.setLeftAnchor(header, (double)(Game.XMAX + 230) / 3);
        Scene menuScene = new Scene(root,Game.XMAX + 230,Game.YMAX);
        menuScene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE){
                Menu.show();
            }
        });
        Tetris.mainStage.setScene(menuScene);
        root.setStyle("-fx-background-color: rgb(45, 45, 50), rgb(60, 60, 65);");

        VBox tabelLeft = new VBox(30);
        VBox tabelRight = new VBox(30);
        tabelLeft.setAlignment(Pos.CENTER_LEFT);
        tabelRight.setAlignment(Pos.CENTER_RIGHT);

        Text up = new Text("UP");
        up.setFill(Color.WHITE);
        up.setStyle("-fx-font: 20 arials;");

        Text down = new Text("DOWN");
        down.setFill(Color.WHITE);
        down.setStyle("-fx-font: 20 arials;");

        Text left = new Text("LEFT");
        left.setFill(Color.WHITE);
        left.setStyle("-fx-font: 20 arials;");

        Text right = new Text("RIGHT");
        right.setFill(Color.WHITE);
        right.setStyle("-fx-font: 20 arials;");

        TextField actionUp = new TextField();
        actionUp.setPrefColumnCount(5);

        TextField actionDown = new TextField();
        actionDown.setPrefColumnCount(5);

        TextField actionLeft = new TextField();
        actionLeft.setPrefColumnCount(5);

        TextField actionRight = new TextField();
        actionRight.setPrefColumnCount(5);
        actionRight.setLayoutY(190);
        actionRight.setLayoutX(400);

        actionUp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                actionUp.setText(keyEvent.getCode().toString());
                rotate = keyEvent.getCode();          
            }
        });

        actionDown.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                actionDown.setText(keyEvent.getCode().toString());
                Option.down= keyEvent.getCode();
            }
        });

        actionRight.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                actionRight.setText(keyEvent.getCode().toString());
                Option.right = keyEvent.getCode();
            }
        });

        actionLeft.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                actionLeft.setText(keyEvent.getCode().toString());
                Option.left = keyEvent.getCode();
            }
        });

        tabelLeft.getChildren().addAll(up,  down, left, right);
        tabelRight.getChildren().addAll(actionUp, actionDown, actionLeft, actionRight);

        AnchorPane.setRightAnchor(tabelRight, 40.0);
        AnchorPane.setTopAnchor(tabelRight, 120.0);

        AnchorPane.setLeftAnchor(tabelLeft, 40.0);
        AnchorPane.setTopAnchor(tabelLeft, 120.0);
        root.getChildren().addAll(header, tabelLeft, tabelRight);
    }
}
