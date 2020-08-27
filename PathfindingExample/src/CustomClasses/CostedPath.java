package CustomClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CostedPath {
    public int pathCost = 0;
    public List<GraphNodeAL2<?>> pathList = new ArrayList<>();

    public static <T> CostedPath findCheapestPathDijkstra(GraphNodeAL2<?> startNode, T lookingfor) {
        CostedPath cp = new CostedPath();
        List<GraphNodeAL2<?>> encountered = new ArrayList<>(), unencountered = new ArrayList<>();
        startNode.nodeValue = 0;
        unencountered.add(startNode);
        GraphNodeAL2<?> currentNode;
        do {
            currentNode = unencountered.remove(0);
            encountered.add(currentNode);
            if (currentNode.data.equals(lookingfor)) {
                cp.pathList.add(currentNode);
                cp.pathCost = currentNode.nodeValue;
                while (currentNode != startNode) {
                    boolean foundPrevPathNode = false;
                    for (GraphNodeAL2<?> n : encountered) {
                        for (GraphLinkAL e : n.adjList)
                            if (e.destNode == currentNode && currentNode.nodeValue - e.cost == n.nodeValue) {
                                cp.pathList.add(0, n);
                                currentNode = n;
                                foundPrevPathNode = true;
                                break;
                            }
                        if (foundPrevPathNode)
                            break;
                    }
                }
                for (GraphNodeAL2<?> n : encountered) n.nodeValue = Integer.MAX_VALUE;
                for (GraphNodeAL2<?> n : unencountered) n.nodeValue = Integer.MAX_VALUE;
                return cp;
            }
            for (GraphLinkAL e : currentNode.adjList)
                if (!encountered.contains(e.destNode)) {
                    e.destNode.nodeValue = Integer.min(e.destNode.nodeValue, currentNode.nodeValue + e.cost);
                    unencountered.add(e.destNode);
                }
            Collections.sort(unencountered, (n1, n2) -> n1.nodeValue - n2.nodeValue);
        } while (!unencountered.isEmpty());
        return null;
    }
}
