import edu.wisc.cs.cs400.JavaFXTester;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing the functionality of Vienna Subway Guide GUI
 * @author Haiyi Wang
 */
public class FrontendDeveloperTests extends JavaFXTester{
    StationSearcherApp app = new StationSearcherApp();
    /**
     * put TestApp into this class
     */
    public FrontendDeveloperTests() {
        super(StationSearcherTestApp.class);
    }


    /**
     * Test the string of path display in listview is the same as expected
     */
    @Test
    public void test1() {
        ListView<Label> listview = lookup("#display").query();
        clickOn("#start").write("a");
        clickOn("#end").write("c");
        clickOn("#submit");
        List<String> list = new ArrayList<>();
        for (Label label: listview.getItems()) {
            if (!(label.getText().equals(""))) {
                list.add(label.getText());
            }
        }
        assertEquals("[Shortest Path(9): , a[1], b[1], f[1, 2], c[2], Least Transfer Path(11): , a[1], d[1, 2], c[1]]",
                list.toString());
    }

    /**
     * Test the color of path display in listview is the same as expected
     */
    @Test
    public void test2() {
        ListView<Label> listview = lookup("#display").query();
        clickOn("#start").write("a");
        clickOn("#end").write("c");
        clickOn("#submit");
        List<String> list = new ArrayList<>();
        for (Label label: listview.getItems()) {
            if (!(label.getText().equals(""))) {
                list.add(String.valueOf(label.getTextFill()));
            }
        }
        List<String> color_code = new ArrayList<>();
        color_code.add("0x333333ff");
        color_code.add(Paint.valueOf("red").toString());
        color_code.add(Paint.valueOf("red").toString());
        color_code.add(Paint.valueOf("black").toString());
        color_code.add(Paint.valueOf("green").toString());
        color_code.add("0x333333ff");
        color_code.add(Paint.valueOf("red").toString());
        color_code.add(Paint.valueOf("black").toString());
        color_code.add(Paint.valueOf("green").toString());
        assertEquals(color_code.toString(),
                list.toString());
    }

    /**
     * Test the line numbers of path display in listview is the same as expected
     */
    @Test
    public void test3() {
        ListView<Label> listview = lookup("#display").query();
        clickOn("#start").write("a");
        clickOn("#end").write("c");
        clickOn("#submit");
        List<String> list = new ArrayList<>();
        for (Label label: listview.getItems()) {
            if (!(label.getText().equals("") || label.getText().contains("Shortest Path") ||
                    label.getText().contains("Least Transfer Path"))) {
                String temp = label.getText().split("\\[")[1];
                Pattern p = Pattern.compile("\\d");
                Matcher m = p.matcher(temp);
                while(m.find()) {
                    list.add(m.group());
                }
            }
        }
        assertEquals("[1, 1, 1, 2, 2, 1, 1, 2, 1]",
                list.toString());
    }

    /**
     * Test whether clear button will clear any input and output
     */
    @Test
    public void test4() {
        ListView<Label> listview = lookup("#display").query();
        clickOn("#start").write("a");
        clickOn("#end").write("c");
        clickOn("#submit");
        TextField start = lookup("#start").query();
        TextField end = lookup("#end").query();
        List<String> list = new ArrayList<>();
        list.add(start.getText());
        list.add(end.getText());
        for (Label label: listview.getItems()) {
            if (!(label.getText().equals(""))) {
                list.add(label.getText());
            }
        }
        assertEquals("[a, c, Shortest Path(9): , a[1], b[1], f[1, 2], c[2], Least Transfer Path(11): , a[1], d[1, 2], c[1]]",
                list.toString());

        clickOn("#clear");
        List<String> list_clear = new ArrayList<>();
        list_clear.add(start.getText());
        list_clear.add(end.getText());
        for (Label label: listview.getItems()) {
            if (!(label.getText().equals(""))) {
                list.add(label.getText());
            }
        }
        assertEquals("[, ]",list_clear.toString());
    }

    /**
     * Test whether submit again after clearing anything will show the same string of path as expected
     */
    @Test
    public void test5() {
        ListView<Label> listview = lookup("#display").query();
        clickOn("#start").write("a");
        clickOn("#end").write("c");
        clickOn("#submit");
        clickOn("#clear");
        clickOn("#start").write("a");
        clickOn("#end").write("c");
        clickOn("#submit");
        List<String> list = new ArrayList<>();
        for (Label label: listview.getItems()) {
            if (!(label.getText().equals(""))) {
                list.add(label.getText());
            }
        }
        assertEquals("[Shortest Path(9): , a[1], b[1], f[1, 2], c[2], Least Transfer Path(11): , a[1], d[1, 2], c[1]]",
                list.toString());
    }

    /**
     * Test whether station names or station orders are the same as expected
     * I choose a path that need to get to two different lines to increase difficulty
     * From Donauinsel to Schottenring
     * @throws FileNotFoundException
     */
    @Test
    public void test6() throws FileNotFoundException {
        // create backend into frontend app class
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
        app.backend = new StationSearcherBackend(stations, edges);

        // soString is a test method in app class to test stations' names
        assertEquals("[Donauinsel, Vorgartenstrasse, Praterstern, Taborstrasse, Schottenring], [Donauinsel, Vorgartenstrasse, Praterstern, Taborstrasse, Schottenring]", app.toString("Donauinsel", "Schottenring"));
    }

    /**
     * Test whether station colors or station line numbers are the same as expected
     * I choose a path that need to get to two different lines to increase difficulty
     * From Donauinsel to Schottenring
     * @throws FileNotFoundException
     */
    @Test
    public void test7() throws FileNotFoundException {
        // create backend into frontend app class
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
        app.backend = new StationSearcherBackend(stations, edges);

        // toColor method is test method in app class to test colors of stations
        // 0xff0000ff is red, 0x000000ff is black, 0x800080ff is purple
        assertEquals("[0xff0000ff, 0xff0000ff, 0x000000ff, 0x800080ff, 0x000000ff], [0xff0000ff, 0xff0000ff, 0x000000ff, 0x800080ff, 0x000000ff]", app.toColor("Donauinsel", "Schottenring"));

        // toNumber method is test method in app class to test line numbers of stations
        assertEquals("[[1], [1], [1, 2], [2], [2, 4]], [[1], [1], [1, 2], [2], [2, 4]]", app.toNumber("Donauinsel", "Schottenring"));
    }

    /**
     * Test algorithm in a simple short path
     * Test whether it can run shortestPath and leastTransferPath correctly
     * and whether it can calculate costs correctly
     */
    @Test
    public void test8() {
        StationGraph<String> alg = new StationGraph<>();
        alg.insertVertex("a");
        alg.insertVertex("b");
        alg.insertVertex("c");
        alg.insertVertex("d");
        alg.insertVertex("e");
        alg.insertEdge("a", "b", 1);
        alg.insertEdge("b", "c", 2);
        alg.insertEdge("c", "e", 4);
        alg.insertEdge("a", "d", 6);
        alg.insertEdge("d", "e", 12);
        List<String> sp = alg.shortestPath("a", "e");
        List<String> ltp = alg.leastTransferPath("a", "e");
        assertEquals("[a, b, c, e]", sp.toString());
        assertEquals("[a, d, e]", ltp.toString());
        assertEquals("7", String.valueOf(alg.getPathCost("a", "e")));
        assertEquals("18", String.valueOf(alg.leastTransferPathCost("a", "e")));
    }

    /**
     * Test algorithm in a complex and long path
     * Test whether it can run shortestPath and leastTransferPath correctly
     * and whether it can calculate costs correctly
     */
    @Test
    public void test9() {
        StationGraph<String> alg = new StationGraph<>();
        alg.insertVertex("a");
        alg.insertVertex("b");
        alg.insertVertex("c");
        alg.insertVertex("d");
        alg.insertVertex("e");
        alg.insertVertex("f");
        alg.insertVertex("g");
        alg.insertVertex("h");
        alg.insertVertex("i");
        alg.insertVertex("j");
        alg.insertEdge("a", "e", 2);
        alg.insertEdge("a", "i", 1);
        alg.insertEdge("b", "a", 1);
        alg.insertEdge("c", "b", 1);
        alg.insertEdge("c", "d", 9);
        alg.insertEdge("d", "g", 6);
        alg.insertEdge("e", "c", 1);
        alg.insertEdge("e", "f", 4);
        alg.insertEdge("e", "h", 12);
        alg.insertEdge("f", "d", 3);
        alg.insertEdge("g", "h", 4);
        alg.insertEdge("g", "j", 6);
        alg.insertEdge("h", "j", 100);
        alg.insertEdge("i", "j", 1);
        List<String> sp = alg.shortestPath("e", "j");
        List<String> ltp = alg.leastTransferPath("e", "j");
        assertEquals("[e, c, b, a, i, j]", sp.toString());
        assertEquals("[e, h, j]", ltp.toString());
        assertEquals("5", String.valueOf(alg.getPathCost("e", "j")));
        assertEquals("112", String.valueOf(alg.leastTransferPathCost("e", "j")));
    }
}
