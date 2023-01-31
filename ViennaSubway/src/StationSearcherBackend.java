import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class StationSearcherBackend implements IStationSearcherBackend {

    IStationGraph<IStation> metroGraph;
    HashMap<String, IStation> stationMap;

    /**
     * default constructor to initialize empty map
     */
    public StationSearcherBackend() {
        metroGraph = new StationGraph<IStation>();
        stationMap = new HashMap<String, IStation>();
    }

    /**
     * constructor for tester only
     * @param metroGraph a metrograph created by placeholder class
     * @param stations list of stations
     * @param edges list of edges
     */
    public StationSearcherBackend(IStationGraph<IStation> metroGraph,List<IStation> stations, List<IEdge> edges){
        this.metroGraph = metroGraph;
        stationMap = new HashMap<>();
        addStations(stations);
        addEdges(edges);
    }

    /**
     * constructor for metro map
     * @param stations list of stations
     * @param edges list of edges
     */
    public StationSearcherBackend(List<IStation> stations, List<IEdge> edges) {
        this();
        addStations(stations);
        addEdges(edges);
    }

    /**
     * search the shortest path between stations
     * @param start the starting station
     * @param end the ending station
     * @return the list of stations that metro goes through
     * @throws NoSuchElementException station was not found
     */
    @Override
    public List<IStation> searchShortestPath(String start, String end)throws NoSuchElementException  {
        if(stationMap.get(start) == null || stationMap.get(end) == null){
            throw new NoSuchElementException("station not found");
        }
        return metroGraph.shortestPath(stationMap.get(start), stationMap.get(end));
    }

    /**
     * search the least transfer path between stations
     * @param start the starting station
     * @param end the ending station
     * @return the list of stations that metro goes through
     * @throws NoSuchElementException station was not found
     */
    @Override
    public List<IStation> searchLeastTransfer(String start, String end)throws NoSuchElementException  {
        if(stationMap.get(start) == null || stationMap.get(end) == null){
            throw new NoSuchElementException("station not found");
        }
        return metroGraph.leastTransferPath(stationMap.get(start), stationMap.get(end));
    }

    /**
     * add the stations to graph
     * @param stations list of stations
     */
    @Override
    public void addStations(List<IStation> stations) {
        for(IStation station:stations){
            metroGraph.insertVertex(station);
            stationMap.put(station.getStationName(), station);
        }
    }

    /**
     * add the edges to graph
     * @param edges list of edges
     */
    @Override
    public void addEdges(List<IEdge> edges) {
        for (IEdge edge : edges) {
            if(stationMap.get(edge.getEndStation()) == null) {
                List<IStation> station = new ArrayList<>();
                station.add(new Station(edge.getEndStation(), stationMap.get(edge.getStartStation()).getColor()));
                addStations(station);
            }
            metroGraph.insertEdge(stationMap.get(edge.getStartStation()), stationMap.get(edge.getEndStation()),
                    edge.getCost());
            metroGraph.insertEdge(stationMap.get(edge.getEndStation()), stationMap.get(edge.getStartStation()),
                    edge.getCost());
        }

    }

    /**
     * get the cost of the shortest path
     * @param start start station name
     * @param end end station name
     * @return an integer that represent the cost
     * @throws NoSuchElementException
     */
    @Override
    public Integer getShortestPathCost(String start, String end)throws NoSuchElementException {
        if(stationMap.get(start) == null || stationMap.get(end) == null){
            throw new NoSuchElementException("station not found");
        }
        return metroGraph.getPathCost(stationMap.get(start), stationMap.get(end));
    }

    @Override
    public Integer getLeastTransferPathCost(String start, String end)throws NoSuchElementException  {
        if(stationMap.get(start) == null || stationMap.get(end) == null){
            throw new NoSuchElementException("station not found");
        }
        return metroGraph.leastTransferPathCost(stationMap.get(start), stationMap.get(end));
    }
}