package problem1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class FileProcessorTest {
  private FileProcessor test;

  @Before
  public void setUp() throws Exception {
    test = new FileProcessor();
  }

  @Test
  public void readCsvFile() throws IOException {
    test.readCsvFile("nonprofit-supporters.csv");
    for (int i = 0; i < test.getHeader().size(); i++){
      System.out.println(test.getHeader().get(i));
    }
    for (int i = 0; i < test.getCsvList().size(); i++){
      System.out.println(Arrays.toString(test.getCsvList().get(i)));
    }
  }

  @Test
  public void setEmailTemplate() throws IOException {
    test.setEmailTemplate("email-template.txt");
    for (int i = 0; i < test.getEmailTemplate().size(); i++){
      System.out.println(Arrays.toString(test.getEmailTemplate().get(i)));
    }
  }
  @Test
  public void setLetterTemplate() throws IOException{
    test.setLetterTemplate("letter-template.txt");
    for (int i = 0; i < test.getLetterTemplate().size(); i++){
      System.out.println(Arrays.toString(test.getLetterTemplate().get(i)));
    }
  }

}