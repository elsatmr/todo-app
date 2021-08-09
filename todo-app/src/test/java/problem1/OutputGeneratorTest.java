package problem1;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class OutputGeneratorTest {
  private OutputEditor outputEditor;
  private OutputGenerator outputGenerator;
  private CommandLineParser commandLineParser;
  private OutputReader outputReader;
  private FileProcessor fileProcessor;

  @Before
  public void setUp() throws Exception {
    String [] test = {"--output-dir",
        "C:\\Users\\smith\\OneDrive\\Desktop\\cs5004"
            + "\\Group_Yannan_Zheng_Elsa_Tamara_Zimeng_Xie_Chengyuan_Zhou\\HW8",
        "--letter", "--letter-template",
        "letter-template.txt", "--email",
        "--email-template", "email-template.txt", "--csv-file",
        "nonprofit-supporters.csv"};
    commandLineParser = new CommandLineParser(test);
    fileProcessor = new FileProcessor();
    outputReader = new OutputReader(commandLineParser,fileProcessor);
    outputEditor = new OutputEditor(outputReader);
    outputGenerator = new OutputGenerator(outputEditor,commandLineParser.getOutputDir());
  }

  @Test
  public void generateEmail() throws IOException {
    outputGenerator.generateEmail();

  }

  @Test
  public void generateLetter() throws IOException {
    outputGenerator.generateLetter();
  }
}