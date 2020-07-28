package Classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    private int x,y;
    public Tile(int x, int y){
        super(40, 40, 40, 40);
        this.x = x;
        this.y = y;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
    }

    public void setStartTile(){
        setFill(Color.DARKVIOLET);
    }

    public void setFinishTile(){
        setFill(Color.GREENYELLOW);
    }

    public void setTranverseTile(){
        setFill(Color.CRIMSON);
    }

    public int getXValue() {
        return x;
    }

    public void setXValue(int x) {
        this.x = x;
    }

    public int getYValue() {
        return y;
    }

    public void setYValue(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
