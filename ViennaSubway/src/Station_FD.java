import java.util.List;

public class Station_FD implements IStation{
    public String station_name;
    public String color;
    public List<Integer> line;

    public Station_FD(String station_name, String color, List<Integer> line) {
        this.station_name = station_name;
        this.color = color;
        this.line = line;
    }
    @Override
    public String getStationName() {
        return station_name;
    }

    @Override
    public void setStationName(String station) {

    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setLine(Integer line) {

    }

    @Override
    public List<Integer> getLine() {
        return line;
    }
}
