import Classes.Graph;
import Classes.GraphNode;
import Classes.Tile;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Random;

public class Controller {
    public final int columns = 12;//across
    public final int rows = 10;//down
    public VBox vbox1; //the start box
    //public ArrayList<GraphNode<Tile>> list = new ArrayList<>();
    //public ArrayList<GraphNode<Integer>> deflatedList = new ArrayList<>();
    public GraphNode[][] array = new GraphNode[rows][columns];

    public void initialize() {
        int location = 0;
        for (int i = 0; i < rows; i++) {
            HBox hbox = new HBox();
            for (int j = 0; j < columns; j++) {
                Tile rect = new Tile(i, j);
                hbox.getChildren().add(rect);
                //list.add(new GraphNode<Tile>(rect));
                location++;
                array[i][j] = new GraphNode<Tile>(rect);
            }
            vbox1.getChildren().add(hbox);
            //connectRectangles();
        }
        connectRectangles();
    }

    public void connectRectangles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i + 1 < rows) array[i][j].connectToNodeUndirected(array[i + 1][j]);
                if (j + 1 < columns) array[i][j].connectToNodeUndirected(array[i][j + 1]);
            }
        }
    }

    public void bfs() {
        //connectRectangles();
        Random rand = new Random();
        int y1 = rand.nextInt(columns - 1);
        int y2 = rand.nextInt(columns - 1);
        int x1 = rand.nextInt(rows - 1);
        int x2 = rand.nextInt(rows - 1);
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
        //connectRectangles();
        Random rand = new Random();
        int y1 = rand.nextInt(columns - 1);
        int y2 = rand.nextInt(columns - 1);
        int x1 = rand.nextInt(rows - 1);
        int x2 = rand.nextInt(rows - 1);
        GraphNode<Tile> point1 = array[x1][y1];
        GraphNode<Tile> point2 = array[x2][y2];
        System.out.println(point1.data.toString() + " - " + point1.adjList.size());
        System.out.println(point2.data.toString() + " - " + point2.adjList.size());
        List<GraphNode<?>> graphNodeList = Graph.findPathDepthFirst(point1,null, point2.data);
        ;
        for (int i = 1; i < graphNodeList.size() - 1; i++) {
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
        ;
    }

    public void dijkstra() {
    }

    public void aStar() {
    }
}
