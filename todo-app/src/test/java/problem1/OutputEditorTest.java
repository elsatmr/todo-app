package problem1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class OutputEditorTest {

  private FileProcessor fp;
  private CommandLineParser clp;
  private OutputReader or;
  private String[] cmdArgs = {"--output-dir",
      "P:\\NEU\\Academics\\02_Spring 2021\\02.01_CS 5004\\cs5004\\Group_Yannan_Zheng_Elsa_Tamara_Zimeng_Xie_Chengyuan_Zhou\\HW8\\src", "--letter", "--letter-template",
     "letter-template.txt", "--email", "--email-template", "email-template.txt", "--csv-file", "nonprofit-supporters.csv"};
  private OutputReader reader;
  private OutputEditor editor;
  private HashMap<DoubleIndices, String[]> letterPlaceholder;
  private HashMap<DoubleIndices, String[]> emailPlaceholder;
  private ArrayList<ArrayList<String[]>> letterOutputList;
  private ArrayList<ArrayList<String[]>> emailOutputList;

  @Before
  public void setUp() throws Exception {
    fp = new FileProcessor();
    clp = new CommandLineParser(cmdArgs);
    reader = new OutputReader(clp, fp);
    editor = new OutputEditor(reader);
    letterPlaceholder = editor.locatePlaceholders(this.reader.getTemplateLetter());
    emailPlaceholder = editor.locatePlaceholders(this.reader.getTemplateEmail());
  }

  @Test
  public void populateDatabase() {
    ArrayList<ArrayList<String[]>> temp1, temp2;
    temp1 = editor.populateDatabase(letterPlaceholder, this.reader.getTemplateLetter());
    temp2 = editor.populateDatabase(emailPlaceholder, this.reader.getTemplateEmail());
    assertEquals(temp1.size(), 500);
    assertEquals(temp2.size(), 500);
  }

  @Test
  public void getLetterOutputList() {
    assertEquals(editor.getLetterOutputList(),
        editor.populateDatabase(letterPlaceholder, reader.getTemplateLetter()));
  }

  @Test
  public void getEmailOutputList() {
    assertEquals(editor.getEmailOutputList(),
        editor.populateDatabase(emailPlaceholder, reader.getTemplateEmail()));
  }

  @Test
  public void getReader() {
    assertEquals(editor.getReader(), reader);
  }
}