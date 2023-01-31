//--== IEdge.java --==
//Name: Abhishek
//CSL Username: grewal
//Email: agrewal2@wisc.edu
//Lecture #: 001 @11:00am
//Notes to grader: -

public interface IEdge {
        void setStartStation(String station); // set start station of egde
        void setEndStation(String station); // set end station of edge
        void setCost(Integer cost); // set time cost of edge
        void setColor(String color); // set color of edge
        void setLine(Integer line); // set line of the edge
        String getStartStation(); // get start station connected by the edge
        String getEndStation(); // get the end station connected by the edge
        Integer getCost(); // get the time cost of the edge
        String getColor(); // get color of the edge
        Integer getLine(); // get the line of the edge
}

