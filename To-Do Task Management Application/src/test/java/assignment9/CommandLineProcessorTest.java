package assignment9;

import static org.junit.Assert.*;

import assignment9.ToDo.ToDoBuilder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class CommandLineProcessorTest {
  String[] testArgs1 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text",
      "grocery", "shopping", "--due", "04/07/2020", "--priority", "2"};
  String[] testArgs2 = {"--csv-file", "todos.csv", "--complete-todo", "1", "--complete-todo", "5"};
  String[] testArgs3 = {"--csv-file", "todos.csv", "--display", "--show-incomplete", "--sort-by-date"};
  CommandLineProcessor testCLP1 = new CommandLineProcessor("error-msg.txt");
  CommandLineProcessor testCLP2 = new CommandLineProcessor("error-msg.txt");
  CommandLineProcessor testCLP3 = new CommandLineProcessor("error-msg.txt");
  CommandLineProcessor testCLP4 = new CommandLineProcessor("error-msg.txt");
  ToDoBuilder testTdb = new ToDoBuilder().setText("grocery shopping").setDue("04/07/2020").setPriority("2").
      setCategory("?").setCompleted("false");
  Set<Integer> testTodoSet = new HashSet<Integer>() {{add(1); add(5);}};
  Set<IDisplayOption<ToDo>> testDisplaySet = new HashSet<IDisplayOption<ToDo>>() {{
    add(new FilterByIncomplete()); add(new SortByDate());}};

  String[] illArgs1 = {"--add-todo", "--todo-text", "grocery", "--due", "04/07/2020", "--priority", "2"};
  String[] illArgs2 = {"--csv-file", "--add-todo", "--todo-text",
      "grocery", "--due", "04/07/2020", "--priority", "2"};
  String[] illArgs3 = {"--csv-file", "todos.csv", "--add-todo",
      "grocery", "--due", "04/07/2020", "--priority", "2"};
  String[] illArgs4 = {"--csv-file", "todos.csv", "--todo-text", "grocery", "--due", "04/07/2020", "--priority", "2"};
  String[] illArgs5 = {"--csv-file", "todos.csv", "--complete-todo"};
  String[] illArgs6 = {"--csv-file", "todos.csv", "--complete-todo", "one"};
  String[] illArgs7 = {"--csv-file", "todos.csv", "--display", "--sort-by-priority", "--sort-by-date"};
  String[] illArgs8 = {"--csv-file", "todos.csv", "--show-incomplete", "--sort-by-date"};



  @Before
  public void setUp() throws Exception {
    testCLP1.processArgument(testArgs1);
    testCLP2.processArgument(testArgs2);
    testCLP3.processArgument(testArgs3);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs() throws CommandLineException {
    testCLP4.processArgument(illArgs1);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs2() throws CommandLineException {
    testCLP4.processArgument(illArgs2);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs3() throws CommandLineException {
    testCLP4.processArgument(illArgs3);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs4() throws CommandLineException {
    testCLP4.processArgument(illArgs4);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs5() throws CommandLineException {
    testCLP4.processArgument(illArgs5);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs6() throws CommandLineException {
    testCLP4.processArgument(illArgs6);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs7() throws CommandLineException {
    testCLP4.processArgument(illArgs7);
  }

  @Test(expected = CommandLineException.class)
  public void illegalArgs8() throws CommandLineException {
    testCLP4.processArgument(illArgs8);
  }

  @Test
  public void getDisplayOptionSet() {
    assertEquals(testDisplaySet, testCLP3.getDisplayOptionSet());
  }

  @Test
  public void getCsvFile() {
    assertEquals("todos.csv", testCLP1.getCsvFile());
  }

  @Test
  public void getCompleteTodoSet() {
    assertEquals(testTodoSet, testCLP2.getCompleteTodoSet());
  }

  @Test
  public void getTdb() {
    assertEquals(testTdb, testCLP1.getTdb());
  }
}