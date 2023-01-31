import java.util.List;

public interface IStationGraph<T> extends GraphADT<T> {

	/*
	 * returns a path with the least amount of edges between startingVertex and destinationVertex
	 *
	 * @param start	the data item in the starting vertex for the path
	 * @param end	the data item in the destination vertex for the path
	 * @return list of data items in vertices in order on the least number of edges  path between vertex with
	 *  data item startingVertex and vertex with data item destinationVertex, including both startingVertex and destinationVertex
	 */
	public List<T> leastTransferPath(T start, T end);

	public int leastTransferPathCost(T start, T end);
}
