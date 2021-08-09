package problem1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ToDoListTest {
  Todo todo1;
  Todo todo2;
  Todo todo3;
  ToDoList toDoList1;
  ToDoList toDoList2;

  @Before
  public void setUp() throws Exception {
    todo1 = Todo.category("work").complete("true").priority("1").due("01/01/2020").
        build("test1");
    todo2 = Todo.category("study").priority("2").due("05/01/1919").build("test2");

    todo3 = Todo.build("test3");
    toDoList1 = new ToDoList();
    toDoList2 = new ToDoList();
    toDoList1.addTodo(todo1);
    toDoList1.addTodo(todo2);
    toDoList1.addTodo(todo3);
  }


  @Test
  public void display() {
    toDoList1.display();
    toDoList2.display();
  }

  @Test
  public void showIncomplete() {
    toDoList1.showIncomplete();
    toDoList2.showIncomplete();
  }

  @Test
  public void showCategory() {
    toDoList1.showCategory("work");
    toDoList1.showCategory("eat");
    toDoList2.showCategory("test");
  }

  @Test
  public void sortByDate() {
    toDoList1.sortByDate();
    toDoList2.sortByDate();
  }

  @Test
  public void sortByPriority() {
    toDoList1.sortByPriority();
    toDoList2.sortByPriority();
  }

  @Test
  public void notEmpty() {
    assertTrue(toDoList1.notEmpty());
    assertFalse(toDoList2.notEmpty());
  }

  @Test
  public void testEquals() {
    assertTrue(toDoList1.equals(toDoList1));
    assertFalse(toDoList1.equals(toDoList2));
    assertFalse(toDoList1.equals(null));
    assertFalse(toDoList1.equals(123));
  }

  @Test
  public void testHashCode() {
    assertTrue(toDoList1.hashCode()==toDoList1.hashCode() );
  }

  @Test
  public void testToString() {
    assertEquals("ToDoList{todoArrayList="
        + "[Todo{ID='1', text='test1', "
        + "complete='true', due='01/01/2020', "
        + "priority='1', category='work'}, "
        + "Todo{ID='2', text='test2', complete='false', "
        + "due='05/01/1919', priority='2', category='study'}, "
        + "Todo{ID='3', text='test3', "
        + "complete='false', due='?',"
        + " priority='3', category='?'}]}",toDoList1.toString());
  }
}