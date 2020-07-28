package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    public static <T> List<GraphNode<?>> findPathBreadthFirst(GraphNode<?> startNode, T lookingfor) {
        List<List<GraphNode<?>>> agenda = new ArrayList<>();
        List<GraphNode<?>> firstAgendaPath = new ArrayList<>(), resultPath;
        firstAgendaPath.add(startNode);
        agenda.add(firstAgendaPath);
        resultPath = findPathBreadthFirst(agenda, null, lookingfor);
        Collections.reverse(resultPath);
        return resultPath;
    }

    public static <T> List<GraphNode<?>> findPathBreadthFirst(List<List<GraphNode<?>>> agenda, List<GraphNode<?>> encountered, T lookingfor) {
        if (agenda.isEmpty()) return null;
        List<GraphNode<?>> nextPath = agenda.remove(0);
        GraphNode<?> currentNode = nextPath.get(0);
        if (currentNode.data.equals(lookingfor)) return nextPath;
        if (encountered == null) encountered = new ArrayList<>();
        encountered.add(currentNode);
        for (GraphNode<?> adjNode : currentNode.adjList)
            if (!encountered.contains(adjNode)) {
                List<GraphNode<?>> newPath = new ArrayList<>(nextPath);
                newPath.add(0, adjNode);
                agenda.add(newPath);
            }
        return findPathBreadthFirst(agenda, encountered, lookingfor); //Tail call
    }

    public static <T> List<GraphNode<?>> findPathDepthFirst(GraphNode<?> from, List<GraphNode<?>> encountered, T lookingfor) {
        List<GraphNode<?>> result;
        if (from.data.equals(lookingfor)) {
            result = new ArrayList<>();
            result.add(from);
            return result;
        }
        if (encountered == null) encountered = new ArrayList<>();
        encountered.add(from);
        for (GraphNode<?> adjNode : from.adjList)
            if (!encountered.contains(adjNode)) {
                result = findPathDepthFirst(adjNode, encountered, lookingfor);
                if (result != null) {
                    result.add(0, from);
                    return result;
                }
            }
        return null;
    }
}
