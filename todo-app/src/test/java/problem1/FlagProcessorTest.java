package problem1;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable.UnaryOp.Abs;
import org.junit.Before;
import org.junit.Test;

public class FlagProcessorTest {
  private FlagProcessor fp;
  private StandaloneFlag addTodo;
  private ParameterizedFlag todoText;
  private ParameterizedFlag csvFile;
  private ParameterizedFlag csvFileTwo;
  private ParameterizedFlag due;
  private ParameterizedFlag category;
  private ParameterizedFlag priority;
  ArrayList<? super AbstractFlag> testing;

  @Before
  public void setUp() throws Exception {
    addTodo = new StandaloneFlag("--add-todo");
    todoText = new ParameterizedFlag("--todo-text", "Re-pot Monsterra");
    csvFile = new ParameterizedFlag("--csv-file", "/Users/elsatamara/Documents/"
        + "Spring_2021/CS5004/GitHub/"
        + "Group_Yannan_Zheng_Elsa_Tamara_Zimeng_Xie_Chengyuan_Zhou/HW9-final/todos.csv");
    csvFileTwo = new ParameterizedFlag("--csv-file", "/Users/elsatamara/Documents/"
        + "Spring_2021/CS5004/GitHub/"
        + "Group_Yannan_Zheng_Elsa_Tamara_Zimeng_Xie_Chengyuan_Zhou/HW9-final/blabla.csv");
    due = new ParameterizedFlag("--due", "03/21/2018");
    category = new ParameterizedFlag("--category", "home");
    priority = new ParameterizedFlag("--priority", "2");
    testing = new ArrayList<>();
    testing.add(addTodo);
    testing.add(todoText);
    testing.add(csvFile);
    testing.add(due);
    testing.add(category);
    testing.add(priority);
    fp = new FlagProcessor((ArrayList<? extends AbstractFlag>)testing);

  }

  @Test
  public void testGenerateCSVPath() {
    assertTrue(fp.generateCSVPath().equals(csvFile.getParameter()));
    assertFalse(fp.generateCSVPath().equals(csvFileTwo.getParameter()));
  }

  @Test
  public void testNeedToAddTodo() {
    ArrayList<? super AbstractFlag> testingTwo = new ArrayList<>();
    testingTwo.add(csvFileTwo);
    FlagProcessor fp2 = new FlagProcessor((ArrayList<? extends AbstractFlag>) testingTwo);
    assertTrue(fp.needToAddTodo());
    assertFalse(fp2.needToAddTodo());
  }

  @Test
  public void testCreateTodo() throws ParseException {
    Todo one = fp.createTodo();
    assertTrue(one.getCategory().equals(category.getParameter()));
    assertTrue(one.getDue().equals(due.getParameter()));
    assertTrue(one.getPriority().equals(priority.getParameter()));
    assertEquals(one.getComplete(), "false");
    StandaloneFlag complete = new StandaloneFlag("--completed");
    ArrayList<? super AbstractFlag> testingTwo = new ArrayList<>();
    testing.add(complete);
    FlagProcessor fp2 = new FlagProcessor((ArrayList<? extends AbstractFlag>) testing);
    Todo two = fp2.createTodo();
    assertEquals("true", two.getComplete());
    Todo compare = Todo.category("home").priority("2").due("03/21/2018").build("Re-pot Monsterra");
    assertTrue(one.equals(compare));
    assertFalse(two.equals(compare));
    Todo compareTwo = Todo.category("home").priority("2").due("03/21/2018").complete("true")
        .build("Re-pot Monsterra");
    assertFalse(one.equals(compareTwo));
    assertTrue(two.equals(compareTwo));
  }

  @Test
  public void testGetIdxToMarkComplete() {
    ArrayList<Integer> empty = new ArrayList<>();
    assertTrue(fp.getIdxToMarkCompleteList().equals(empty));
    ParameterizedFlag markCompleteOne = new ParameterizedFlag("--complete-todo", "1");
    ParameterizedFlag markCompleteTwo = new ParameterizedFlag("--complete-todo", "2");
    testing.add(markCompleteOne);
    testing.add(markCompleteTwo);
    fp = new FlagProcessor((ArrayList<? extends AbstractFlag>) testing);
    fp.needsToMarkComplete();
    ArrayList<Integer> test = new ArrayList<>();
    test.add(6);
    test.add(7);
    assertTrue(fp.getIdxToMarkCompleteList().equals(test));
    assertTrue(fp.idToMarkComplete(test.get(0)).equals(markCompleteOne.getParameter()));
    assertTrue(fp.idToMarkComplete(test.get(1)).equals(markCompleteTwo.getParameter()));
  }

  @Test
  public void toggleDisplayCommandsTest() {
    assertEquals(null, fp.getCategoryToDisplay());
    assertFalse(fp.isNeedsToDisplay());
    assertFalse(fp.isNeedsToShowIncomplete());
    assertFalse(fp.isNeedsToSortDate());
    assertFalse(fp.isNeedsToSortPriority());
    StandaloneFlag display = new StandaloneFlag("--display");
    StandaloneFlag showIncomplete = new StandaloneFlag("--show-incomplete");
    StandaloneFlag sortDate = new StandaloneFlag("--sort-by-date");
    StandaloneFlag sortPriority = new StandaloneFlag("--sort-by-priority");
    ParameterizedFlag category = new ParameterizedFlag("--show-category", "home");
    testing.add(display);
    fp.toggleOnlyDisplayAllCommand();
    assertTrue(fp.isNeedsToDisplay());
    testing.add(showIncomplete);
    testing.add(sortDate);
    testing.add(category);
    fp = new FlagProcessor((ArrayList<? extends AbstractFlag>) testing);
    fp.toggleOtherDisplayCommands();
    assertEquals("home", fp.getCategoryToDisplay());
    assertFalse(fp.isNeedsToDisplay());
    assertTrue(fp.isNeedsToShowIncomplete());
    assertTrue(fp.isNeedsToSortDate());
    fp.toggleOnlyDisplayAllCommand();
    assertFalse(fp.isNeedsToDisplay());
  }
}