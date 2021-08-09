package problem1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class OutputReaderTest {

  private FileProcessor fp;
  private CommandLineParser clp;
  private OutputReader or;
  private String[] cmdArgs = {"--output-dir",
      "P:\\NEU\\Academics\\02_Spring 2021\\02.01_CS 5004\\cs5004\\Group_Yannan_Zheng_Elsa_Tamara_Zimeng_Xie_Chengyuan_Zhou\\HW8\\src", "--letter", "--letter-template",
      "letter-template.txt", "--email", "--email-template", "email-template.txt", "--csv-file", "nonprofit-supporters.csv"};

  @Before
  public void setUp() throws Exception {
    fp = new FileProcessor();
    clp = new CommandLineParser(cmdArgs);
    or = new OutputReader(clp, fp);
  }

  @Test
  public void getTemplateEmail() {
    assertEquals(or.getTemplateEmail(), this.fp.getEmailTemplate());
  }

  @Test
  public void getTemplateLetter() {
    assertEquals(or.getTemplateLetter(), this.fp.getLetterTemplate());
  }

  @Test
  public void getHeaderFields() {
    assertEquals(or.getHeaderFields(), this.fp.getHeader());
  }

  @Test
  public void getActualData() {
    assertEquals(or.getActualData(), this.fp.getCsvList());
  }

  @Test
  public void loadEverything() {
    assertTrue(this.clp.getIsEmail() && this.clp.getIsLetter());
  }
}