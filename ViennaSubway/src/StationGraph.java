import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class StationGraph<T> extends CS400Graph<T> implements IStationGraph<T> {

  /**
   * Accessor method for the least transferred path from start to end
   * 
   * @param start data of the starting vertex
   * @param end   data of the ending vertex
   * @return List<T> list of data in order from start to end that make the least transferred path
   */
  @Override
  public List<T> leastTransferPath(T start, T end) {
    return leastVertexPath(start, end).dataSequence;
  }

  /**
   * Acessor method for the cost of the least transferred path from start to end
   * 
   * @param start data of the starting vertex
   * @param end   data of the ending vertex
   * @return int cost of the path
   */
  @Override
  public int leastTransferPathCost(T start, T end) {
    // must calculate path cost manually because Path object returned from helper method has altered
    // distance which is incorrect
    int toReturn = 0;
    List<T> dataSeq = new ArrayList<T>();
    dataSeq = leastTransferPath(start, end);

    // traverse through all the edges in the dataSeq and add up their weights to get path cost
    for (int i = 0; i < dataSeq.size() - 1; i++) {
      for (Edge e : vertices.get(dataSeq.get(i)).edgesLeaving) {
        if (e.target.data == vertices.get(dataSeq.get(i + 1)).data)
          toReturn += e.weight;
      }
    }
    return toReturn;
  }


  /**
   * Helper method that runs the dijkstra's algo with slightly altered weights so least transfer
   * path is given
   * 
   * @param start data of the starting vertex
   * @param end   data of the ending vertex
   * @return Path object containing the least transfer path from start to end
   */
  protected Path leastVertexPath(T start, T end) {
    if (!containsVertex(start) || !containsVertex(end))
      throw new NoSuchElementException("Graph does not conatin vertix");
    // make priority queue for all initial paths
    PriorityQueue<Path> allDiscPaths = new PriorityQueue<Path>();
    // make arraylist for all selected vertices
    ArrayList<Vertex> visitedV = new ArrayList<Vertex>();

    // first path to start vertex itself
    Path toCheck = new Path(vertices.get(start));
    allDiscPaths.add(toCheck); // path to itself is a discovered path

    while (!allDiscPaths.isEmpty()) {
      toCheck = allDiscPaths.remove();
      // check if we have reached the end vertex
      if (toCheck.end.data == end || toCheck.end.data.equals(end))
        return toCheck;
      // if end vertex is not reached, add it to visited
      visitedV.add(toCheck.end);
      // use edges of the end vertex in the shortest path to branch out
      for (int i = 0; i < toCheck.end.edgesLeaving.size(); i++) {
        int temp = toCheck.end.edgesLeaving.get(i).weight;
        toCheck.end.edgesLeaving.get(i).weight = 1;
        Path toAdd = new Path(toCheck, toCheck.end.edgesLeaving.get(i));
        // if end vertex of path is not already visited, add path
        if (!visitedV.contains(toAdd.end)) {
          allDiscPaths.add(toAdd);
        }
        toCheck.end.edgesLeaving.get(i).weight = temp;
      }
    }
    throw new NoSuchElementException("No path found");
  }

}

