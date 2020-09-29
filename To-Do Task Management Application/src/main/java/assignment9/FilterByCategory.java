package assignment9;

import java.util.List;
import java.util.Objects;

/**
 * The type Filter by category.
 */
class FilterByCategory implements IDisplayOption<ToDo> {
  private String category;

  /**
   * Instantiates a new Filter by category.
   * @param category the category
   */
  public FilterByCategory(String category) {
    this.category = category;
  }

  /**
   * Apply the category filter to the list
   * @param list the list
   */
  // Uses a method of List called list.remove to remove an object from the list.
  // This method uses lambda expression: for every object, remove if getCategory() does not match
  // this.category.
  @Override
  public void apply(List<ToDo> list) {
    list.removeIf(todo -> !todo.getCategory().equals(this.category));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FilterByCategory)) {
      return false;
    }
    FilterByCategory that = (FilterByCategory) o;
    return Objects.equals(category, that.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category);
  }
}
