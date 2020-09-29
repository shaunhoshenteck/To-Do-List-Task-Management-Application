package assignment9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class ToDoWriter writes the todos to a csv file
 */
public class ToDoWriter {

  /**
   * Writes out the todos to a csv file
   * @param headerList the headers for the todos, as a String
   * @param listToUse list of todos to write out, as a list
   * @param TodoCSV name of csv files to be written, as a String
   */
  public static void writeToCSV(String headerList, List<ToDo> listToUse, String TodoCSV) {
    try (FileWriter fileToWrite = new FileWriter(TodoCSV);
        BufferedWriter writer = new BufferedWriter(fileToWrite)) {
      writer.write(headerList + System.lineSeparator());
      for (ToDo todoObj : listToUse) {
        writer.write(todoObj.toString() + System.lineSeparator());
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
