//--== IStationEdgeLoader.java --==
//Name: Abhishek
//CSL Username: grewal
//Email: agrewal2@wisc.edu
//Lecture #: 001 @11:00am
//Notes to grader: -

import java.util.List;
import java.util.HashMap;
import java.io.FileNotFoundException;

public interface IStationEdgeLoader {
    /**
     * load all edges into a list
     * 
     * @param filepath the relative path to the file
     * @return a list of edges in the file
     * @throws FileNotFoundException if file is not found
     */

    HashMap<IStation, List<IEdge>> loadStation(String filepath) throws FileNotFoundException;

}
