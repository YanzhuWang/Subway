import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class DataWranglerTests {

	/*
	 * test to see if station get methods work as intended
	 */
	@Test
	void test1() {
		Station station = new Station("Madison", "red");
		assertEquals(station.getStationName(), "Madison");
		assertTrue(station.getColor().equals("red"));
	}
	/*
	 * test to see if station class get methods work as intended
	 */
	@Test
	void test2() {
		Station station =  new Station("Madison", "red");
		station.setStationName("Chicago");
		station.setColor("blue");
		station.setLine(1);
		station.setLine(2);
		assertEquals(station.getStationName(), "Chicago");
		assertEquals(station.getColor(), "blue");
		assertEquals(station.getLine().get(0), 1);
		assertEquals(station.getLine().get(1), 2);
	}
	
	/*
	 * test to see if edge class get methods work as intended
	 */
	@Test
	void test3() {
		Edge edge = new Edge("Madison", "Chicago", 1, "red", 5);
		assertEquals(edge.getStartStation(), "Madison");
		assertEquals(edge.getEndStation(), "Chicago");
		assertEquals(edge.getLine(), 1);
		assertEquals(edge.getColor(), "red");
		assertEquals(edge.getCost(), 5);
	}
	
	@Test
	void test4() throws FileNotFoundException {
		StationEdgeLoader loader = new StationEdgeLoader();
		loader.loadStation("src/Vienna-subway.txt");
	}
	
	/*
	 * tests to see if exception is thrown
	 */
	@Test
	void test5() throws FileNotFoundException {
		try {
			StationEdgeLoader loader = new StationEdgeLoader();
                	loader.loadStation("src/Vienna-subway.txt");
		}
		catch(FileNotFoundException e){
		}
	}
}
