package Application;

import com.opencsv.CSVWriter;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import com.opencsv.CSVReader;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Score {

    public static List<Player> players = new ArrayList<>();
    private static boolean isInit = false;

    public static void show(){
        if(!isInit){
            init();
        }
        AnchorPane root = new AnchorPane();
        Text header = new Text("Score Tabel");
        header.setFill(Color.WHITE);
        header.setStyle("-fx-font: 60 arials;");
        AnchorPane.setTopAnchor(header, 30.0);
        AnchorPane.setLeftAnchor(header, (double)(Game.XMAX + 230) / 4);
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
        for(Player pl : players){
            Text name = new Text(pl.getValue());
            name.setFill(Color.WHITE);
            name.setStyle("-fx-font: 20 arials;");
            Text score = new Text(pl.getKey().toString());
            score.setFill(Color.WHITE);
            score.setStyle("-fx-font: 20 arials;");
            tabelLeft.getChildren().add(name);
            tabelRight.getChildren().add(score);
        }
        AnchorPane.setLeftAnchor(tabelLeft, 40.0);
        AnchorPane.setTopAnchor(tabelLeft, 120.0);
        AnchorPane.setRightAnchor(tabelRight, 40.0);
        AnchorPane.setTopAnchor(tabelRight, 120.0);
        root.getChildren().addAll(header, tabelLeft, tabelRight);
    }

    public static void init(){
        CSVReader reader = null;
        FileReader s = null;
        try {
            s = new FileReader("score.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new CSVReader(s);

        String[] line = null;
        while(true) {
            try {
                if ((line = reader.readNext()) == null) break;
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
            players.add(new Player(Integer.parseInt(line[0]),line[1]));
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInit = true;
    }

    public static void add(Player plyer){
        if(!isInit){
            init();
        }

        players.add(plyer);
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player t1, Player t2) {
                return t2.getKey() - t1.getKey();
            }
        });

        if(players.size() > 10) {
            players.remove(10);
        }
    }

    public static void record(){
        if(isInit)
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("score.csv"), ',', ICSVWriter.NO_QUOTE_CHARACTER, ICSVWriter.NO_ESCAPE_CHARACTER,"\r\n");
            ArrayList<String[]> s = new ArrayList<>();
            players.forEach(e -> s.add(new String[]{e.getKey().toString(), e.getValue()}));
            writer.writeAll(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
