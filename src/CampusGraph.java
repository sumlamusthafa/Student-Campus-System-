import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {
    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new LinkedHashMap<>(); 
    }
    public boolean addLocation(String location) {
        if (adjacencyList.containsKey(location)) {
            return false;
        }
        adjacencyList.put(location, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            return false;
        }
        adjacencyList.remove(location);
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    public boolean addConnection(String loc1, String loc2) {
        if (!adjacencyList.containsKey(loc1) || !adjacencyList.containsKey(loc2)) {
            return false; 
        }
        if (adjacencyList.get(loc1).contains(loc2)) {
            return false; 
        }
        adjacencyList.get(loc1).add(loc2);
        adjacencyList.get(loc2).add(loc1);
        return true;
    }

    public boolean removeConnection(String loc1, String loc2) {
        if (!adjacencyList.containsKey(loc1) || !adjacencyList.containsKey(loc2)) {
            return false;
        }
        boolean removed1 = adjacencyList.get(loc1).remove(loc2);
        boolean removed2 = adjacencyList.get(loc2).remove(loc1);
        return removed1 || removed2;
    }

    public boolean hasLocation(String location) {
        return adjacencyList.containsKey(location);
    }

    public void displayNetwork() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations have been added yet.");
            return;
        }
        System.out.println("---- Campus Network (Adjacency List) ----");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " +
                    (entry.getValue().isEmpty() ? "(no connections)" : entry.getValue()));
        }
    }

    public List<String> bfs(String start) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjacencyList.containsKey(start)) {
            return visitedOrder;
        }
        Set<String> visited = new java.util.HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visitedOrder.add(current);
            for (String neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return visitedOrder;
    }

    public List<String> dfs(String start) {
        List<String> visitedOrder = new ArrayList<>();
        if (!adjacencyList.containsKey(start)) {
            return visitedOrder;
        }
        Set<String> visited = new java.util.HashSet<>();
        dfsHelper(start, visited, visitedOrder);
        return visitedOrder;
    }

    private void dfsHelper(String current, Set<String> visited, List<String> visitedOrder) {
        visited.add(current);
        visitedOrder.add(current);
        for (String neighbour : adjacencyList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsHelper(neighbour, visited, visitedOrder);
            }
        }
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }
}
