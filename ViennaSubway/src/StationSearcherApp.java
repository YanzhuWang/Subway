import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * JavaFx GUI let users type start station and last station
 * and will show them a shortest path and a least transfer path
 * @author haiyi Wang
 */
public class StationSearcherApp extends Application implements IStationSearcherApp{
    protected IStationSearcherBackend backend;

    /**
     * make a stage for computer-user interaction and initialize backend
     * main features are two textfields for typing start and end station
     * two buttons for submit inputs and clear all outputs
     * A list view to display paths of the shortest path and the least transfer path
     * @param stage stage to show
     * @throws FileNotFoundException if data file not found
     */
    @Override
    public void start(Stage stage) throws FileNotFoundException{
        get_BD();
        final Label from = new Label("From:");
        final TextField start = new TextField();
        start.setPromptText("Enter your start station");
        start.setId("start");

        final Label to = new Label("To:");

        final TextField end = new TextField();
        end.setPromptText("Enter your destination");
        end.setId("end");

        ListView<Label> listview = new ListView<>();
        listview.setId("display");

        Button submit = new Button("Submit");
        submit.setId("submit");
        submit.setOnAction(e -> submitAction(start, end, listview));

        Button clear = new Button("Clear");
        clear.setId("clear");
        clear.setOnAction(e -> clearAction(start, end, listview));
        HBox hbox = new HBox(submit, clear);
        hbox.setSpacing(10);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        grid.getChildren().add(from);
        GridPane.setColumnIndex(from, 0);
        GridPane.setRowIndex(from, 0);

        grid.getChildren().add(start);
        GridPane.setColumnIndex(start, 0);
        GridPane.setRowIndex(start, 1);

        grid.getChildren().add(to);
        GridPane.setColumnIndex(to, 0);
        GridPane.setRowIndex(to, 2);

        grid.getChildren().add(end);
        GridPane.setColumnIndex(end, 0);
        GridPane.setRowIndex(end, 3);


        grid.getChildren().add(hbox);
        GridPane.setConstraints(hbox, 0,4);


        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(grid);
        borderPane.setRight(listview);
        Scene scene = new Scene(borderPane, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Vienna Subway Guide");
        submit.requestFocus();
        stage.show();
    }

    /**
     * initialize backend
     * @throws FileNotFoundException if data file not found
     */
    public void get_BD() throws FileNotFoundException {
        IStationEdgeLoader loader = new StationEdgeLoader();
        HashMap<IStation, List<IEdge>> station_edges = loader.loadStation("src/Vienna-subway.txt");
        List<IStation> stations = new ArrayList<>();
        for (IStation station: station_edges.keySet()) {
                stations.add(station);
        }

        List<IEdge> edges = new ArrayList<>();
        for (IStation station: stations) {
            edges.addAll(station_edges.get(station));
        }

        for (int i =0; i < stations.size(); i++) {
            for (int j = i + 1; j < stations.size(); j++) {
                if (stations.get(i).getStationName().equals(stations.get(j).getStationName())) {
                    stations.remove(j);
                }
            }
        }

        backend = new StationSearcherBackend(stations, edges);
    }

    /**
     * the action to display paths of the shortest path and the least transfer path after clicking submit
     * @param start input for start station
     * @param end   input for end station
     * @param show  ListView
     */
    @Override
    public void submitAction(TextField start, TextField end, ListView<Label> show) {
        if(!(start.getText().equals("") || end.getText().equals(""))) {
            List<Label> sp = submitHelper_sp(start.getText(), end.getText());
            List<Label> ltp = submitHelper_ltp(start.getText(), end.getText());
            ObservableList<Label> path_oj = FXCollections.observableArrayList();
            path_oj.addAll(sp);
            path_oj.addAll(ltp);
            show.setItems(path_oj);
        }
    }

    /**
     * helper method to return list of stations of the shortest path
     * @param start the name of start station
     * @param end the name of end station
     * @return list of stations of the shortest path
     */
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
     * helper method to return list of stations of the least transfer path
     * @param start the name of start station
     * @param end the name of end station
     * @return list of stations of the least transfer path
     */
    public List<Label> submitHelper_ltp(String start, String end) {
        int time = backend.getLeastTransferPathCost(start, end);
        List<IStation> path = backend.searchLeastTransfer(start, end);
        List<Label> path_labels = create_labels(path);
        path_labels.add(0, new Label("Least Transfer Path" + "(" + time + ")" + ": "));
        return path_labels;
    }

    /**
     * clear input and output in GUI
     * @param start input for start station
     * @param end   input for end station
     * @param show  ListView
     */
    @Override
    public void clearAction(TextField start, TextField end, ListView<Label> show) {
        start.clear();
        end.clear();
        show.getItems().clear();
    }

    /**
     * helper method for submitHelper methods to create list of labels in each station in each path
     * @param path the path of stations
     * @return list of station labels in each path
     */
    private List<Label> create_labels(List<IStation> path) {
        List<Label> stations = new ArrayList<>();
        for (IStation station: path) {
            Label label = new Label(station.getStationName() + station.getLine().toString());
            if (station.getLine().size() < 2) {
                label.setTextFill(Paint.valueOf(station.getColor().trim()));
            } else {
                label.setTextFill(Paint.valueOf("black"));
            }
            stations.add(label);
        }
        return stations;
    }

    /**
     * make a test string
     * @param start start station's name
     * @param end   end station's name
     * @return a string of path
     */
    @Override
    public String toString(String start, String end) {
        List<Label> sp = submitHelper_sp(start, end);
        List<Label> ltp = submitHelper_ltp(start, end);
        List<String> sp_s = new ArrayList<>();
        List<String> ltp_s = new ArrayList<>();
        for (Label station: sp) {
            String name = station.getText();
            if (!(name.contains("Shortest Path") || name.equals(""))) {
                sp_s.add(name.split("\\[")[0]);
            }
        }
        for (Label station: ltp) {
            String name = station.getText();
            if (!(name.contains("Least Transfer Path"))) {
                ltp_s.add(name.split("\\[")[0]);
            }
        }
        String print_sp = sp_s.toString();
        String print_ltp = ltp_s.toString();
        return print_sp + ", " + print_ltp;
    }

    /**
     * make a test color
     * @param start start station's name
     * @param end   end station's name
     * @return colors of stations in path
     */
    @Override
    public String toColor(String start, String end) {
        List<Label> sp = submitHelper_sp(start, end);
        List<Label> ltp = submitHelper_ltp(start, end);
        List<String> sp_colors = new ArrayList<>();
        List<String> ltp_colors = new ArrayList<>();
        for (Label station: sp) {
            Paint color = station.getTextFill();
            String color_code = color.toString();
            if (!(station.getText().contains("Shortest Path") || station.getText().equals(""))) {
                sp_colors.add(color_code);
            }
        }

        for (Label station: ltp) {
            Paint color = station.getTextFill();
            String color_code = color.toString();
            if (!(station.getText().contains("Least Transfer Path"))) {
                ltp_colors.add(color_code);
            }
        }
        String colors_sp = sp_colors.toString();
        String colors_ltp = ltp_colors.toString();
        return colors_sp + ", " + colors_ltp;
    }

    /**
     * make a test number
     * @param start start station's name
     * @param end   end station's name
     * @return line numbers of stations in a path
     */
    @Override
    public String toNumber(String start, String end) {
        List<Label> sp = submitHelper_sp(start, end);
        List<Label> ltp = submitHelper_ltp(start, end);
        List<String> sp_numbers = new ArrayList<>();
        List<String> ltp_numbers = new ArrayList<>();
        for (Label station: sp) {
            String station_name = station.getText();
            if (!(station_name.contains("Shortest Path") || station_name.equals(""))) {
                String temp = station_name.split("\\[")[1];
                sp_numbers.add("[" + temp);
            }
        }

        for (Label station: ltp) {
            String station_name = station.getText();
            if (!(station_name.contains("Least Transfer Path"))) {
                String temp = station_name.split("\\[")[1];
                ltp_numbers.add("[" + temp);
            }
        }
        String numbers_sp = sp_numbers.toString();
        String numbers_ltp = ltp_numbers.toString();
        return numbers_sp + ", " + numbers_ltp;
    }

    /**
     * launch GUI application
     * @param args
     */
    public static void main(String[] args) {
        Application.launch();
    }
}
