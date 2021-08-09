package problem1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParameterizedFlagTest {

  private ParameterizedFlag categoryOne = new ParameterizedFlag("--category", "1");
  private ParameterizedFlag categoryOneCopy = new ParameterizedFlag("--category", "1");
  private ParameterizedFlag categoryFive = new ParameterizedFlag("--category", "5");
  private ParameterizedFlag completeTodo = new ParameterizedFlag("--complete-todo", "5");

  @Test
  public void testEquals() {
    assertEquals(categoryOne, categoryOne);
    assertEquals(categoryOne, categoryOneCopy);
    assertEquals(categoryOne, categoryOneCopy);
    assertNotEquals(categoryOne, 8);
    assertNotNull(categoryOne);
    assertNotEquals(categoryOne, categoryFive);
    assertNotEquals(categoryFive, completeTodo);
  }

  @Test
  public void testHashCode() {
    assertEquals(categoryOne.hashCode(), categoryOneCopy.hashCode());
    assertNotEquals(categoryOne.hashCode(), categoryFive.hashCode());
  }

  @Test
  public void getParameter() {
    assertEquals(categoryOne.getParameter(), "1");
    assertEquals(categoryFive.getParameter(), "5");
  }

  @Test
  public void testToString() {
    assertEquals(categoryOne.toString(), "ParameterizedFlags{" +
        "parameter=" + categoryOne.getParameter() +
        "} " + "AbstractFlag{" +
        "name='" + categoryOne.getName() + '\'' + '}');
  }
}