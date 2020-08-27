package CustomClasses;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    private int x,y, distance;
    private boolean value;
    public Tile(int x, int y, int distance){
        super(40, 40, 40, 40);
        this.x = x;
        this.y = y;
        this.distance = distance;
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

    public void setDistance(int distance){ this.distance = distance;}

    public int getDistance() { return distance; }
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

    //public void setDFSTransverseTile(int i){ setFill(Color.color(0.05*(i*0.07),0.075*(i*0.25),0.1*(i*0.05)));}
    public void setDFSTransverseTile(int i){ setFill(Color.DEEPSKYBLUE);}
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

    //public void showValue(){dre}
}
