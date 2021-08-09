package problem1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;

public class FileProcessorTest {
  FileProcessor test;

  @org.junit.Before
  public void setUp() throws Exception {
    test = new FileProcessor("/Users/elsatamara/Documents/Spring_2021/CS5004/GitHub/"
        + "Group_Yannan_Zheng_Elsa_Tamara_Zimeng_Xie_Chengyuan_Zhou/HW9-final/todo1.csv");
  }

  @Test
  public void testReadCSVFile() throws IOException {
    test.readCSVFile();
    for (int i = 0; i < test.getHeader().size(); i++){
      System.out.println(test.getHeader().get(i));
    }
    for (int i = 0; i < test.getCsvList().size(); i++){
      System.out.println(Arrays.toString(test.getCsvList().get(i)));
    }
  }

  @Test
  public void testCreateInitialTodoList() throws Exception {
    test.readCSVFile();
    ToDoList one = test.createInitialTodoList();
    for (Todo todos : one.getTodoArrayList()) {
      System.out.println(todos.toString());
    }
  }

}