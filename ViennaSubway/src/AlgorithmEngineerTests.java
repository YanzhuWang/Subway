import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlgorithmEngineerTests {

  private static StationGraph<String> graph;
  
  @BeforeEach
  public void createGraph() {
    graph = new StationGraph<>();
    // insert vertices A-F
    graph.insertVertex("A");
    graph.insertVertex("B");
    graph.insertVertex("C");
    graph.insertVertex("D");
    graph.insertVertex("E");
    graph.insertVertex("F");
    // insert edges from Week 11. Shortest Path Activity
    graph.insertEdge("A", "B", 6);
    graph.insertEdge("A", "C", 2);
    graph.insertEdge("A", "D", 5);
    graph.insertEdge("B", "E", 1);
    graph.insertEdge("B", "C", 2);
    graph.insertEdge("C", "B", 3);
    graph.insertEdge("C", "F", 1);
    graph.insertEdge("D", "E", 3);
    graph.insertEdge("E", "A", 4);
    graph.insertEdge("F", "A", 1);
    graph.insertEdge("F", "D", 1);
  }
  
  /**
   * Checks if least transfer path is correct when there is a longer shortest path
   */
  @Test
  public void test1() {
    assertEquals("[A, D, E]", graph.leastTransferPath("A", "E").toString());
  }
  
  /**
   * Checks if the cost of the least transfer path is correct
   */
  @Test
  public void test2() {
    assertTrue(graph.leastTransferPathCost("A", "E") == 8);
  }
  
  /**
   * Checks if the least transfer path is correct when it is also the shortest path
   */
  @Test
  public void test3() {
    assertEquals("[B, E]", graph.leastTransferPath("B", "E").toString());
  }
  
  /**
   * Checks if shortestPath runs correctly after least transfer path is found using altered weights
   */
  @Test
  public void test4() {
    //make sure leastTransferPath doesn't permanently alter the edge weights
    assertEquals("[A, D, E]", graph.leastTransferPath("A", "E").toString());
    assertEquals("[A, C, B, E]", graph.shortestPath("A", "E").toString());
  }
  
  /**
   * Checks if the cost of shortestPath returns correctly after it is altered by leastTransferPath
   */
  @Test
  public void test5() {
    assertTrue(graph.leastTransferPathCost("A", "E") == 8);
    assertTrue(graph.getPathCost("A", "E") == 6);
  }


}

