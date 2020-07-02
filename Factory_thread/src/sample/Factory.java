package sample;

import javafx.application.Platform;

import java.util.ArrayList;

public class Factory {
    private int curentDetails1type = 0;
    private int curentDetails2type = 0;
    private int curentDetails3type = 0;

    public static final int maxDetails1type = 1350;
    public static final int maxDetails2type = 500;
    public static final int maxDetails3type = 700;

    private int countSoldProduct = 0;
    private int completeProduct = 0;

    public static final int NEEDED1 = 10;
    public static final int NEEDED2 = 20;
    public static final int NEEDED3 = 15;

    private Controller controller;

    private ArrayList<Worker> listWorker= new ArrayList<Worker>();

    Factory(Controller controller){
        this.controller = controller;
    }

    public void start(){
        listWorker.add(new Worker(0, this, controller));
        listWorker.add(new Worker(1, this, controller));
        listWorker.add(new Worker(2, this, controller));
        listWorker.add(new Worker(3, this, controller));
        listWorker.add(new Worker(4, this, controller));
        listWorker.add(new Worker(5, this, controller));
        for(Thread e : listWorker){
            e.start();
        }
    }

    synchronized public void takeDetails1(){
        while(curentDetails1type < NEEDED1){
            try {
                listWorker.get(4).setStateWorker(Worker.State.WAIT);
                Platform.runLater(() -> {
                    controller.chandeLabel(listWorker.get(4));
                });
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        curentDetails1type -= NEEDED1;
        notifyAll();
        Platform.runLater(() -> {
            controller.changeBar1(curentDetails1type);
        });
    }

    synchronized public void takeDetails2(){
        while(curentDetails2type < NEEDED2){
            try {
                listWorker.get(4).setStateWorker(Worker.State.WAIT);
                Platform.runLater(() -> {
                    controller.chandeLabel(listWorker.get(4));
                });
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        curentDetails2type -= NEEDED2;
        notifyAll();
        Platform.runLater(() -> {
            controller.changeBar2(curentDetails2type);
        });
    }

    synchronized public void takeDetails3(){
        while(curentDetails3type < NEEDED3){
            try {
                listWorker.get(4).setStateWorker(Worker.State.WAIT);
                Platform.runLater(() -> {
                    controller.chandeLabel(listWorker.get(4));
                });
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        curentDetails3type -= NEEDED3;
        notifyAll();
        Platform.runLater(() -> {
            controller.changeBar3(curentDetails3type);
        });
    }

    synchronized public void addDetails1(){
        while(curentDetails1type >= maxDetails1type){
            try {
                listWorker.get(0).setStateWorker(Worker.State.WAIT);
                Platform.runLater(() -> {
                    controller.chandeLabel(listWorker.get(0));
                });
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        curentDetails1type ++;
        notifyAll();
        Platform.runLater(() -> {
            controller.changeBar1(curentDetails1type);
        });
    }

    synchronized public void addDetails2(){
        while(curentDetails2type >= maxDetails2type){
            try {
                listWorker.get(1).setStateWorker(Worker.State.WAIT);
                Platform.runLater(() -> {
                    controller.chandeLabel(listWorker.get(1));
                });
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        curentDetails2type ++;
        notifyAll();
        Platform.runLater(() -> {
            controller.changeBar2(curentDetails2type);
        });
    }
    synchronized public void addDetails3(){
        while(curentDetails3type >= maxDetails3type){
            try {
                listWorker.get(2).setStateWorker(Worker.State.WAIT);
                listWorker.get(3).setStateWorker(Worker.State.WAIT);
                Platform.runLater(() -> {
                    controller.chandeLabel(listWorker.get(2));
                    controller.chandeLabel(listWorker.get(3));
                });
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        curentDetails3type ++;
        notifyAll();
        Platform.runLater(() -> {
            controller.changeBar3(curentDetails3type);
        });
    }
    synchronized public void addProduct(){
        completeProduct ++;
        Platform.runLater(() -> {
            controller.changeBar4(completeProduct);
        });
        notifyAll();
    }

    synchronized public void saleProduct(){
        while(completeProduct == 0){
            try {
                listWorker.get(5).setStateWorker(Worker.State.WAIT);
                Platform.runLater(() -> {
                    controller.chandeLabel(listWorker.get(5));
                });
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        completeProduct --;
        countSoldProduct ++;
        notifyAll();
        Platform.runLater(() -> {
            controller.changeLabelSold(countSoldProduct);
        });
    }

    public int getCurentDetails1type() {
        return curentDetails1type;
    }

    public int getCurentDetails2type() {
        return curentDetails2type;
    }

    public int getCurentDetails3type() {
        return curentDetails3type;
    }

    public int getCompleteProduct() {
        return completeProduct;
    }
}
