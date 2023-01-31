import javafx.scene.paint.Paint;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Test_1 {
    public static void main(String[] args) throws FileNotFoundException {
        IStationEdgeLoader e = new StationEdgeLoader();
        HashMap<IStation, List<IEdge>> m = e.loadStation("src/Vienna-subway.txt");
        Set<IStation> a = m.keySet();
        for (IStation as: a) {
            System.out.println(as.getLine().toString());
            System.out.println(as.getColor());
            System.out.println(as.getStationName() + "," + m.get(as).get(0).getEndStation() + "," + m.get(as).get(0).getCost());
        }
        System.out.println(a.size());
    }
}
