/**
 * Main controller
 * @Author Ellen O'Leary
 * @Version 1.0
 */

import CustomClasses.*;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Random;

public class Controller {
    public final int columns = 12;//across
    public final int rows = 10;//down

    public VBox vbox1; //the start box
    public TextField startXPoint, startYPoint, endXPoint, endYPoint; //text boxes that takes in the points
    public Text endText, startText; //text area to fill in if the values are wrong
    private GraphNode<Tile> point1, point2; //the start and end point

    public GraphNode<Tile>[][] array = new GraphNode[rows][columns]; //array for bfs and dfs
    public GraphNodeAL2<Tile>[][] dijkstraArray = new GraphNodeAL2[rows][columns]; //array for bfs
    public Random rand = new Random(); //import the random class

    /**
     * draws the grid
     */
    public void initialize() {
        for (int i = 0; i < rows; i++) {
            HBox hbox = new HBox(); //draws a new hbox for each rows
            for (int j = 0; j < columns; j++) {
                int distance = rand.nextInt(10); //creates new distance
                Tile rect = new Tile(i, j, distance); //creates a new tile
                hbox.getChildren().add(rect); //adds tile to hbox
                array[i][j] = new GraphNode<>(rect);
                dijkstraArray[i][j] = new GraphNodeAL2<>(rect);
            }
            vbox1.getChildren().add(hbox);//adds the hbox to vbox
        }
    }

    /**
     * connects the grid up
     */
    public void connectRectangles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNode<Tile> tile = array[i][j];
                tile.adjList.clear(); //clears the adjList just in case it already connected
                GraphNodeAL2<Tile> dijkstraTile = dijkstraArray[i][j];
                dijkstraTile.adjList.clear();
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNode<Tile> current = array[i][j];
                if (i + 1 < rows) { //if there is a tile to right
                    GraphNode<Tile> left = array[i + 1][j];
                    if (current.data.getValue() && left.data.getValue()) {//if they're both paths
                        array[i][j].connectToNodeUndirected(array[i + 1][j]); //connect current node to right node
                        dijkstraArray[i][j].connectToNodeUndirected(dijkstraArray[i + 1][j], dijkstraArray[i][j].data.getDistance());
                    }
                }
                if (j + 1 < columns) { //if there is a tile below
                    GraphNode<Tile> down = array[i][j + 1];
                    if (current.data.getValue() && down.data.getValue()) { //if they're both paths
                        array[i][j].connectToNodeUndirected(array[i][j + 1]); //connect current node to the node below
                        dijkstraArray[i][j].connectToNodeUndirected(dijkstraArray[i][j + 1], dijkstraArray[i][j].data.getDistance());
                    }
                }
            }
        }
    }

    /**
     * Checks if the points are available and sets the points
     */
    private void findPoints() {
        int x1 = Integer.parseInt(startXPoint.getText()); //get number for start x point
        if (x1 > columns) startText.setText("The x value is not within range (has to be less than 12)");
        else if (x1 < 0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        int x2 = Integer.parseInt(endXPoint.getText()); //get number for destination x point
        if (x2 > columns) startText.setText("The x value is not within range (has to be less than 12)");
        else if (x2 < 0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        int y1 = Integer.parseInt(startYPoint.getText()); //get number for start y point
        if (y1 > rows) startText.setText("The x value is not within range (has to be less than 10)");
        else if (y1 < 0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        int y2 = Integer.parseInt(endYPoint.getText()); //get number for destination y point
        if (y2 > rows) startText.setText("The x value is not within range (has to be less than 10)");
        else if (y2 < 0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        point1 = array[x1][y1]; //start point
        point2 = array[x2][y2]; //destination point
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
    }

    /**
     * Gets the breath first search path
     */
    public void bfs() {
        connectRectangles();
        findPoints();
        List<GraphNode<?>> GraphNodeList = Graph.findPathBreadthFirst(point1, point2.data);

        for (int i = 1; i < GraphNodeList.size() - 1; i++) {
            System.out.println(GraphNodeList.get(i).data.toString());
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < rows; k++) {
                    if (GraphNodeList.get(i).data.toString().equals(array[k][j].data.toString())) {
                        GraphNode<Tile> point = array[k][j];
                        point.data.setBFSTransverseTile();
                    }
                }
            }
        }
        System.out.println("-------------------");
        point1.data.setStartTile(); //set start tile to correct color
        point2.data.setFinishTile(); //set destination tile to correct color
    }

    /**
     * Gets the depth first search path
     */
    public void dfs() {
        connectRectangles();
        findPoints();
        List<GraphNode<?>> GraphNodeList = Graph.findPathDepthFirst(point1, null, point2.data);
        if (GraphNodeList != null) {
            for (int i = 1; i < GraphNodeList.size() - 1; i++) {
                System.out.println(GraphNodeList.get(i).data.toString());
                for (int j = 0; j < columns; j++) {
                    for (int k = 0; k < rows; k++) {
                        if (GraphNodeList.get(i).data.toString().equals(array[k][j].data.toString())) {
                            GraphNode<Tile> point = array[k][j];
                            point.data.setDFSTransverseTile();
                        }
                    }
                }
            }
        } else {
            System.out.println("There is no nodes available");
        }
        System.out.println("-------------------");
        point1.data.setStartTile(); //set start tile to correct color
        point2.data.setFinishTile(); //set destination tile to correct color
    }

    /**
     * gets the dijkstra path
     */
    public void dijkstra() {
        connectRectangles();
        int x1 = Integer.parseInt(startXPoint.getText());
        int x2 = Integer.parseInt(endXPoint.getText());
        int y1 = Integer.parseInt(startYPoint.getText());
        int y2 = Integer.parseInt(endYPoint.getText());

        GraphNodeAL2<Tile> point1 = dijkstraArray[x1][y1];
        GraphNodeAL2<Tile> point2 = dijkstraArray[x2][y2];
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
        CostedPath GraphNodeList = CostedPath.findCheapestPathDijkstra(point1, point2.data);
        for (GraphNodeAL2<?> n : GraphNodeList.pathList) {
            System.out.println(n.data);
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < rows; k++) {
                    if (n.data.toString().equals(dijkstraArray[k][j].data.toString())) {
                        GraphNodeAL2<Tile> point = dijkstraArray[k][j];
                        point.data.setDijkstraTransverseTile();
                        break;
                    }
                }
            }
        }
        point1.data.setStartTile(); //set start tile to correct color
        point2.data.setFinishTile(); //set destination tile to correct color
    }

    /**
     * creates a random amounts walls
     */
    public void walls() {
        int i = rand.nextInt(20);
        for (; i > 0; i--) {
            int x = rand.nextInt(rows - 1);
            int y = rand.nextInt(columns - 1);
            GraphNode<Tile> tile = array[x][y];
            tile.data.setWallTile();
            GraphNodeAL2<Tile> dijkstraTile = dijkstraArray[x][y];
            dijkstraTile.data.setWallTile();
        }
    }

    /**
     * clears the tiles of any value
     */
    public void clear() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNode<Tile> tile = array[i][j];
                tile.data.setBlankTile();

                GraphNodeAL2<Tile> dijkstraTile = dijkstraArray[i][j];
                dijkstraTile.data.setBlankTile();
            }
        }
    }

    /**
     * calls the method clear followed by the method walls
     * to remove and redraw walls
     */
    public void resetWalls() {
        clear();
        walls();
    }


}
