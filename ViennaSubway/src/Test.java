import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        IStationEdgeLoader e = new StationEdgeLoader();
        HashMap<IStation, List<IEdge>> m = e.loadStation("src/Vienna-subway.txt");
        Set<IStation> a = m.keySet();
        List<IStation> b = new ArrayList<>();
        int c = 0;
        for (IStation as: a) {
            if (!as.getStationName().equals(m.get(as).get(0).getEndStation())) {
                if (as.getStationName().equals("Karlsplatz")) {
                    System.out.println(as.getStationName() + "," + m.get(as).get(0).getEndStation());
                }
                c++;
            } else {
                b.add(as);
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        /*
        for (IStation as: b) {
            System.out.println(as.getStationName() + "," + m.get(as).get(0).getEndStation());
        }
        List<Integer> s = new ArrayList<>();
        System.out.println(s.size());

         */
        System.out.println(c);
        System.out.println(b.size());
        /*
            public void get_BD() throws FileNotFoundException {
        IStationEdgeLoader loader = new StationEdgeLoader();
        HashMap<IStation, List<IEdge>> station_edges = loader.loadStation("src/Vienna-subway.txt");
        List<IStation> stations = new ArrayList<>(station_edges.keySet());
        List<IEdge> edges = new ArrayList<>();
        for (IStation station: stations) {
            if (!station.getStationName().equals(station_edges.get(station).get(0).getEndStation())) {
            edges.addAll(station_edges.get(station));
        }
        backend = new StationSearcherBackend(stations, edges);
    }
         */
    }
}
