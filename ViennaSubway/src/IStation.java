//--== IStation.java --==
//Name: Abhishek
//CSL Username: grewal
//Email: agrewal2@wisc.edu
//Lecture #: 001 @11:00am
//Notes to grader: -

import java.util.List;

public interface IStation {
    String getStationName(); // retrieve the station name

    void setStationName(String station); // set the station name

    String getColor(); // get the color of the station

    void setColor(String color); // set the color of the station

    void setLine(Integer line); // set the line of the station

    List<Integer> getLine(); // get the line of the station

}
