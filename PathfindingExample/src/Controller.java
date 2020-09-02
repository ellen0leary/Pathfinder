/**
 * Controlls the
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
            HBox hbox = new HBox();
            for (int j = 0; j < columns; j++) {
                int distance = rand.nextInt(10);
                Tile rect = new Tile(i, j, distance);
                hbox.getChildren().add(rect);
                array[i][j] = new GraphNode<>(rect);
                dijkstraArray[i][j] = new GraphNodeAL2<>(rect);
            }
            vbox1.getChildren().add(hbox);
        }
    }

    /**
     * connects the grid up
     */
    public void connectRectangles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNode<Tile> tile = array[i][j];
                tile.adjList.clear();
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNode<Tile> current = array[i][j];
                if (i + 1 < rows) {
                    GraphNode<Tile> left = array[i + 1][j];
                    if (current.data.getValue() && left.data.getValue())
                        array[i][j].connectToNodeUndirected(array[i + 1][j]);
                }
                if (j + 1 < columns) {
                    GraphNode<Tile> down = array[i][j + 1];
                    if (current.data.getValue() && down.data.getValue())
                        array[i][j].connectToNodeUndirected(array[i][j + 1]);
                }

            }
        }
    }

    /**
     * Checks if the points are available and sets the points
     */
    private void findPoints(){
        int x1 = Integer.parseInt(startXPoint.getText());
        if(x1>12) startText.setText("The x value is not within range (has to be less than 12)");
        else if(x1<0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        int x2 = Integer.parseInt(endXPoint.getText());
        if(x2>12) startText.setText("The x value is not within range (has to be less than 12)");
        else if(x2<0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        int y1 = Integer.parseInt(startYPoint.getText());
        if(y1>10) startText.setText("The x value is not within range (has to be less than 10)");
        else if(y1<0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        int y2 = Integer.parseInt(endYPoint.getText());
        if(y2>10) startText.setText("The x value is not within range (has to be less than 10)");
        else if(y2<0) startText.setText("The x value is less than zero");
        else startText.setText(" ");

        point1 = array[x1][y1];
        point2 = array[x2][y2];
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
        point1.data.setStartTile();
        point2.data.setFinishTile();
    }

    /**
     *
     */
    public void dfs() {
        connectRectangles();
        findPoints();
        List<GraphNode<?>> GraphNodeList = Graph.searchGraphDepthFirst(point1, null, point2.data);
        if (GraphNodeList != null) {
            for (int i = 1; i < GraphNodeList.size() - 1; i++) {
                System.out.println(GraphNodeList.get(i).data.toString());
                for (int j = 0; j < columns; j++) {
                    for (int k = 0; k < rows; k++) {
                        if (GraphNodeList.get(i).data.toString().equals(array[k][j].data.toString())) {
                            GraphNode<Tile> point = array[k][j];
                            point.data.setDFSTransverseTile(i);
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("There is no nodes available");
        }
        System.out.println("-------------------");
        point1.data.setStartTile();
        point2.data.setFinishTile();
    }

    public void dijkstra() {
        connectDijkstraRectangles();
        int x1 = Integer.parseInt(startXPoint.getText());
        int x2 = Integer.parseInt(endXPoint.getText());
        int y1 = Integer.parseInt(startYPoint.getText());
        int y2 = Integer.parseInt(endYPoint.getText());

        GraphNodeAL2<Tile> point1 = dijkstraArray[x1][y1];
        GraphNodeAL2<Tile> point2 = dijkstraArray[x2][y2];
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
        CostedPath GraphNodeList = CostedPath.findCheapestPathDijkstra(point1, point2.data);
        for(GraphNodeAL2<?> n : GraphNodeList.pathList) {
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
        point1.data.setStartTile();
        point2.data.setFinishTile();
    }

    private void connectDijkstraRectangles() {
        giveTileValues();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNodeAL2<Tile> tile = dijkstraArray[i][j];
                tile.adjList.clear();
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNodeAL2<Tile> current = dijkstraArray[i][j];
                if (i + 1 < rows) {
                    GraphNodeAL2<Tile> left = dijkstraArray[i + 1][j];
                    if (current.data.getValue() && left.data.getValue())
                        dijkstraArray[i][j].connectToNodeUndirected(dijkstraArray[i + 1][j], current.data.getDistance());
                }
                if (j + 1 < columns) {
                    GraphNode<Tile> down = array[i][j + 1];
                    if (current.data.getValue() && down.data.getValue())
                        dijkstraArray[i][j].connectToNodeUndirected(dijkstraArray[i][j + 1], current.data.getDistance());
                }
            }
        }
    }

    //public void aStar() { }

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
            }
        }
    }

    /**
     * calls the method clear followed by the method walls
     */
    public void resetWalls() {
        clear();
        walls();
    }

    /**
     * Gives tiles values
     */
    public void giveTileValues() {
        for(int i=0; i<rows; i++){
            for(int j=0;j<columns; j++){
                int distance = rand.nextInt(10);

                GraphNodeAL2<Tile> tile = dijkstraArray[i][j];
                tile.data.setDistance(distance);
            }
        }
    }
}
