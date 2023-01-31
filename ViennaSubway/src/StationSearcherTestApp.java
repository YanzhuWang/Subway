import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test App for testing
 * @author Haiyi Wang
 */
public class StationSearcherTestApp extends StationSearcherApp{
    private static IStationSearcherBackend backend;

    /**
     * make a placeholder bankend for test
     */
    @Override
    public void get_BD() {
        List<IStation> test_sp = new ArrayList<>();
        List<IStation> test_ltp = new ArrayList<>();

        test_sp.add(new Station_FD("a", "red", new ArrayList<>(Arrays.asList(1))));
        test_sp.add(new Station_FD("b", "red", new ArrayList<>(Arrays.asList(1))));
        test_sp.add(new Station_FD("f", "black", new ArrayList<>(Arrays.asList(1,2))));
        test_sp.add(new Station_FD("c", "green", new ArrayList<>(Arrays.asList(2))));

        test_ltp.add(new Station_FD("a", "red", new ArrayList<>(Arrays.asList(1))));
        test_ltp.add(new Station_FD("d", "black", new ArrayList<>(Arrays.asList(1,2))));
        test_ltp.add(new Station_FD("c", "green", new ArrayList<>(Arrays.asList(1))));
        backend = new StationSearcherBackend_FD(test_sp, test_ltp, 9, 11);
    }

    /**
     * Same as submitHelper_sp method in StationSearcherApp to run placeholder backend
     * @param start the name of start station
     * @param end the name of end station
     * @return list of stations of the shortest path
     */
    @Override
    public List<Label> submitHelper_sp(String start, String end) {
        int time = backend.getShortestPathCost(start, end);
        List<IStation> path = backend.searchShortestPath(start, end);
        List<Label> path_labels = create_labels(path);
        path_labels.add(0, new Label("Shortest Path" + "(" + time + ")" + ": "));
        for (int i = 0; i < 4; i++) {
            path_labels.add(new Label(""));
        }
        return path_labels;
    }

    /**
     * Same as submitHelper_ltp method in StationSearcherApp to run placeholder backend
     * @param start the name of start station
     * @param end the name of end station
     * @return list of stations of the least transfer path
     */
    @Override
    public List<Label> submitHelper_ltp(String start, String end) {
        int time = backend.getLeastTransferPathCost(start, end);
        List<IStation> path = backend.searchLeastTransfer(start, end);
        List<Label> path_labels = create_labels(path);
        path_labels.add(0, new Label("Least Transfer Path" + "(" + time + ")" + ": "));
        return path_labels;
    }

    /**
     * Same as create_labels helper method in StationSearcherApp
     * @param path the path of stations
     * @return list of station labels in each path
     */
    private List<Label> create_labels(List<IStation> path) {
        List<Label> stations = new ArrayList<>();
        for (IStation station: path) {
            Label label = new Label(station.getStationName() + station.getLine().toString());
            if (station.getLine().size() < 2) {
                label.setTextFill(Paint.valueOf(station.getColor()));
            } else {
                label.setTextFill(Paint.valueOf("black"));
            }
            stations.add(label);
        }
        return stations;
    }

    /**
     * launch application
     * @param args
     */
    public static void main(String[] args) {
        Application.launch();
    }
}
