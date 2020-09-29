package assignment9;

import static org.junit.Assert.*;

import assignment9.ToDo.ToDoBuilder;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class ToDoTest {
  ToDoBuilder testTdb = new ToDoBuilder().setText("grocery").setDue("04/07/2020").setPriority("?").
      setCategory("?").setCompleted("false").setID("1");
  ToDo testTodo;

  @Before
  public void setUp() throws Exception {
    testTodo = new ToDo(testTdb);
  }

  @Test
  public void getID() {
    assertEquals((Integer) 1, testTodo.getID());
  }

  @Test
  public void getText() {
    assertEquals("grocery", testTodo.getText());
  }

  @Test
  public void getCompleted() {
    assertFalse(testTodo.getCompleted());
  }

  @Test
  public void getDue() {
    assertEquals(LocalDate.of(2020,4,7), testTodo.getDue());
  }

  @Test
  public void getPriority() {
    assertEquals((Integer) 3, testTodo.getPriority());
  }

  @Test
  public void getCategory() {
    assertEquals("unknown", testTodo.getCategory());
  }

  @Test
  public void setCompleted() {
    testTodo.setCompleted(true);
    assertTrue(testTodo.getCompleted());
  }
}