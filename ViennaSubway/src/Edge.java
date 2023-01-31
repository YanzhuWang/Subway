
public class Edge implements IEdge{
	
	private String startStation;
	private String endStation;
	private String color;
	private Integer cost;
	private Integer line;
	
	public Edge(String start, String end, int line, String color, int cost) {
		this.startStation = start;
		this.endStation = end;
		this.line = line;
		this.color = color;
		this.cost = cost;
	}
	
	@Override
	public void setStartStation(String station) {
		this.startStation = station;
	}

	@Override
	public void setEndStation(String station) {
		this.endStation = station;
	}

	@Override
	public void setCost(Integer cost) {
		this.cost = cost;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void setLine(Integer line) {
		this.line = line;
	}

	@Override
	public String getStartStation() {
		return this.startStation;
	}

	@Override
	public String getEndStation() {
		return this.endStation;
	}

	@Override
	public Integer getCost() {
		return this.cost;
	}

	@Override
	public String getColor() {
		return this.color;
	}

	@Override
	public Integer getLine() {
		return this.line;
	}

}
