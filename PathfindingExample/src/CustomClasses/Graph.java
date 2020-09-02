package CustomClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    public static <T> List<GraphNode<?>> findPathBreadthFirst(GraphNode<?> startNode, T lookingFor) {
        List<List<GraphNode<?>>> agenda = new ArrayList<>();
        List<GraphNode<?>> firstAgendaPath = new ArrayList<>(), resultPath;
        firstAgendaPath.add(startNode);
        agenda.add(firstAgendaPath);
        resultPath = findPathBreadthFirst(agenda, null, lookingFor);
        Collections.reverse(resultPath);
        return resultPath;
    }

    public static <T> List<GraphNode<?>> findPathBreadthFirst(List<List<GraphNode<?>>> agenda, List<GraphNode<?>> encountered, T lookingFor) {
        if (agenda.isEmpty()) return null;
        List<GraphNode<?>> nextPath = agenda.remove(0);
        GraphNode<?> currentNode = nextPath.get(0);
        if (currentNode.data.equals(lookingFor)) return nextPath;
        if (encountered == null) encountered = new ArrayList<>();
        encountered.add(currentNode);
        for (GraphNode adjNode : currentNode.adjList)
            if (!encountered.contains(adjNode)) {
                List<GraphNode<?>> newPath = new ArrayList<>(nextPath);
                newPath.add(0, adjNode);
                agenda.add(newPath);
            }
        return findPathBreadthFirst(agenda, encountered, lookingFor); //Tail call
    }

    public static <T> GraphNode<?> searchGraphDepthFirst(GraphNode<?> from, List<GraphNode<?>> encountered, T lookingfor) {
        if (from.data.equals(lookingfor)) return from;
        if (encountered == null) encountered = new ArrayList<>(); //First node so create new (empty) encountered list
        encountered.add(from);
        for (GraphNode<?> adjNode : from.adjList)
            if (!encountered.contains(adjNode)) {
                GraphNode<?> result = searchGraphDepthFirst(adjNode, encountered, lookingfor);
                if (result != null) return result;
            }
        return null;
    }
}
/*
    public static <T> List<GraphNode<?>> findPathDepthFirst(GraphNode<?> from, List<GraphNode<?>> encountered, T lookingFor) {
        List<GraphNode<?>> result;
        if (from.data.equals(lookingFor)) {
            result = new ArrayList<>();
            result.add(from);
            return result;
        }
        if (encountered == null) encountered = new ArrayList<>();
        encountered.add(from);
        for (GraphNode adjNode : from.adjList)
            if (!encountered.contains(adjNode)) {
                result = findPathDepthFirst(adjNode, encountered, lookingFor);
                if (result != null) {
                    result.add(0, from);
                    return result;
                }
            }
        return null;
    }
}
*/
