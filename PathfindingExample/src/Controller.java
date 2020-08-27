import CustomClasses.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Random;

public class Controller {
    public final int columns = 12;//across
    public final int rows = 10;//down
    public VBox vbox1; //the start box
    public GraphNode<Tile>[][] array = new GraphNode[rows][columns]; //array for bfs and dfs
    public GraphNodeAL2<Tile>[][] dijkstraArray = new GraphNodeAL2[rows][columns];
    Random rand = new Random();
    //private CustomClasses.CostedPath CostPath;

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

    private int randomXPoint() {
        return rand.nextInt(rows - 1);
    }

    private int randomYPoint() {
        return rand.nextInt(columns - 1);
    }

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
                    if (current.data.getValue()  && left.data.getValue())
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

    public void bfs() {
        int y1 = randomYPoint();
        int y2 = randomYPoint();
        int x1 = randomXPoint();
        int x2 = randomXPoint();
        GraphNode<Tile> point1 = array[x1][y1];
        GraphNode<Tile> point2 = array[x2][y2];
        point1.data.setStartTile();
        point2.data.setFinishTile();
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
        List<GraphNode<?>> graphNodeList = Graph.findPathBreadthFirst(point1, point2.data);

        for (int i = 1; i < graphNodeList.size() - 1; i++) {
            System.out.println(graphNodeList.get(i).data.toString());
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < rows; k++) {
                    if (graphNodeList.get(i).data.toString().equals(array[k][j].data.toString())) {
                        GraphNode<Tile> point = array[k][j];
                        point.data.setBFSTransverseTile();
                        //array[j][k].data.;
                        //break;
                    }
                }
            }
        }
        System.out.println("-------------------");
    }

    public void dfs() {
        int y1 = randomYPoint(),y2 = randomYPoint();
        int x1 = randomXPoint(),x2 = randomXPoint();

        GraphNode<Tile> point1 = array[x1][y1];
        GraphNode<Tile> point2 = array[x2][y2];
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
        List<GraphNode<?>> graphNodeList = Graph.findPathDepthFirst(point1, null, point2.data);
        if(graphNodeList!=null) {
            for (int i = 1; i < graphNodeList.size() - 1; i++) {
                System.out.println(graphNodeList.get(i).data.toString());
                for (int j = 0; j < columns; j++) {
                    for (int k = 0; k < rows; k++) {
                        if (graphNodeList.get(i).data.toString().equals(array[k][j].data.toString())) {
                            GraphNode<Tile> point = array[k][j];
                            point.data.setDFSTransverseTile(i);
                            break;
                        }
                    }
                }
            }
        }else {
            System.out.println("There is no nodes available");
        }
        System.out.println("-------------------");
    }

    public void dijkstra() {
        connectDijkstraRectangles();
        int y1 = randomYPoint();
        int y2 = randomYPoint();
        int x1 = randomXPoint();
        int x2 = randomXPoint();
        GraphNodeAL2<Tile> point1 = dijkstraArray[x1][y1];
        GraphNodeAL2<Tile> point2 = dijkstraArray[x2][y2];
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
       CostedPath graphNodeList =CostedPath.findCheapestPathDijkstra(point1, point2.data);
       System.out.println(graphNodeList);
        //System.out.println(graphNodeList.pathCost);
        //graphNodeList.toString();
    }

    private void connectDijkstraRectangles() {
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

    public void walls() {
        int i = rand.nextInt(20);
        for (; i > 0; i--) {
            int x = rand.nextInt(rows - 1);
            int y = rand.nextInt(columns - 1);
            GraphNode<Tile> tile = array[x][y];
            tile.data.setWallTile();
        }
        connectRectangles();
    }

    public void resetWalls() {
        clear();
        walls();
    }

    public void clear() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNode<Tile> tile = array[i][j];
                tile.data.setBlankTile();
            }
        }
    }

    //public void giveTileValues() {}
}
