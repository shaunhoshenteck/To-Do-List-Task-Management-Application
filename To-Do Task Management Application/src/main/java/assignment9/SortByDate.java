package assignment9;

import java.util.List;
import java.util.Objects;

/**
 * The type Sort by date.
 */
public class SortByDate implements IDisplayOption<ToDo>{

  /**
   * Apply sorting by date to the list
   * @param list the list
   */
 // Sorts the list based on the custom comparator created as a static inner class within class ToDo.
  @Override
  public void apply(List<ToDo> list) {
    list.sort(ToDo.DueComparator);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(ToDo.DueComparator);
  }

  @Override
  public boolean equals(Object obj) {
    return obj.getClass().equals(this.getClass());
  }
}
