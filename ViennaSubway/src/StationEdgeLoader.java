//--== IStationEdgeLoader.java --==
//Name: Abhishek
//CSL Username: grewal
//Email: agrewal2@wisc.edu
//Lecture #: 001 @11:00am
//Notes to grader: -

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StationEdgeLoader implements IStationEdgeLoader{
	/**
	 * load all edges into a list
	 *
	 * @param filepath the relative path to the file
	 * @return a list of edges in the file
	 * @throws FileNotFoundException if file is not found
	 */

	public HashMap<IStation, List<IEdge>> loadStation(String filepath) throws FileNotFoundException{
		HashMap<IStation, List<IEdge>> stations = new HashMap<IStation, List<IEdge>>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(filepath));
			for(int i = 1; i < lines.size(); i++) {
				String[] comp = new String[5];
				comp = lines.get(i).split(",");
				if (comp[4].equals("2Pilgramgasse")) {
					comp[4] = "2";
				}
				Station station1 = new Station(comp[0].trim(), comp[3]);
				Station station2 = new Station(comp[1].trim(), comp[3]);
				station1.setLine(Integer.parseInt(comp[2].trim()));
				Edge edge = new Edge(comp[0].trim(), comp[1].trim(), Integer.parseInt(comp[2].trim()), comp[3].trim(), Integer.parseInt(comp[4].trim()));
				if (stations.containsKey(station1)) {
					stations.get(station1).add(edge);
					Set<IStation> keys = stations.keySet();
					for(IStation key: keys) {
						if(key.equals(station1)) {
							if(!key.getLine().contains(Integer.parseInt(comp[2].trim()))) {
								key.setLine(Integer.parseInt(comp[2].trim()));

							}
							if(!key.getColor().equals(station1.getColor())) {
								key.setColor("black");
							}
						}
					}
				}
				else {
					ArrayList<Integer> slines = new ArrayList<Integer>();
					slines.add(Integer.parseInt(comp[2].trim()));
					List<IEdge> edges = new ArrayList<IEdge>();
					edges.add(edge);
					stations.put(station1, edges);
				}
				// removing this code because backend developer handles this
				/**
				 if (stations.containsKey(station2)) {
				 stations.get(station2).add(edge);
				 Set<IStation> keys = stations.keySet();
				 for(IStation key: keys) {
				 if(key.equals(station2)) {
				 if(key.getLine().contains(Integer.parseInt(comp[2].trim()))) {
				 key.setLine(Integer.parseInt(comp[2].trim()));

				 }
				 if(!key.getColor().equals(station2.getColor())) {
				 key.setColor("black");
				 }
				 }
				 }
				 }
				 else {
				 ArrayList<Integer> slines = new ArrayList<Integer>();
				 slines.add(Integer.parseInt(comp[2].trim()));
				 List<IEdge> edges = new ArrayList<IEdge>();
				 edges.add(edge);
				 stations.put(station2, edges);
				 }
				 **/
			}
		}
		catch(IOException e) {
			throw new FileNotFoundException("error reading txt file");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return stations;
	}

}
