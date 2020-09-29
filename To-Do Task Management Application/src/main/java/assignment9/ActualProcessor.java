package assignment9;

import assignment9.ToDo.ToDoBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The type Actual processor.
 */
// ActualProcessor class handles all of the processing: Basically switching from incomplete to complete,
// Sorting the list based on user requirements, adding filters and displaying after sorting and filtering.
public class ActualProcessor {

    /**
     * Add to do.
     * @param tdb the tdb
     * @param listToAdd the list to add
     */
    // Method adds a new ToDo to the list of Todos.
    public static void addToDo(List<ToDo> listToAdd, ToDoBuilder tdb) {
        listToAdd.add(tdb.build());
        System.out.println("New Todo has been added!");
    }

    /**
     * Sets complete.
     * @param toDoHashSet the to do hash set
     * @param list the list
     */
    // IDs in the set will have their complete status changed to true. Iterates through the
    // set first then compares the id with ToDo ids in the list. If match, complete field of that
    // object gets switched to true.
    public static void setComplete(Set<Integer> toDoHashSet, List<ToDo> list) {
        for (Integer id : toDoHashSet) {
            for (ToDo item : list) {
                if (item.getID().equals(id)) {
                    item.setCompleted(true);
                    return;
                }
            }
            System.out.println("Error: Todo id: " + id + " not found");
        }
        System.out.println("Todo completed!");
    }

    /**
     * Display to dos.
     * @param list the list
     * @param displayOptionSet the display option set
     */
    // A set of IDisplayOption objects. This set is populated based on command line inputs.
    // Uses polymorphism -> we iterate through each IDisplayOption object in the set, then we
    // use that specific object's .apply() method on the list to filter/sort it.
    public static void displayToDos(List<ToDo> list, Set<IDisplayOption<ToDo>> displayOptionSet) {

        List<ToDo> copy = new ArrayList<>();
            for (ToDo todo : list) {
                copy.add(new ToDo(todo));
        }

        // Set -> [FilterByIncomplete(), SortByDate()]
        // 1) FilterByIncomplete.apply(list)
        // 2) SortByDate.apply(list)

        for (IDisplayOption<ToDo> displayOption: displayOptionSet) {
            displayOption.apply(copy);
        }

        // Print out the list after filtering and sorting
        for (ToDo toDo: copy) {
            System.out.println(toDo.toString());
        }
    }

    public static void setID(List<ToDo> list, ToDoBuilder tdb) {
        tdb.setID(String.valueOf(list.size() + 1));
    }
}
