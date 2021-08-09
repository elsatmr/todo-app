package problem1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TodoTest {
  Todo todo1;
  Todo todo2;
  Todo todo3;

  @Before
  public void setUp() throws Exception {
    todo1 = Todo.category("work").complete("true").priority("1").due("01/01/2020").
        build("test1");
    todo2 = Todo.category("study").priority("2").due("05/01/1919").build("test2");

    todo3 = Todo.build("test3");
  }

  @Test
  public void setComplete() {
    todo1.setComplete("false");
    assertEquals("false",todo1.getComplete());
  }

  @Test
  public void setID() {
    todo1.setID(1);
    assertEquals(1,todo1.getId().longValue());
  }

  @Test
  public void getComplete() {
    assertEquals("true",todo1.getComplete());
    assertEquals("false",todo3.getComplete());
  }

  @Test
  public void getDue() {
    assertEquals("01/01/2020",todo1.getDue());
    assertEquals("?",todo3.getDue());
  }

  @Test
  public void getText() {
    assertEquals("test1",todo1.getText());
  }

  @Test
  public void getPriority() {
    assertEquals("1",todo1.getPriority());
    assertEquals("3",todo3.getPriority());
  }

  @Test
  public void getCategory() {
    assertEquals("work",todo1.getCategory());
    assertEquals("?",todo3.getCategory());
  }

  @Test
  public void getID() {
    assertNull(todo1.getId());
  }

  @Test
  public void testEquals() {
    assertTrue(todo1.equals(todo1));
    assertFalse(todo1.equals(todo3));
    assertFalse(todo1.equals(todo2));
    assertFalse(todo1.equals(null));
    assertFalse(todo1.equals(123));
  }

  @Test
  public void testHashCode() {
    assertTrue(todo1.hashCode()==todo1.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Todo{ID='null', text='test1', "
        + "complete='true', due='01/01/2020', priority='1', "
        + "category='work'}",todo1.toString());
  }
}