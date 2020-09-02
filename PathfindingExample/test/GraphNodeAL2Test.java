/**
 * Test for GraphNodeAL2
 * @author Ellen o' Leary
 * @version 1.0
 */

import CustomClasses.GraphNodeAL2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphNodeAL2Test {
    GraphNodeAL2<Integer> node1,node2, node3, node4;
    @Before
    public void setup(){
        node1 = new GraphNodeAL2<>(45);
        node2 = new GraphNodeAL2<>(2);
        node3 = new GraphNodeAL2<>(864);
        node4 = new GraphNodeAL2<>(97234);
    }

    @Test
    public void connectToNodeDirected(){
        node1.connectToNodeDirected(node2, 2);
        node1.connectToNodeDirected(node3,5); //node1 to 2,3,4
        node1.connectToNodeDirected(node4,7);
        node2.connectToNodeDirected(node3,3); //node2 to 3,4
        node2.connectToNodeDirected(node4,2);
        node3.connectToNodeDirected(node4,6); //node3 to 4
        //node 4 to none

        assertEquals("Connected to 3 other nodes", 3, node1.adjList.size());
        assertEquals("Connected to 2 other nodes", 2, node2.adjList.size());
        assertEquals("Connected to 1 other nodes", 1, node3.adjList.size());
        assertEquals("Connected to 0 other nodes", 0, node4.adjList.size());
    }

    @Test
    public void connectToNodeUndirectedTest(){
        node1.connectToNodeUndirected(node2,2); //node1 to 2
        node2.connectToNodeUndirected(node3,5); //node2 to 1,3,4
        node3.connectToNodeUndirected(node4,7); //node3 to 2,4
        node4.connectToNodeUndirected(node2,1); //node4 to 3,4

        assertEquals("Connected to 1 other nodes", 1, node1.adjList.size());
        assertEquals("Connected to 3 other nodes", 3, node2.adjList.size());
        assertEquals("Connected to 2 other nodes",2, node3.adjList.size());
        assertEquals("Connected to 2 other nodes", 2, node4.adjList.size());
    }
}
