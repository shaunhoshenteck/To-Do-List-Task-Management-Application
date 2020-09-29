package assignment9;

import java.util.List;

/**
 * The type Filter by incomplete.
 */
class FilterByIncomplete implements IDisplayOption<ToDo> {

  /**
   * Apply the incomplete filter to the list
   * @param list the list
   */
  // Uses a method of List called list.remove to remove an object from the list if the
  // objects .getCompleted() method matches.
  // This method uses lambda expression: for every object that has getCompleted() as true,
  // remove it from list.
  @Override
  public void apply(List<ToDo> list) { list.removeIf(todo -> todo.getCompleted());
  }

  @Override
  public int hashCode() {
    return 42;
  }

  @Override
  public boolean equals(Object obj) {
    return obj.getClass().equals(this.getClass());
  }
}
