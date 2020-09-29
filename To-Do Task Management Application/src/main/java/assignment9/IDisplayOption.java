package assignment9;

import java.util.List;

/**
 * The interface Display option.
 * @param <T> the type parameter
 */
// Interface for our Filter and Sort objects. Will use polymorphism to apply these filter and
// sort methods.
public interface IDisplayOption<T> {

   /**
    * Apply the filters for display
    * @param list the list
    */
   void apply(List<T> list);
}
