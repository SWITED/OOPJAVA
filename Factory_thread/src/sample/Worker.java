package sample;

import javafx.application.Platform;

public class Worker extends Thread{
    public final int type;
    private final int timeToMakeDetail1 = 200;
    private final int timeToMakeDetail2 = 100;
    private final int timeToMakeDetail3 = 300;
    private final int timeToMakeProduct = 300;
    private final int timeToSaleProduct = 10000;

    private int workTime = 0;
    private int maxWorkTime = 20000;
    private final int timeToRest = 3000;
    private Factory factory;
    private Controller controller;
    private State state = State.WAIT;

    enum State {
        WORK("Working..."), WAIT("Do nothing, wait details or space..."), REST("Do nothing...");

        private String string;

        public String getString(){
            return string;
        }

        State(String s) {
            this.string = s;
        }
    }

    public Worker(int type, Factory factory, Controller controller) {
        super();
        this.controller = controller;
        this.factory = factory;
        this.type = type;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (workTime > maxWorkTime) {
                workTime = 0;
                state = State.REST;
                Platform.runLater(() -> {
                    controller.chandeLabel(this);
                });
                try {
                    Thread.sleep(timeToRest);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            state = State.WORK;

            Platform.runLater(() -> {
                controller.chandeLabel(this);
            });

            switch (type) {
                case 0:
                    try {
                        Thread.sleep(timeToMakeDetail1);
                        workTime += timeToMakeDetail1;
                        factory.addDetails1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        Thread.sleep(timeToMakeDetail2);
                        workTime += timeToMakeDetail2;
                        factory.addDetails2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                case 2:
                    try {
                        Thread.sleep(timeToMakeDetail3);
                        workTime += timeToMakeDetail3;
                        factory.addDetails3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        factory.takeDetails1();
                        factory.takeDetails2();
                        factory.takeDetails3();
                        state = State.WORK;
                        Platform.runLater(() -> {
                            controller.chandeLabel(this);
                        });
                        Thread.sleep(timeToMakeProduct);
                        factory.addProduct();
                        workTime += timeToMakeProduct;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        Thread.sleep(timeToSaleProduct);
                        factory.saleProduct();
                        workTime += timeToSaleProduct;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.exit(0);
            }
        }
        state = State.REST;
        Platform.runLater(() -> {
            controller.chandeLabel(this);
        });
    }

    public State getStateWorker() {
        return state;
    }

    public void setStateWorker(State stateWorker) {
        state  = stateWorker;
    }
}
