
import java.util.*;

public class StationGraphPlaceholder<T> implements IStationGraph<T> {

    /**
     * Vertex objects group a data field with an adjacency list of weighted
     * directed edges that lead away from them.
     */
    protected class Vertex {
        public T data; // vertex label or application specific data
        public LinkedList<IEdge> edgesLeaving;

        public Vertex(T data) {
            this.data = data;
            this.edgesLeaving = new LinkedList<>();
        }
    }

    protected Hashtable<T, Vertex> vertices; // holds graph verticies, key=data
    public StationGraphPlaceholder() { vertices = new Hashtable<>(); }

    public boolean insertVertex(T data) {
        return true;
    }
    
    public boolean removeVertex(T data) {
        return true;
    }
    
    public boolean insertEdge(T source, T target, int weight) {
        return true;
    }    
    
    public boolean removeEdge(T source, T target) {
        return true;
    }
    
    public boolean containsVertex(T data) {
        return true;
    }

    public boolean containsEdge(T source, T target) {
        return true;
    }
    
    public int getWeight(T source, T target) {
        return 0;
    }
    
    public int getEdgeCount() {
        int edgeCount = 0;
        for(Vertex v : vertices.values())
            edgeCount += v.edgesLeaving.size();
        return edgeCount;
    }
    
    public int getVertexCount() {
        return vertices.size();
    }

    public boolean isEmpty() {
        return vertices.size() == 0;
    }

    protected class Path{}


    protected Path dijkstrasShortestPath(T start, T end) {
        return null;
    }
    

    public List<T> shortestPath(T start, T end) {
        List<T> stations = new LinkedList<T>();
        stations.add(start);
        stations.add(end);
        return stations;
    }

    public int getPathCost(T start, T end) {
        return 100;
    }

    /**
     * place holder method 
     */
    @Override
    public List<T> leastTransferPath(T start, T end) {
        List<T> stations = new LinkedList<T>();
        stations.add(start);
        stations.add(end);
        return stations;
    }

    @Override
    public int leastTransferPathCost(T start, T end) {
        return 190;
    }


}
