package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label zr1;

    @FXML
    private Label mx1;

    @FXML
    private Label zr2;

    @FXML
    private Label mx2;

    @FXML
    private Label zr3;

    @FXML
    private Label mx3;

    @FXML
    private Label rr1;

    @FXML
    private Label rr2;

    @FXML
    private Label rr3;

    @FXML
    private Label rr4;

    @FXML
    private Label rr5;

    @FXML
    private Label rr6;

    @FXML
    private ProgressBar br1;

    @FXML
    private ProgressBar br2;

    @FXML
    private ProgressBar br3;

    @FXML
    private ProgressBar br4;

    @FXML
    private Label ls;

    @FXML
    void initialize() {
        assert zr1 != null : "fx:id=\"zr1\" was not injected: check your FXML file 'gui.fxml'.";
        assert mx1 != null : "fx:id=\"mx1\" was not injected: check your FXML file 'gui.fxml'.";
        assert zr2 != null : "fx:id=\"zr2\" was not injected: check your FXML file 'gui.fxml'.";
        assert mx2 != null : "fx:id=\"mx2\" was not injected: check your FXML file 'gui.fxml'.";
        assert zr3 != null : "fx:id=\"zr3\" was not injected: check your FXML file 'gui.fxml'.";
        assert mx3 != null : "fx:id=\"mx3\" was not injected: check your FXML file 'gui.fxml'.";
        assert rr1 != null : "fx:id=\"rr1\" was not injected: check your FXML file 'gui.fxml'.";
        assert rr2 != null : "fx:id=\"rr2\" was not injected: check your FXML file 'gui.fxml'.";
        assert rr3 != null : "fx:id=\"rr3\" was not injected: check your FXML file 'gui.fxml'.";
        assert rr4 != null : "fx:id=\"rr4\" was not injected: check your FXML file 'gui.fxml'.";
        assert rr5 != null : "fx:id=\"rr5\" was not injected: check your FXML file 'gui.fxml'.";
    }

    public void setValue(Factory factory){
        zr1.setText(String.valueOf(0));
        zr2.setText(String.valueOf(0));
        zr3.setText(String.valueOf(0));
        mx1.setText(String.valueOf(factory.maxDetails1type));
        mx2.setText(String.valueOf(factory.maxDetails2type));
        mx3.setText(String.valueOf(factory.maxDetails3type));
        ls.setText(String.valueOf(0));
    }

    synchronized public void chandeLabel(Worker worker){
        switch (worker.type){
            case 0:
                rr1.setText(worker.getStateWorker().getString());
                break;
            case 1:
                rr2.setText(worker.getStateWorker().getString());
                break;
            case 2:
                rr3.setText(worker.getStateWorker().getString());
                break;
            case 3:
                rr4.setText(worker.getStateWorker().getString());
                break;
            case 4:
                rr5.setText(worker.getStateWorker().getString());
                break;
            case 5:
                rr6.setText(worker.getStateWorker().getString());
                break;
        }
    }

    public void changeBar1(int value){
        br1.setProgress(value/(double) Factory.maxDetails1type);
    }

    public void changeBar2(int value) {
        br2.setProgress(value / (double) Factory.maxDetails2type);
    }

    public void changeBar3(int value){
        br3.setProgress(value/(double) Factory.maxDetails3type);
    }

    public void changeBar4(int value){
        br4.setProgress(1 - 1/(Math.pow(value + 1, 0.2)));
    }

    public void changeLabelSold(int value){
        ls.setText(String.valueOf(value));
    }
}
