/**
 * Test class for CostedPath (dijkstra)
 * @author Ellen O' Leary
 * @version 1.0
 */

import CustomClasses.CostedPath;
import CustomClasses.GraphNodeAL2;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CostedPathTest {
    GraphNodeAL2<Integer> node1, node2, node3, node4;
    public ArrayList<GraphNodeAL2> listOfNodes;

    @Before
    public void setup(){
        node1 = new GraphNodeAL2<>(6);
        node2 = new GraphNodeAL2<>(3);
        node3 = new GraphNodeAL2<>(7);
        node4 = new GraphNodeAL2<>(5);
        listOfNodes = new ArrayList<>();
    }

    @Test
    public void dijkstraTest1(){
        node1.connectToNodeUndirected(node2,6);
        node1.connectToNodeUndirected(node4,4);
        node2.connectToNodeUndirected(node3,4);
        node4.connectToNodeUndirected(node3, 5);
        listOfNodes.add(node1);
        listOfNodes.add(node4);
        listOfNodes.add(node3);

        CostedPath path = CostedPath.findCheapestPathDijkstra(node1,node3.data);
        assertEquals("Passes through node1, node4, node3", listOfNodes, path.pathList);
    }

    @Test
    public void dijkstraTest2(){
        node1.connectToNodeDirected(node2,6);
        node1.connectToNodeDirected(node4,6);
        node2.connectToNodeDirected(node3,4);
        node4.connectToNodeDirected(node3,5);
        listOfNodes.add(node1);
        listOfNodes.add(node2);
        listOfNodes.add(node3);

        CostedPath path = CostedPath.findCheapestPathDijkstra(node1,node3.data);
        assertEquals("Passes through node1, node2, node3", listOfNodes, path.pathList);
    }
}
