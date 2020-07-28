package Classes;

import java.util.ArrayList;
import java.util.HashSet;

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
