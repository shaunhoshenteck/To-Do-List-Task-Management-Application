package assignment9;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The type To do helper.
 */
public class ToDoHelper {
    private static String headers;

    /**
     * Parse to do csv list.
     * @param toDoCSV the to do csv
     * @return the list
     */
    public static List<ToDo> parseToDoCSV(String toDoCSV) {

        List<ToDo> toDoList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(toDoCSV);
        BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            // creating headers
            String data;
            headers = bufferedReader.readLine();

            // reads lines after the header and splits data into their correct header
            while ((data = bufferedReader.readLine()) != null) {
                String[] values = data.split(",(?=([^\"]|\"[^\"]*\")*$)");
                ToDo.ToDoBuilder tdb = new ToDo.ToDoBuilder();
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("\"", "").trim();
                    tdb.setDataAtIndex(i, values[i]);
                }

                // build the todo object and add it to the list of ToDos.
                toDoList.add(tdb.build());
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("A file was not found: " + e.getMessage());
            e.printStackTrace();
        } catch (
                IOException ioe) {
            System.out.print("Something went wrong: " + ioe.getMessage());
            ioe.printStackTrace();
        }
        return toDoList;
    }

    public static String formattedHeaders() {
        return headers;
    }
}

