/**
 * Test class for Graph
 * @author Ellen O' Leary
 * @version 1.0
 */

import CustomClasses.Graph;
import CustomClasses.GraphNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {
    GraphNode<Integer> node1,node2, node3, node4,node5;
    ArrayList<GraphNode> listOfNodes;

    @Before
    public void setup(){
        node1 = new GraphNode<>(3);
        node2 = new GraphNode<>(7);
        node3 = new GraphNode<>(2);
        node4 = new GraphNode<>(9);
        node5 = new GraphNode<>(4);

        listOfNodes = new ArrayList<>();
    }

    @Test
    public void bfsTest(){
        node1.connectToNodeDirected(node4);
        node1.connectToNodeDirected(node2); //node1 to 2,4
        node2.connectToNodeDirected(node3); //node2 to 3
        node3.connectToNodeDirected(node4); //node3 to 4
        node4.connectToNodeDirected(node5); //node4 to 5

        listOfNodes.add(node1);
        listOfNodes.add(node4);
        listOfNodes.add(node5);

        List<GraphNode<?>> list = Graph.findPathBreadthFirst(node1, node5.data);
        assertEquals("", listOfNodes, list);
    }

    @Test
    public void dfsTest(){
        node1.connectToNodeDirected(node4);
        node1.connectToNodeDirected(node2); //node1 to 2,4
        node2.connectToNodeDirected(node3); //node2 to 3
        node3.connectToNodeDirected(node4); //node3 to 4
        node4.connectToNodeDirected(node5); //node4 to 5

        listOfNodes.add(node1);
        listOfNodes.add(node4);
        listOfNodes.add(node5);

        List<GraphNode<?>> list = Graph.findPathDepthFirst(node1, null,node5.data);
        assertEquals("", listOfNodes, list);
    }
}
