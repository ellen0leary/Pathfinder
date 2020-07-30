import CustomClasses.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Controller {
    public final int columns = 12;//across
    public final int rows = 10;//down
    public VBox vbox1; //the start box
    public GraphNode<Tile>[][] array = new GraphNode[rows][columns];
    Random rand = new Random();

    public void initialize() {
        for (int i = 0; i < rows; i++) {
            HBox hbox = new HBox();
            for (int j = 0; j < columns; j++) {
                Tile rect = new Tile(i, j);
                hbox.getChildren().add(rect);
                array[i][j] = new GraphNode<Tile>(rect);
            }
            vbox1.getChildren().add(hbox);
        }
    }

    public int randomXPoint(){
        return rand.nextInt(rows - 1);
    }

    public int randomYPoint(){
        return rand.nextInt(columns - 1);
    }

    public void connectRectangles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GraphNode<Tile> current = array[i][j];
                if (i + 1 < rows) {
                    GraphNode<Tile> left = array[i+1][j];
                    if(current.data.getValue()==true && left.data.getValue()==true)
                        array[i][j].connectToNodeUndirected(array[i+1][j]);
                }
                if (j + 1 < columns)  {
                    GraphNode<Tile> down = array[i][j+1];
                    if(current.data.getValue()==true && down.data.getValue()==true)
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
        ;
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
        int y1 = randomYPoint();
        int y2 = randomYPoint();
        int x1 = randomXPoint();
        int x2 = randomXPoint();
        GraphNode<Tile> point1 = array[x1][y1];
        GraphNode<Tile> point2 = array[x2][y2];
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
        List<GraphNode<?>> graphNodeList = Graph.findPathDepthFirst(point1,null, point2.data);
        for (int i = 1; i < Objects.requireNonNull(graphNodeList).size() - 1; i++) {
            System.out.println(graphNodeList.get(i).data.toString());
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < rows; k++) {
                    if (graphNodeList.get(i).data.toString().equals(array[k][j].data.toString())) {
                        GraphNode<Tile> point = array[k][j];
                        point.data.setDFSTransverseTile(i);

                    }
                }
            }
        }
        System.out.println("-------------------");
    }

    public void dijkstra() { }

    //public void aStar() { }

    public void walls(){
        int i = rand.nextInt(20);
        for(; i>0; i--){
            int x = rand.nextInt(rows-1);
            int y = rand.nextInt(columns-1);
            GraphNode<Tile> tile = array[x][y];
            tile.data.setWallTile();
        }
        connectRectangles();
    }

    public void resetWalls(){
        for(int i=0; i<rows;i++){
            for(int j=0;j<columns;j++){
                GraphNode<Tile> tile = array[i][j];
                tile.data.setBlankTile();
            }
        }
        walls();
    }

    public void clear(){
        for(int i=0; i<rows;i++){
            for(int j=0;j<columns;j++){
                GraphNode<Tile> tile = array[i][j];
                tile.data.setBlankTile();
            }
        }
    }
}
