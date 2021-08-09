package problem1;

import static org.junit.Assert.*;

import org.junit.Test;

public class StandaloneFlagTest {

  private StandaloneFlag addTodo = new StandaloneFlag("--add-todo");;
  private StandaloneFlag addTodoCopy = new StandaloneFlag("--add-todo");;
  private StandaloneFlag addTooDo = new StandaloneFlag("--add-toodo");

  @Test
  public void getName() {
    assertEquals(addTodo.getName(), "--add-todo");
  }

  @Test
  public void testEquals() {
    assertEquals(addTodo, addTodo);
    assertEquals(addTodo, addTodoCopy);
    assertEquals(addTodo, addTodoCopy);
    assertNotEquals(addTodo, 8);
    assertNotNull(addTodo);
    assertNotEquals(addTodo, addTooDo);
  }

  @Test
  public void testHashCode() {
    assertEquals(addTodo.hashCode(), addTodoCopy.hashCode());
    assertNotEquals(addTodo.hashCode(), addTooDo.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(addTodo.toString(), "StandaloneFlag{} " + "AbstractFlag{" +
        "name='" + addTodo.getName() + '\'' + '}');
  }
}