package problem1;
/*
This is a to do class, represent an event to be done.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Todo {
  private String complete;
  private final String due;
  private final String text;
  private final String priority;
  private final String category;
  private Date date;
  private Integer id;

  /**
   * change the status of an event.
   * @param complete if the event is complete or not.
   */
  public void setComplete(String complete){
    this.complete = complete;
  }

  /**
   * set the ID for the event in the list.
   * @param id ID of the event.
   */
  public void setID(Integer id) {
    this.id = id;
  }

  /**
   * get the date of an event in Date Format.
   * @return the Date Format of the date.
   */
  private Date getDate() {
    return date;
  }

  /**
   * this is an inner builder class for todo.
   */
  public static class ToDoBuilder {
    private String complete = "false";
    private String due = "?";
    private String priority = "3";
    private String category = "?";

    /**
     * set the completion of a todo, if given.
     * @param complete completion of a todo, if given.
     * @return a string 'true' or 'false'.
     */
    public ToDoBuilder complete(String complete) {
      this.complete = complete;
      return this;
    }

    /**
     * set the due date of a todo ,if given.
     * @param due due date of a todo.
     * @return a string from of a date.
     */
    public ToDoBuilder due(String due) {
      this.due = due;
      return this;
    }

    /**
     * return a number represent priority of the event. 1 is highest, 3 is lowest.
     * @param priority default is 3, if no priority is specified.
     * @return a string from of an int of priority.
     */
    public ToDoBuilder priority(String priority){
      this.priority = priority;
      return this;
    }

    /**
     * set the category of a event.
     * @param category category of a event.
     * @return a string describes the category.
     */
    public ToDoBuilder category(String category){
      this.category = category;
      return this;
    }

    /**
     * build the a todo object based on the provided information.
     * @param text the description of a todo.
     * @return a new todo object.
     * @throws ParseException when a given due date is not in dd/MM/yyyy format.
     */
    public Todo build(String text) throws ParseException {
      return new Todo(text,this);
    }
  }

  /**
   * set the due date of a todo ,if given by calling the builder function.
   * @param due due date of a todo.
   * @return a string from of a date.
   */
  public static ToDoBuilder due(String due){
    return new ToDoBuilder().due(due);
  }

  /**
   * set the completion of a todo, if given, by calling the builder function.
   * @param complete completion of a todo, if given.
   * @return a string 'true' or 'false'.
   */

  public static ToDoBuilder complete(String complete){
    return new ToDoBuilder().complete(complete);
  }

  /**
   * return a number represent priority of the event by calling the builder function.
   * 1 is highest, 3 is lowest.
   * @param priority default is 3, if no priority is specified.
   * @return a string from of an int of priority.
   */
  public static ToDoBuilder priority(String priority){
    return new ToDoBuilder().priority(priority);
  }

  /**
   * set the category of a event by calling the builder function.
   * @param category category of a event.
   * @return a string describes the category.
   */
  public static ToDoBuilder category(String category){
    return new ToDoBuilder().category(category);
  }

  /**
   * build the a todo object based on the provided information.
   * @param text the description of a todo.
   * @return a new todo object.
   * @throws ParseException when a given due date is not in dd/MM/yyyy format.
   */
  public static Todo build(String text) throws ParseException {
    return new ToDoBuilder().build(text);
  }

  /**
   * Constructor for todo.
   * @param text a string describe the todo event.
   * @param toDoBuilder a inner class to help to build a todo object.
   * @throws ParseException when a given due date is not in dd/MM/yyyy format.
   */
  private Todo(String text,ToDoBuilder toDoBuilder) throws ParseException {
    this.text = text;
    this.complete = toDoBuilder.complete;
    this.due = toDoBuilder.due;
    this.priority = toDoBuilder.priority;
    this.category = toDoBuilder.category;
    this.date = setDate();
    this.id = null;
  }

  /**
   * compare 2 todos with their priority.
   */
  public static Comparator<Todo> compareByPriority = new Comparator<Todo>() {
    @Override
    public int compare(Todo o1, Todo o2) {
      int priorityOne = Integer.parseInt(o1.getPriority());
      int priorityTwo = Integer.parseInt(o2.getPriority());
      return priorityOne - priorityTwo;
    }
  };
  /**
   * compare 2 todos with their date.
   */
  public static Comparator<Todo> compareByDate = new Comparator<Todo>() {
    @Override
    public int compare(Todo o1, Todo o2) {
      return o1.getDate().compareTo(o2.getDate());
    }
  };

  /**
   * a Date format date for todo.
   * @return a Date format of a date.
   * @throws ParseException when a given due date is not in dd/MM/yyyy format
   */
  private Date setDate() throws ParseException {
    if (this.due != "?"){
      return new SimpleDateFormat("MM/dd/yyyy").parse(this.due);
    } else{
      return new Date(8640000000000000L);
    }

  }

  /**
   * return the completion of todo.
   * @return the completion of todo.
   */
  public String getComplete() {
    return complete;
  }

  /**
   * return the due date of todo.
   * @return the due date of todo.
   */
  public String getDue() {
    return due;
  }

  /**
   * return the description of todo.
   * @return the description of todo.
   */

  public String getText() {
    return text;
  }

  /**
   * return the priority of a todo.
   * @return the priority of a todo.
   */
  public String getPriority() {
    return priority;
  }

  /**
   * return the category of a todo.
   * @return the category of a todo.
   */
  public String getCategory() {
    return category;
  }

  /**
   * return the ID of a todo.
   * @return the ID of a todo.
   */
  public Integer getId() {
    return id;
  }



  /**
   * return ture if a todo is the same as the compared object.
   * @param o the compared object.
   * @return ture if a todo is the same as the compared object.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Todo)) {
      return false;
    }
    Todo todo = (Todo) o;
    return Objects.equals(complete, todo.complete) &&
        Objects.equals(due, todo.due) &&
        Objects.equals(text, todo.text) &&
        Objects.equals(priority, todo.priority) &&
        Objects.equals(category, todo.category) &&
        Objects.equals(date, todo.date) &&
        Objects.equals(id, todo.id);
  }

  /**
   * return the hashcode of a todo.
   * @return the hashcode of a todo.
   */
  @Override
  public int hashCode() {
    return Objects.hash(complete, due, text, priority, category, date, id);
  }

  /**
   * return a string represents todo.
   * @return a string represents todo.
   */
  @Override
  public String toString() {
    return "Todo{" +
        "ID='" + id + '\''+
        ", text='" + text + '\'' +
        ", complete='" + complete + '\'' +
        ", due='" + due + '\'' +
        ", priority='" + priority + '\'' +
        ", category='" + category + '\'' +
        '}';
  }
}