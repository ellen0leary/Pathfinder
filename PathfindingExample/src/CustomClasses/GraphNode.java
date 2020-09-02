package CustomClasses;

import java.util.ArrayList;

public class GraphNode<T> {
    public T data;
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

