package problem1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
  This is a TodoList Class represents a collection of todos.
 */
public class ToDoList{
  private ArrayList<Todo> todoArrayList;

  /**
   * Constructor for the todoList.
   */
  public ToDoList() {
    this.todoArrayList = new ArrayList<Todo>();
  }

  /**
   * return Arraylist of todo.
   * @return Arraylist of todo.
   */
  public ArrayList<Todo> getTodoArrayList() {
    return todoArrayList;
  }

  /**
   * Add a new todo into the todoList.
   * @param todo new todo.
   */
  public void addTodo(Todo todo){
    this.todoArrayList.add(todo);
    todo.setID(this.todoArrayList.indexOf(todo)+1);
  }

  /**
   * check if the arraylist of todo is empty.
   * @return ture if its not empty, false if its empty.
   */
  public boolean notEmpty(){
    if (this.todoArrayList.isEmpty()){
      System.out.println("There is no Todo in the list.\n");
      return false;
    }
    return true;
  }

  /**
   * Display all todos.
   */
  public void display() {
    if (notEmpty()) {
      System.out.println("Displaying all todos: \n");
      for (Todo todos : this.todoArrayList) {
        System.out.println(todos.toString());
      }
    }
  }

  /**
   * Display all incomplete todos.
   */
  public void showIncomplete() {
    if (notEmpty()) {
      boolean exist = false;
      System.out.println("Displaying all incomplete todos: \n");
      for (Todo todos : this.todoArrayList) {
        if (todos.getComplete().equals("false")) {
          exist = true;
          System.out.println(todos.toString());
        }
      }
      if (!exist) {
        System.out.println("No incomplete todos left");
      }
    }
  }

  /**
   * Display all todos with given category.
   * @param category given category.
   */
  public void showCategory(String category){
    if (notEmpty()) {
      boolean exist = false;
      System.out.println("Displaying category: " + category + " : \n");
      for (Todo todos : this.todoArrayList) {
        if (todos.getCategory().equals(category)) {
          exist = true;
          System.out.println(todos.toString());
        }
      }
      if (!exist) {
        System.out.println("No category: " + category + " is found");
      }
    }
  }

  /**
   * Display all todos, sort by date.
   */
  public void sortByDate() {
    if (notEmpty()) {
      System.out.println("Displaying all todos -- sort by date : \n");
      ArrayList<Todo> sortByDate = this.todoArrayList;
      Collections.sort(sortByDate, Todo.compareByDate);
      for (Todo toDo : sortByDate) {
        System.out.println(toDo.toString());
      }
    }
  }

  /**
   * Display all todos, sort by priority.
   */
  public void sortByPriority() {
    if (notEmpty()) {
      System.out.println("Displaying all todos -- sort by priority : \n");
      ArrayList<Todo> sortByPriority = this.todoArrayList;
      Collections.sort(sortByPriority, Todo.compareByPriority);
      for (Todo toDo : sortByPriority) {
        System.out.println(toDo.toString());
      }
    }
  }

  /**
   * Iterates through the list, marks a Todo object complete if matches id
   * @param id param passed, Todo object id to be marked complete
   */
  public void markTodoComplete(String id) {
    if (notEmpty()) {
      for (Todo todos : this.todoArrayList) {
        if (todos.getId().toString().equals(id)) {
          todos.setComplete("true");
          return;
        }
      }
      System.out.println("No Thread id: " + id + " is found");
      }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ToDoList)) {
      return false;
    }
    ToDoList toDoList = (ToDoList) o;
    return Objects.equals(todoArrayList, toDoList.todoArrayList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(todoArrayList);
  }

  @Override
  public String toString() {
    return "ToDoList{" +
        "todoArrayList=" + todoArrayList +
        '}';
  }
}