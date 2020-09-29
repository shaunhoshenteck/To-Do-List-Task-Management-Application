package assignment9;

import java.util.List;
import java.util.Objects;

/**
 * The type Sort by priority.
 */
class SortByPriority implements IDisplayOption<ToDo> {

  /**
   * Apply sorting by priority to the list
   * @param list the list
   */
  // Sorts the list based on the custom comparator created as a static inner class within class ToDo.
  @Override
  public void apply(List<ToDo> list) {
    list.sort(ToDo.PriorityComparator);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(ToDo.PriorityComparator);
  }

  @Override
  public boolean equals(Object obj) {
    return obj.getClass().equals(this.getClass());
  }
}
