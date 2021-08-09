package problem1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import problem1.Todo.ToDoBuilder;

/**
 * A class representing a FileProcessor, processes the initial CSV file given by user.
 */

public class FileProcessor {
  private String csvPath;
  private ArrayList<String> header;
  private ArrayList<String[]> csvList;

  /**
   * Constructor for FileProcessor class
   * @param csvPath A string, the supplied CSV path
   */
  public FileProcessor(String csvPath) {
    this.csvPath = csvPath;
  }

  /**
   * Returns the CSV path supplied by the user
   * @return A string, the CSV path supplied by the user
   */
  public String getCSVPath() {
    return this.csvPath;
  }

  public ArrayList<String> getHeader() {
    return this.header;
  }

  public ArrayList<String[]> getCsvList() {
    return this.csvList;
  }

  /**
   * Reads the CSV file, store it in an ArrayList of Strings[]
   * @throws IOException if there is an error in reading the file
   */
  public void readCSVFile() throws IOException {
    Reader reader = new FileReader(this.getCSVPath());
    BufferedReader file = new BufferedReader(reader);
    String csvLine;
    ArrayList<String[]> listString = new ArrayList<>();
    csvLine = file.readLine();
    String[] headerArray = csvLine.split(",");
    this.header = new ArrayList<>(Arrays.asList(headerArray));
    while ((csvLine = file.readLine()) != null) {
      String[] temp = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
      listString.add(temp);
    }
    this.csvList = listString;
  }

  /**
   * Creates a Todo list, by first, accessing information on the ArrayList<String[]>,
   * create one todo object per column of ArrayList<String[]> (it's upside down,
   * one todo object is not every row but every column), and put it all together
   * in a ToDoList object
   * @return a ToDoList object with all the todo objects built in
   * @throws ParseException if String is unparseable
   */
  public ToDoList createInitialTodoList() throws ParseException {
    ToDoList one = new ToDoList();
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.getCsvList().size(); i++) {
      for (int j = 0; j < this.getCsvList().get(i).length; j++) {
        String stripped = this.getCsvList().get(i)[j].replaceAll("^\"|\"$", "");
        temp.add(stripped);
      }
      Todo toAdd = this.createOneLineTodoObjects(temp);
      temp.clear();
      one.addTodo(toAdd);
    }
    return one;
  }

  /**
   * A helper method. Creates a Todo object based on the information
   * on the List<String> param passed.
   * @param b contains the information extracted from ArrayList<String[]> for one Todo object.
   * @return a Todo object built based on the spec given by user
   * @throws ParseException if String is unparseable
   */
  private Todo createOneLineTodoObjects(List<String> b) throws ParseException {
    ToDoBuilder todo = Todo.complete(b.get(2));
    if (b.get(3) != null && !b.get(3).equals("?")) {
      todo.due(b.get(3));
    }
    if (b.get(4) != null && !b.get(4).equals("?")) {
      todo.priority(b.get(4));
    }
    if (b.get(5) != null && !b.get(5).equals("?")) {
      todo.category(b.get(5));
    }
    Todo todo1 = todo.build(b.get(1));
    return todo1;
  }

  /**
   * Writes into the CSV file
   */
  public void updateCsvFile(ToDoList initialList) throws IOException, ParseException {
    FileWriter fileWriter = new FileWriter(this.csvPath);
    String header = this.header.stream().collect(Collectors.joining(","));
    fileWriter.write(header);
    fileWriter.write(System.getProperty("line.separator"));
    for (int i = 0; i < initialList.getTodoArrayList().size() ; i++){
      ArrayList<String> temp = new ArrayList<>();
      if (initialList.getTodoArrayList().get(i).getId() == null){
        temp.add("?");
      } else{
        temp.add(initialList.getTodoArrayList().get(i).getId().toString());
      }
      if (initialList.getTodoArrayList().get(i).getText() == null){
        temp.add("?");
      } else {
        temp.add(initialList.getTodoArrayList().get(i).getText());
      }
      if (initialList.getTodoArrayList().get(i).getComplete() == null){
        temp.add("?");
      } else {
        temp.add(initialList.getTodoArrayList().get(i).getComplete());
      }
      if (initialList.getTodoArrayList().get(i).getDue() == null){
        temp.add("?");
      } else {
        temp.add(initialList.getTodoArrayList().get(i).getDue());
      }
      if (initialList.getTodoArrayList().get(i).getPriority() == null){
        temp.add("?");
      } else {
        temp.add(initialList.getTodoArrayList().get(i).getPriority());
      }
      if (initialList.getTodoArrayList().get(i).getCategory() == null){
        temp.add("?");
      } else {
        temp.add(initialList.getTodoArrayList().get(i).getCategory());
      }
      String todoTemp = temp.stream().collect(Collectors.joining(","));
      fileWriter.write(todoTemp);
      fileWriter.write(System.getProperty("line.separator"));
    }
    fileWriter.close();
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
