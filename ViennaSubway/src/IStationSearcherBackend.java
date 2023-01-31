import java.util.List;

/**
 * interface for backend of station searcher
 */
public interface IStationSearcherBackend{
    /**
     * search for the shortest path from start to end
     * @param start the starting station
     * @param end the ending station
     * @return a list of connecting station from start to end
     */
    List<IStation> searchShortestPath(String start, String end);

    /**
     * search for the path with least change in train
     * @param start the starting station
     * @param end the ending station
     * @return a list of connecting station from start to end
     */
    List<IStation> searchLeastTransfer(String start, String end);

    /**
     * add all the station into graph
     * @param stations list of stations
     */
    void addStations(List<IStation> stations);

    /**
     * add all the edges into graph
     * @param edges list of edges
     */
    void addEdges(List<IEdge> edges);

    /**
     * get the shortest path cost between two station
     * @param start start station name
     * @param end end station name
     * @return the shortest path cost between two station
     */
    Integer getShortestPathCost(String start, String end);

    /**
     * get the least transfer path cost between two station
     * @param start start station name
     * @param end end station name
     * @return least transfer path cost between two station
     */
    Integer getLeastTransferPathCost(String start, String end);
}