package Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.*;

import java.util.Random;

public class Game {
    public static final int SIZE = 30;
    public static final int YMAX = SIZE * 24;
    public static final int XMAX = SIZE * 12;
    private static final int [] scoreLine = {100, 300, 700, 1500};
    public static final int MOVE = SIZE;
    public static int [][] MINE;
    private static int SCORE;
    private static boolean isClick = false;

    private static Pane root;
    private static Scene gameScene;
    private static boolean gameIsRun = false;

    private static Figure figure;
    private static Figure nextFigure;

    private static void go() {
        Random random = new Random();
        Line line = new Line(XMAX, 0, XMAX, YMAX);
        line.setStroke(Color.WHITE);
        Text scoreText = new Text("Score: " + SCORE);
        root.getChildren().addAll(line, scoreText);
        scoreText.setStyle("-fx-font: 20 arials;");
        scoreText.setFill(Color.WHITE);
        scoreText.setY(50);
        scoreText.setX(XMAX + 10);

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (figure.isTouchGround()) {
                            figure.leaveOnGround();
                            checkFilling();

                            scoreText.setText("Score: " + SCORE);
                            nextFigure.moveOnMine();
                            figure = nextFigure;
                            nextFigure = new Figure(random.nextInt(7));
                            root.getChildren().addAll(nextFigure.figure);

                            if(figure.isCrossMine()){
                                gameOver();

                                fall.cancel();
                            };
                        } else {
                            figure.stepDown();
                        }
                    }
                });
            }

        };
        fall.schedule(task,0,300);
    }

    private static void checkFilling(){
        int currentScore = 0;
        int k = 0;
        ArrayList<Rectangle> rec = new ArrayList<>();
        for(int i = 0; i < MINE.length - 1; ++i){
            int line = 0;
            for(int j = 0; j < MINE[0].length; ++j){
                if(MINE[i][j] == 1){line++;}
            }
            if(line == 12){
                currentScore = scoreLine[k];
                k++;
                for(Node node : root.getChildren()) {
                    if(node instanceof Rectangle && Math.round(((Rectangle) node).getY()/Game.SIZE) == i){
                        rec.add((Rectangle) node);
                    }
                    if(node instanceof Rectangle && Math.round(((Rectangle) node).getY()/Game.SIZE) < i){
                        ((Rectangle) node).setY( ((Rectangle) node).getY() + MOVE);
                    }
                }
                for(int p = i; p >= 0; --p) {
                    for (int j = 0; j < MINE[0].length; ++j) {
                        if(p != 0){MINE[p][j] = MINE[p - 1][j];}
                        else{
                            MINE[p][j] = 0;
                        }
                    }
                }

            }
        }
        root.getChildren().removeAll(rec);
        SCORE += currentScore;
    }

    public static void init(){
        Random random = new Random();
        MINE = new int[YMAX/SIZE + 1][XMAX/SIZE];
        SCORE = 0;
        gameIsRun = true;
        root = new Pane();
        gameScene = new Scene(root, XMAX + 230, YMAX);

        figure = new Figure(random.nextInt(7));
        nextFigure = new Figure(random.nextInt(7));
        Arrays.fill(MINE[YMAX / SIZE], 1);

        Tetris.mainStage.setScene(gameScene);

        root.setStyle("-fx-background-color: rgb(45, 45, 50), rgb(60, 60, 65);");
        root.getChildren().addAll(figure.figure);
        root.getChildren().addAll(nextFigure.figure);
        gameScene.setOnKeyPressed(e -> {
            if(e.getCode() == Option.rotate){
                figure.rotate();
            }
            if(e.getCode() == Option.down){
                figure.drop();
            }
            if(e.getCode() == Option.right){
                figure.moveRight();
            }
            if(e.getCode() == Option.left){
                figure.moveLeft();
            }
            if(e.getCode() == KeyCode.ESCAPE && !gameIsRun){
                Menu.show();
            }
        });

        figure.moveOnMine();

        go();
    }

    private static void gameOver(){
        gameIsRun = false;
        Text gameOver = new Text("GAME OVER");
        Text pressButtom = new Text("Press ESC to return to the menu");
        TextField tf = new TextField("");
        tf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!isClick) {
                    if (!tf.getText().equals("")) {
                        Score.add(new Player(SCORE, tf.getText()));
                    } else {
                        Score.add(new Player(SCORE, "noname"));
                    }
                    isClick = true;
                }
            }
        });
        tf.setPrefColumnCount(10);
        tf.setLayoutX(200);
        tf.setLayoutY(440);
        pressButtom.setFill(Color.BLACK);
        pressButtom.setStyle("-fx-font: 38 arial");
        pressButtom.setX(20);
        pressButtom.setY(YMAX/2.0 + 50);
        gameOver.setFill(Color.GRAY);
        gameOver.setStyle("-fx-font: 90 arial");
        gameOver.setX(20);
        gameOver.setY(YMAX/2.0);
        root.getChildren().addAll(gameOver,pressButtom, tf);
    }
}

