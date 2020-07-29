package Classes;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    public T data;
    public int nodeValue = Integer.MAX_VALUE;
    public ArrayList<GraphNode<T>> adjList = new ArrayList<>();

    public GraphNode(T data) {this.data = data;}
    public void connectToNodeDirected(GraphNode<T> destNode){
        adjList.add(destNode);
    }

    public void connectToNodeUndirected(GraphNode<T> destNode){
        adjList.add(destNode);
        destNode.adjList.add(this);
    }
}

/*class GraphLinkAL {
    public GraphNode<?> destNode;
    public int cost;

    public GraphLinkAL(GraphNode<?> destNode, int cost) {
        this.destNode = destNode;
        this.cost = cost;
    }

    public List<GraphLinkAL> adjList2 = new ArrayList<>(); //Could use any concrete List implementation

    public void connectToNodeDirectedCost(GraphNode<T> destNode, int cost) {
        adjList2.add(new GraphLinkAL(destNode, cost));
    }

    public void connectToNodeUndirectedCost(GraphNode<T> destNode, int cost) {
        adjList2.add(new GraphLinkAL(destNode, cost));
        destNode.adjList.add(new GraphLinkAL(this, cost));
    }
}*/

