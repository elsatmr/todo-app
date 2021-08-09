package problem1;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;

public class FlagFactoryTest {

  CommandLineParser clp1, clp2, clp3, clp4, clp5, clp6, clp7, clp8, clp9, clp10, clp11, clp12,
      clp13, clp14, clp15, clp16;
  FlagFactory ff;
  private final String[] commandLine1 = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--add-todo", "--todo-text", "{Service car}", "--due", "06/01/2021", "--priority", "2",
      "--category", "Maintenance", "--complete-todo", "10", "--complete-todo", "11", "--display",
      "--show-category", "Shopping", "--sort-by-date"};
  private final String[] badPath = new String[] {"--csv-file", "path/to/file/tada.csv",
      "--complete-todo", "23"};
  private final String[] badPath2 = new String[] {"--csv-file", "C:\\path\\to\\justkidding.lolz",
      "--complete-todo", "23"};
  private final String[] nonsenseCommand = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--orange"};
  private final String[] badPriority = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--add-todo", "--todo-text", "{Service car}", "--due", "06/01/2021", "--priority", "100"};
  private final String[] badId = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--complete-todo", "-223"};
  private final String[] duplicateCsv = new String[] {"--csv-file", "C:\\a\\b.csv", "--csv-file",
      "D:\\c\\d.csv", "--complete-todo", "1"};
  private final String[] noOperation = new String[] {"--csv-file", "C:\\a\\b.csv"};
  private final String[] multipleTodo = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--add-todo", "--todo-text", "{Service car}", "--add-todo", "--todo-text", "{Buy doughnuts}"};
  private final String[] multipleTodoText = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--add-todo", "--todo-text", "{Service car}", "--todo-text", "{Buy doughnuts}"};
  private final String[] multiplePriority = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--add-todo", "--todo-text", "{Service car}", "--priority", "1", "--priority", "2"};
  private final String[] multipleDisplay = new String[] {"--csv-file", "C:\\path\\to\\file.csv",
      "--display", "--show-category", "--display", "--sort-by-date"};
  private final String[] sortByDateAndPriority = new String[] {"--csv-file",
      "C:\\path\\to\\file.csv", "--display", "--sort-by-date", "--sort-by-priority"};
  private final String[] multipleShowIncomplete = new String[] {"--csv-file",
      "C:\\path\\to\\file.csv", "--display", "--show-incomplete", "--show-incomplete"};
  private final String[] completeWithoutAddTodo = new String[] {"--csv-file",
      "C:\\path\\to\\file.csv", "--complete", "--display"};
  private final String[] sortWithoutDisplay = new String[] {"--csv-file",
      "C:\\path\\to\\file.csv", "--complete-todo", "11", "--sort-by-date"};



  @Before
  public void setUp() throws Exception {
    clp1 = new CommandLineParser(commandLine1);
    ff = new FlagFactory();
  }

  @Test
  public void validFlags() throws Exception {
    ff.produceFlags(clp1.getFlags());
  }

  // This code is written on a Windows machine
  @Test(expected = IllegalPathException.class)
  public void badPath() throws Exception {
    clp2 = new CommandLineParser(badPath);
    ff.produceFlags(clp2.getFlags());
  }

  // This code is written on a Windows machine
  @Test(expected = IllegalPathException.class)
  public void badPath2() throws Exception {
    clp3 = new CommandLineParser(badPath2);
    ff.produceFlags(clp3.getFlags());
  }

  @Test(expected = FlagNotFoundException.class)
  public void nonsenseCommand() throws Exception {
    clp4 = new CommandLineParser(nonsenseCommand);
    ff.produceFlags(clp4.getFlags());
  }

  @Test(expected = IllegalPriorityException.class)
  public void badPriority() throws Exception {
    clp5 = new CommandLineParser(badPriority);
    ff.produceFlags(clp5.getFlags());
  }

  @Test(expected = IllegalIdException.class)
  public void badId() throws Exception {
    clp6 = new CommandLineParser(badId);
    ff.produceFlags(clp6.getFlags());
  }

  @Test(expected = Exception.class)
  public void duplicateCsv() throws Exception {
    clp7 = new CommandLineParser(duplicateCsv);
    ff.produceFlags(clp7.getFlags());
  }

  @Test(expected = Exception.class)
  public void noOperation() throws Exception {
    clp8 = new CommandLineParser(noOperation);
    ff.produceFlags(clp8.getFlags());
  }

  @Test(expected = Exception.class)
  public void multipleTodo() throws Exception {
    clp9 = new CommandLineParser(multipleTodo);
    ff.produceFlags(clp9.getFlags());
  }

  @Test(expected = Exception.class)
  public void multipleTodoText() throws Exception {
    clp10 = new CommandLineParser(multipleTodoText);
    ff.produceFlags(clp10.getFlags());
  }

  @Test(expected = Exception.class)
  public void multiplePriority() throws Exception {
    clp11 = new CommandLineParser(multiplePriority);
    ff.produceFlags(clp11.getFlags());
  }

  @Test(expected = Exception.class)
  public void multipleDisplay() throws Exception {
    clp12 = new CommandLineParser(multipleDisplay);
    ff.produceFlags(clp12.getFlags());
  }

  @Test(expected = Exception.class)
  public void sortByDateAndPriority() throws Exception {
    clp13 = new CommandLineParser(sortByDateAndPriority);
    ff.produceFlags(clp13.getFlags());
  }

  @Test(expected = Exception.class)
  public void multipleShowIncomplete() throws Exception {
    clp14 = new CommandLineParser(multipleShowIncomplete);
    ff.produceFlags(clp7.getFlags());
  }

  @Test(expected = Exception.class)
  public void completeWithoutAddTodo() throws Exception {
    clp15 = new CommandLineParser(completeWithoutAddTodo);
    ff.produceFlags(clp15.getFlags());
  }

  @Test(expected = Exception.class)
  public void sortWithoutDisplay() throws Exception {
    clp16 = new CommandLineParser(sortWithoutDisplay);
    ff.produceFlags(clp16.getFlags());
  }

  @Test
  public void getFlags() throws NoSuchFieldException, IllegalAccessException {
    // Reflection. Do not abuse
    Class<? extends FlagFactory> cls = ff.getClass();
    Field field = cls.getDeclaredField("flags");
    field.setAccessible(true);
    assertEquals(ff.getFlags(), field.get(ff));
  }
}