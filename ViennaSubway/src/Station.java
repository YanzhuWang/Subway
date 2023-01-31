import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Station implements IStation{

	private String stationName;
	private String color;
	private List<Integer> lineList = new ArrayList<Integer>();

	public Station(String stationName, String color) {
		this.stationName = stationName;
		this.color = color;
	}
	@Override
	public String getStationName() {
		return this.stationName;
	}

	@Override
	public void setStationName(String station) {
		this.stationName = station;
	}

	@Override
	public String getColor() {
		return this.color;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void setLine(Integer line) {
		this.lineList.add(line);
	}

	@Override
	public List<Integer> getLine() {
		return lineList;
	}

	@Override
	public boolean equals(Object O) {
		if(((Station) O).getStationName().equals(this.stationName)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.stationName.hashCode();
	}

}

