import java.util.List;

public class StationSearcherBackend_FD implements IStationSearcherBackend{
    List<IStation> sp_stations;
    List<IStation> ltp_stations;
    int sp_cost;
    int ltp_cost;
    public StationSearcherBackend_FD(List<IStation> sp_stations, List<IStation> ltp_stations, int sp_cost, int ltp_cost) {
        this.sp_stations = sp_stations;
        this.ltp_stations = ltp_stations;
        this.sp_cost = sp_cost;
        this.ltp_cost = ltp_cost;
    }

    @Override
    public List<IStation> searchShortestPath(String start, String end) {
        return sp_stations;
    }

    @Override
    public List<IStation> searchLeastTransfer(String start, String end) {
        return ltp_stations;
    }

    @Override
    public void addStations(List<IStation> stations) {

    }

    @Override
    public void addEdges(List<IEdge> edges) {

    }

    @Override
    public Integer getShortestPathCost(String start, String end) {
        return sp_cost;
    }

    @Override
    public Integer getLeastTransferPathCost(String start, String end) {return ltp_cost;}
}
