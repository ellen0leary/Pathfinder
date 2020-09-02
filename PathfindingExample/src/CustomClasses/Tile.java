/**
 * Tile class for rectangles in grid
 * @author Ellen O' Leary
 * @version 1.0
 */
package CustomClasses;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    private int x,y, distance;
    private boolean value;

    /**
     * Constructor for Tile class
     * @param x the x value for the rectangle
     * @param y the y value for the rectangle
     * @param distance the distance to the next rectangle (for dijkstra algo)
     */
    public Tile(int x, int y, int distance){
        super(40, 40, 40, 40); //size of rectangle
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.value = true; //if tile is white it is true
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
    }

    /**
     * Gets the x value of the tile
     * @return x value
     */
    public int getXValue() {
        return x;
    }

    /**
     * sets the x value
     * @param x the new x value
     */
    public void setXValue(int x) {
        this.x = x;
    }

    /**
     * gets the y value of the tile
     * @return y value
     */
    public int getYValue() {
        return y;
    }

    /**
     * sets the y value
     * @param y the new y value
     */
    public void setYValue(int y) {
        this.y = y;
    }

    /**
     * if the tile is transferable
     * @return value
     */
    public boolean getValue() { return value;}

    /**
     * set the distance from the surrounding tiles
     * @param distance the new distance from the surrounding tiles
     */
    public void setDistance(int distance){
        this.distance = distance;
    }

    /**
     * gets the distance from the surrounding tiles
     * @return the distance
     */
    public int getDistance() { return distance; }

    /**
     * gets the value of the tile
     * @param value the new value if the tile
     */
    public void setValue(boolean value) { this.value = value;}

    /**
     * the toString for Tile class
     * @return the toString for the class
     */
    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * sets the start tile colour to dark violet
     */
    public void setStartTile(){ setFill(Color.DARKVIOLET);}

    /**
     * sets the destination tile to green yellow
     */
    public void setFinishTile(){ setFill(Color.GREENYELLOW); }

    /**
     * sets the tile in dfs path to crimson
     */
    public void setBFSTransverseTile(){ setFill(Color.CRIMSON); }

    /**
     * set the tile in bfs path to deep sky blue
     */
    public void setDFSTransverseTile(){ setFill(Color.DEEPSKYBLUE);}

    /**
     * sets the tile in dijkstra path to dark magenta
     */
    public void setDijkstraTransverseTile() { setFill(Color.DARKMAGENTA); }

    /**
     * set the tile to a blank tile
     */
    public void setBlankTile() {
        setFill(Color.WHITE);
        setValue(true);
    }

    /**
     * sets the tile to a "wall" tile
     */
    public void setWallTile() {
        setFill(Color.BLACK);
        setValue(false);
    }

}
