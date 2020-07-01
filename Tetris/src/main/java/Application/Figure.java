package Application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Figure {
    private static int xPosition = Game.XMAX - Game.SIZE * 6 + 100;
    private static int yPosition = Game.YMAX / 2;

    public ArrayList<Rectangle> figure = new ArrayList<Rectangle>();
    private int type = 0;
    private int rotat = 0;

    Figure(int type){
        this.type = type;
        Rectangle a = null, b = null, c = null, d = null;
        switch (type) {
            case 0:
                a = new Rectangle(Game.SIZE * 5 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                b = new Rectangle(Game.SIZE * 5 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                c = new Rectangle(Game.SIZE * 6 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                d = new Rectangle(Game.SIZE * 6 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                a.setFill(Color.YELLOW);
                b.setFill(Color.YELLOW);
                c.setFill(Color.YELLOW);
                d.setFill(Color.YELLOW);
                break;

            case 1:
                a = new Rectangle(Game.SIZE * 4 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                b = new Rectangle(Game.SIZE * 5 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                c = new Rectangle(Game.SIZE * 6 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                d = new Rectangle(Game.SIZE * 7 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                a.setFill(Color.BLUE);
                b.setFill(Color.BLUE);
                c.setFill(Color.BLUE);
                d.setFill(Color.BLUE);
                break;

            case 2:
                a = new Rectangle(Game.SIZE * 6 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                b = new Rectangle(Game.SIZE * 7 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                c = new Rectangle(Game.SIZE * 5 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                d = new Rectangle(Game.SIZE * 6 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                a.setFill(Color.GREEN);
                b.setFill(Color.GREEN);
                c.setFill(Color.GREEN);
                d.setFill(Color.GREEN);
                break;

            case 3:
                a = new Rectangle(Game.SIZE * 5 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                b = new Rectangle(Game.SIZE * 6 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                c = new Rectangle(Game.SIZE * 6 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                d = new Rectangle(Game.SIZE * 7 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                a.setFill(Color.RED);
                b.setFill(Color.RED);
                c.setFill(Color.RED);
                d.setFill(Color.RED);
                break;

            case 4:
                a = new Rectangle(Game.SIZE * 5 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                b = new Rectangle(Game.SIZE * 6 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                c = new Rectangle(Game.SIZE * 7 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                d = new Rectangle(Game.SIZE * 6 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                a.setFill(Color.PURPLE);
                b.setFill(Color.PURPLE);
                c.setFill(Color.PURPLE);
                d.setFill(Color.PURPLE);
                break;
            case 5:
                a = new Rectangle(Game.SIZE * 5 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                b = new Rectangle(Game.SIZE * 6 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                c = new Rectangle(Game.SIZE * 7 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                d = new Rectangle(Game.SIZE * 7 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                a.setFill(Color.AQUA);
                b.setFill(Color.AQUA);
                c.setFill(Color.AQUA);
                d.setFill(Color.AQUA);
                break;
            case 6:
                a = new Rectangle(Game.SIZE * 5 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                b = new Rectangle(Game.SIZE * 6 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                c = new Rectangle(Game.SIZE * 7 + xPosition, yPosition, Game.SIZE, Game.SIZE);
                d = new Rectangle(Game.SIZE * 5 + xPosition, Game.SIZE + yPosition, Game.SIZE, Game.SIZE);
                a.setFill(Color.ORANGE);
                b.setFill(Color.ORANGE);
                c.setFill(Color.ORANGE);
                d.setFill(Color.ORANGE);
                break;
        }
        a.setArcHeight(10);
        b.setArcHeight(10);
        c.setArcHeight(10);
        d.setArcHeight(10);
        a.setArcWidth(10);
        b.setArcWidth(10);
        c.setArcWidth(10);
        d.setArcWidth(10);
        figure.add(a);
        figure.add(b);
        figure.add(c);
        figure.add(d);
    }

    boolean isTouchGround(){
        for(Rectangle rec : figure) {
            if(Game.MINE[(int)Math.round(rec.getY()/Game.SIZE) + 1][(int)Math.round(rec.getX()/Game.SIZE)] > 0) {return true;}
        }
        return false;
    }

    boolean isCrossMine(){
        for(Rectangle rec : figure) {
            if(Game.MINE[(int)Math.round(rec.getY()/Game.SIZE)][(int)Math.round(rec.getX()/Game.SIZE)] > 0) {return true;}
        }
        return false;
    }

    void moveRight(){
        for(Rectangle rec : figure){
            if((int)Math.round(rec.getX()/Game.SIZE) + 1 >= Game.MINE[0].length || Game.MINE[(int)Math.round(rec.getY()/Game.SIZE)][(int)Math.round(rec.getX()/Game.SIZE) + 1] == 1) {
                return;
            }
        }
        for(Rectangle rec : figure){
            rec.setX(rec.getX() + Game.SIZE);
        }
    }

    void moveLeft(){
        for(Rectangle rec : figure){
            if((int)Math.round(rec.getX()/Game.SIZE) - 1 < 0 || Game.MINE[(int)Math.round(rec.getY()/Game.SIZE)][(int)Math.round(rec.getX()/Game.SIZE) - 1] == 1) {
                return;
            }
        }
        for(Rectangle rec : figure){
            rec.setX(rec.getX() - Game.SIZE);
        }
    }

    void leaveOnGround(){
        for(Rectangle rec : figure) {
            Game.MINE[(int)Math.round(rec.getY()/Game.SIZE)][(int)Math.round(rec.getX()/Game.SIZE)] = 1;
        }
    }

    void stepDown(){
        for(Rectangle rec : figure) {
            rec.setY(rec.getY() + Game.MOVE);
        }
    }

    void rotate(){
        switch (type){
            case 1:
                switch (rotat){
                    case 0:
                        figure.get(0).setX(figure.get(0).getX() + Game.SIZE * 2);
                        figure.get(1).setX(figure.get(1).getX() + Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() - Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() + Game.SIZE * 2);
                        figure.get(1).setY(figure.get(1).getY() + Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() - Game.SIZE);
                        break;
                    case 1:
                        figure.get(0).setX(figure.get(0).getX() - Game.SIZE * 2);
                        figure.get(1).setX(figure.get(1).getX() - Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() + Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() - Game.SIZE * 2);
                        figure.get(1).setY(figure.get(1).getY() - Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() + Game.SIZE);
                        break;
                    default:
                }
                rotat = (rotat + 1) % 2;
                break;
            case 2:
                switch (rotat){
                    case 0:
                        figure.get(1).setX(figure.get(1).getX() - Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() + Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() + Game.SIZE * 2);
                        figure.get(1).setY(figure.get(1).getY() - Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() - Game.SIZE);
                        break;
                    case 1:
                        figure.get(1).setX(figure.get(1).getX() + Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() - Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() - Game.SIZE * 2);
                        figure.get(1).setY(figure.get(1).getY() + Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() + Game.SIZE);
                        break;
                    default:
                }
                rotat = (rotat + 1) % 2;
                break;
            case 3:
                switch (rotat){
                    case 0:
                        figure.get(0).setX(figure.get(0).getX() + Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() + Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() + Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() - Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() - Game.SIZE * 2);
                        break;
                    case 1:
                        figure.get(0).setX(figure.get(0).getX() - Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() - Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() - Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() + Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() + Game.SIZE * 2);
                        break;
                    default:
                }
                rotat = (rotat + 1) % 2;
                break;
            case 4:
                switch (rotat){
                    case 0:
                        figure.get(0).setX(figure.get(0).getX() + Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() - Game.SIZE);
                        break;
                    case 1:
                        figure.get(3).setX(figure.get(3).getX() - Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() - Game.SIZE);
                        break;
                    case 2:
                        figure.get(2).setX(figure.get(2).getX() - Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() + Game.SIZE);
                        break;
                    case 3:
                        figure.get(0).setX(figure.get(0).getX() - Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() + Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() + Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() + Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() + Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() - Game.SIZE);
                    default:
                }
                rotat = (rotat + 1) % 4;
                break;
            case 5:
                switch (rotat){
                    case 0:
                        figure.get(0).setX(figure.get(0).getX() + Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() - Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() + Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() - Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() - Game.SIZE * 2);
                        break;
                    case 1:
                        figure.get(0).setX(figure.get(0).getX() + Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() - Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() - Game.SIZE * 2);
                        figure.get(0).setY(figure.get(0).getY() - Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() + Game.SIZE);
                        break;
                    case 2:
                        figure.get(0).setX(figure.get(0).getX() - Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() + Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() - Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() + Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() + Game.SIZE * 2);
                        break;
                    case 3:
                        figure.get(0).setX(figure.get(0).getX() - Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() + Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() + Game.SIZE * 2);
                        figure.get(0).setY(figure.get(0).getY() + Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() - Game.SIZE);
                        break;
                    default:
                }
                rotat = (rotat + 1) % 4;
                break;
            case 6:
                switch (rotat){
                    case 0:
                        figure.get(0).setX(figure.get(0).getX() + Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() - Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() + Game.SIZE * 2);
                        figure.get(0).setY(figure.get(0).getY() + Game.SIZE );
                        figure.get(2).setY(figure.get(2).getY() - Game.SIZE);
                        break;
                    case 1:
                        figure.get(0).setX(figure.get(0).getX() + Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() - Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() - Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() + Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() - Game.SIZE * 2);
                        break;
                    case 2:
                        figure.get(0).setX(figure.get(0).getX() - Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() + Game.SIZE);
                        figure.get(3).setX(figure.get(3).getX() - Game.SIZE * 2);
                        figure.get(0).setY(figure.get(0).getY() - Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() + Game.SIZE);
                        break;
                    case 3:
                        figure.get(0).setX(figure.get(0).getX() - Game.SIZE);
                        figure.get(2).setX(figure.get(2).getX() + Game.SIZE);
                        figure.get(0).setY(figure.get(0).getY() + Game.SIZE);
                        figure.get(2).setY(figure.get(2).getY() - Game.SIZE);
                        figure.get(3).setY(figure.get(3).getY() + Game.SIZE * 2);
                        break;
                    default:
                }
                rotat = (rotat + 1) % 4;
                break;
            default:
                break;
        }
        for(Rectangle r : figure){
            if(Math.round(r.getY()/Game.SIZE) < 0 || Math.round(r.getY()/Game.SIZE) >= Game.YMAX/Game.SIZE ||
                    Math.round(r.getX()/Game.SIZE) < 0 || Math.round(r.getX()/Game.SIZE) >= Game.XMAX/Game.SIZE){
                rotate();
                break;
            }
        }
        if(isCrossMine()){
            rotate();
        }
    }

    void drop(){
        while(!isTouchGround()){
            stepDown();
        }
    }

    void moveOnMine(){
        for(Rectangle rec : figure) {
            rec.setY(rec.getY() - yPosition);
            rec.setX(rec.getX() - xPosition);
        }
    }
}
