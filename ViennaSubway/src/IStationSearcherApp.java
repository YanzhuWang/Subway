import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This interface is to define GUI application
 *
 * @author Haiyi Wang & Daidan Lu
 */
public interface IStationSearcherApp {

  /**
   * There are two inputs for typing start station's name and end station's name
   * There are two button:
   * one for submitting inputs and get subway routes
   * one for clearing every text in the GUI
   * There is a listView for displaying the result of stations name as list
   * ListView will store names in labels to help change color and font of stations' names
   *
   * @param stage stage to show
   */

  public void start(Stage stage) throws FileNotFoundException;

  /**
   * prviate function to make events after clicking submit button
   *
   * @param start input for start station
   * @param end   input for end station
   * @param show  ListView
   */
  public void submitAction(TextField start, TextField end, ListView<Label> show);

  /**
   * prviate function to make clear events after clicking clear button
   *
   * @param start input for start station
   * @param end   input for end station
   * @param show  ListView
   */
  public void clearAction(TextField start, TextField end, ListView<Label> show);

  /**
   * helper method to help submitAction method to make a list of stations' information
   * <p>
   * THIS IS ALSO USED IN TEST
   *
   * @param start start station's name
   * @param end   end station's name
   * @return list of stations' information in the form of Label
   */
  private List<Label> submitHelper(String start, String end) {
    return null;
  }

  /**
   * To make search results as String for test
   *
   * @param start start station's name
   * @param end   end station's name
   * @return String of subway route information
   */
  public String toString(String start, String end);

  /**
   * To make sure that every stations' colors are the same as expected
   *
   * @param start start station's name
   * @param end   end station's name
   * @return String of colors of each station
   */
  public String toColor(String start, String end);

  /**
   * To make sure that every stations' line numbers are the same as expected
   *
   * @param start start station's name
   * @param end   end station's name
   * @return String of lines of each station
   */
  public String toNumber(String start, String end);
}
