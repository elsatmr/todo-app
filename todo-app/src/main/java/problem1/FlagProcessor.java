package problem1;

import java.text.ParseException;
import java.util.ArrayList;
import problem1.Todo.ToDoBuilder;

/**
 * A class representing a FlagProcessor.
 */

public class FlagProcessor {
  private static final String DISPLAY = "--display";
  private static final String SHOW_INCOMPLETE = "--show-incomplete";
  private static final String SHOW_CATEGORY = "--show-category";
  private static final String SORT_BY_DATE = "--sort-by-date";
  private static final String SORT_BY_PRIORITY = "--sort-by-priority";
  private static final String CSV_FILE = "--csv-file";
  private static final String ADD_TODO = "--add-todo";
  private static final String COMPLETED = "--completed";
  private static final String DUE = "--due";
  private static final String PRIORITY = "--priority";
  private static final String CATEGORY = "--category";
  private static final String COMPLETE_TODO = "--complete-todo";
  private static final String TODO_TEXT = "--todo-text";
  private static final String TRUE = "true";

  private ArrayList<? extends AbstractFlag> flags;
  private ArrayList<Integer> idxToMarkCompleteList;
  private boolean needsToDisplay;
  private boolean needsToShowIncomplete;
  private String categoryToDisplay;
  private boolean needsToSortDate;
  private boolean needsToSortPriority;

  /**
   * Constructor for FlagProcessor class
   * @param flags an array list of flags object, can consist of ParameterizedFlag
   *              and Standalone flags
   */
  public FlagProcessor(ArrayList<? extends AbstractFlag> flags) {
    this.flags = flags;
    this.idxToMarkCompleteList = new ArrayList<>();
    this.needsToDisplay = false;
    this.categoryToDisplay = null;
    this.needsToSortDate = false;
    this.needsToSortPriority = false;
  }

  /**
   * Returns an array list of AbstractFlag objects
   * @return an array list of AbstractFlag objects
   */
  private ArrayList<? extends AbstractFlag> getFlags() {
    return flags;
  }

  /**
   * Returns an array list of Todo ID (integer) that needs to be completed
   * @return an array list of Todo ID (integer) that needs to be completed
   */
  public ArrayList<Integer> getIdxToMarkCompleteList() {
    return this.idxToMarkCompleteList;
  }

  /**
   * Returns true if --show-incomplete is being asked
   * @return true if --show-incomplete is being asked
   */
  public boolean isNeedsToShowIncomplete() {
    return this.needsToShowIncomplete;
  }

  /**
   * Sets the boolean variable of whether --show-incomplete is being asked
   * @param needsToShowIncomplete true if asked, false otherwise
   */
  public void setNeedsToShowIncomplete(boolean needsToShowIncomplete) {
    this.needsToShowIncomplete = needsToShowIncomplete;
  }

  /**
   * Returns true if --display is asked
   * @return true if asked, false otherwise
   */
  public boolean isNeedsToDisplay() {
    return this.needsToDisplay;
  }

  /**
   * Sets the boolean variable of whether -ydisplau is being asked
   * @param needsToDisplay true if asked, false otherwise
   */
  private void setNeedsToDisplay(boolean needsToDisplay) {
    this.needsToDisplay = needsToDisplay;
  }

  /**
   * Returns a String, the category the user asked to display from --show-category
   * @return the argument passed from --show-category
   */
  public String getCategoryToDisplay() {
    return this.categoryToDisplay;
  }

  /**
   * Sets the String variable of the argument of --category being asked
   * @param categoryToDisplay the string to set
   */
  private void setCategoryToDisplay(String categoryToDisplay) {
    this.categoryToDisplay = categoryToDisplay;
  }

  /**
   * Returns true if user asked for --sort-by-date
   * @return true if user asked for --sort-by-date, false otherwise
   */
  public boolean isNeedsToSortDate() {
    return this.needsToSortDate;
  }

  /**
   * Sets the boolean value of needsToSortDate variable
   * @param needsToSortDate true if user asked for --sort-by-date, false otherwise
   */
  private void setNeedsToSortDate(boolean needsToSortDate) {
    this.needsToSortDate = needsToSortDate;
  }

  /**
   * Returns true if user asked for --sort-by-priority
   * @return true if user asked for --sort-by-priority, false otherwise
   */
  public boolean isNeedsToSortPriority() {
    return this.needsToSortPriority;
  }

  /**
   * Sets the boolean value of needsToSortPriority variable
   * @param needsToSortPriority true if user asked for --sort-by-priority, false otherwise
   */
  private void setNeedsToSortPriority(boolean needsToSortPriority) {
    this.needsToSortPriority = needsToSortPriority;
  }

  /**
   * Generates the CSV file path to be read from the array of validated flags
   * @return a String, the CSV file path
   */
  public String generateCSVPath() {
    ParameterizedFlag f;
    for (int i = 0; i < this.getFlags().size(); i++) {
      if (this.getFlags().get(i).getName().equals(CSV_FILE)) {
        f = (ParameterizedFlag)this.getFlags().get(i);
        return f.getParameter();
      }
    }
    return null;
  }

  /**
   * Returns true if user wants to add a new todo (gives --add-todo) command
   * @return true if user wants to add a new todo (gives --add-todo) command, false otherwise.
   */
  public boolean needToAddTodo() {
    for (int i = 0; i < this.getFlags().size(); i++) {
      if (this.getFlags().get(i).getName().equals(ADD_TODO)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Create a todo object based on the validated user arguments that are stored in
   * AbstractFlag objects inside of the flag ArrayList
   * @return a todo object - build by using the builder design pattern
   * @throws ParseException if String is unparseable (doesnt meet the required format)
   */
  public Todo createTodo() throws ParseException {
    ToDoBuilder a = new ToDoBuilder();
    for (int i = 0; i < this.getFlags().size(); i++) {
      if (this.getFlags().get(i).getName().equals(COMPLETED)) {
        a.complete(TRUE);
      }
      if (this.getFlags().get(i).getName().equals(DUE)) {
        String dueDate = ((ParameterizedFlag) this.getFlags().get(i)).getParameter();
        a.due(dueDate);
      }
      if (this.getFlags().get(i).getName().equals(PRIORITY)) {
        String priority = ((ParameterizedFlag) this.getFlags().get(i)).getParameter();
        a.priority(priority);
      }
      if (this.getFlags().get(i).getName().equals(CATEGORY)) {
        String category = ((ParameterizedFlag) this.getFlags().get(i)).getParameter();
        String strippedCategory = category.replaceAll("[{}]", "");
        a.category(strippedCategory);
      }
    }
    Todo todo = this.initiateTodo(a);
    return todo;
  }

  /**
   * Helper method, after building a toDoBuilder object based on the flags, finally
   * builds it with the todo-text (required for every todo object)
   * @param toDoBuilder todoBuilder object built from createTodo()
   * @return A built todo
   * @throws ParseException if String is unparseable (doesnt meet the required format)
   */
  private Todo initiateTodo(ToDoBuilder toDoBuilder) throws ParseException {
    String strippedTodoText = null;
    for (int i = 0; i < this.getFlags().size(); i++) {
      if (this.getFlags().get(i).getName().equals(TODO_TEXT)) {
        String beforeStripped = ((ParameterizedFlag) this.getFlags().get(i)).getParameter();
        strippedTodoText = beforeStripped.replaceAll("[{}]", "");
      }
    }
    Todo a = toDoBuilder.build(strippedTodoText);
    return a;
  }

  /**
   * Checks if user gives --complete-todo command, if they do, store the idx
   * of where in the array does the user gives it in.
   */
  public void needsToMarkComplete() {
    for (int i = 0; i < this.getFlags().size(); i++) {
      if (this.getFlags().get(i).getName().equals(COMPLETE_TODO)) {
        this.getIdxToMarkCompleteList().add(i);
      }
    }
  }

  /**
   * Returns the argument of --complete-todo command,
   * which is which todo id to mark complete.
   * @param idx idx in the array
   * @return A string, which todo id to complete
   */
  public String idToMarkComplete(int idx) {
    return ((ParameterizedFlag) this.getFlags().get(idx)).getParameter();
  }

  /**
   * Toggling the variable related to --display, based on the validated user input
   * (stored as AbstractFlag object in the flag ArrayList)
   * e.g. if user gives --show-incomplete, then needsToShowIncomplete variable will be marked
   * as true. Prepares for the changes on the todolist later in main
   */
  public void toggleOtherDisplayCommands() {
    for (int i = 0; i < this.getFlags().size(); i++) {
      if (this.getFlags().get(i).getName().equals(SHOW_INCOMPLETE)) {
        this.setNeedsToShowIncomplete(true);
      }
      if (this.getFlags().get(i).getName().equals(SHOW_CATEGORY)) {
        String a = ((ParameterizedFlag) this.getFlags().get(i)).getParameter();
        this.setCategoryToDisplay(a);
      }
      if (this.getFlags().get(i).getName().equals(SORT_BY_DATE)) {
        this.setNeedsToSortDate(true);
      }
      if (this.getFlags().get(i).getName().equals(SORT_BY_PRIORITY)) {
        this.setNeedsToSortPriority(true);
      }
    }
  }

  /**
   * Special toggle if user only requests --display which is displaying
   * the entire todo list.
   */
  public void toggleOnlyDisplayAllCommand() {
    if (!this.isNeedsToSortDate() && !isNeedsToSortPriority() && !isNeedsToShowIncomplete()
        && getCategoryToDisplay() == null) {
      for (int i = 0; i < this.getFlags().size(); i++) {
        if (this.getFlags().get(i).getName().equals(DISPLAY)) {
          this.setNeedsToDisplay(true);
        }
      }
      return;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
