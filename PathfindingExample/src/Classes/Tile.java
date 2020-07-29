package Classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    private int x,y;
    private boolean value;
    public Tile(int x, int y){
        super(40, 40, 40, 40);
        this.x = x;
        this.y = y;
        this.value = true;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
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

    public boolean getValue() { return value;}

    public void setValue(boolean value) { this.value = value;}

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setStartTile(){ setFill(Color.DARKVIOLET);}

    public void setFinishTile(){ setFill(Color.GREENYELLOW); }

    public void setBFSTransverseTile(){ setFill(Color.CRIMSON); }

    public void setDFSTransverseTile(int i){ setFill(Color.color(0.1*i,0.12*i,0.1*i));}

    public void setDijkstraTransverseTile() { setFill(Color.CADETBLUE); }

    public void setATransverseTile() { setFill(Color.ORANGERED); }

    public void setBlankTile() {
        setFill(Color.WHITE);
        setValue(true);
    }

    public void setWallTile() {
        setFill(Color.BLACK);
        setValue(false);
    }
}
