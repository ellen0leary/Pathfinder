package CustomClasses;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeAL2<T> {
    public T data;
    public int nodeValue=Integer.MAX_VALUE;
    public List<GraphLinkAL> adjList=new ArrayList<>(); //Adjacency list now contains link objects = key!
//Could use any concrete List implementation

    public GraphNodeAL2(T data) {
        this.data=data;
    }
    public void connectToNodeDirected(GraphNodeAL2<T> destNode,int cost) {
        adjList.add( new GraphLinkAL(destNode,cost) ); //Add new link object to source adjacency list
    }
    public void connectToNodeUndirected(GraphNodeAL2<T> destNode,int cost) {
        adjList.add( new GraphLinkAL(destNode,cost) ); //Add new link object to source adjacency list
        destNode.adjList.add( new GraphLinkAL(this,cost) ); //Add new link object to destination adjacency list
    }
}

class GraphLinkAL {
    public GraphNodeAL2<?> destNode; //Could also store source node if required
    public int cost; //Other link attributes could be similarly stored
    public GraphLinkAL(GraphNodeAL2<?> destNode,int cost) {
        this.destNode=destNode;
        this.cost=cost;
    }
}
