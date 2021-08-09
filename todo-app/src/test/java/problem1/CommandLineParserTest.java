package problem1;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandLineParserTest {

  @Test(expected = NeedsRequiredCommandException.class)
  public void noFileToRead() throws Exception {
    String[] argOne = new String[]{"--csv-file", "--output-dir", "/blabla"};
    CommandLineParser sample = new CommandLineParser(argOne);
  }

  @Test
  public void hasOutputDirAndFile() throws Exception {
    String[] argTwo = new String[] {"--output-dir", "/boba/donut", "--csv-file", "boba.csv"};
    CommandLineParser sample = new CommandLineParser(argTwo);
  }

  @Test(expected = NeedsRequiredCommandException.class)
  public void onlyHasOutputDirCommand() throws Exception {
    String[] argThree = new String[] {"--output-dir"};
    CommandLineParser sample = new CommandLineParser(argThree);
  }

  @Test(expected = NeedsRequiredCommandException.class)
  public void onlyHasCSVFileCommand() throws Exception {
    String[] argFour = new String[] {"--csv-file"};
    CommandLineParser sample = new CommandLineParser(argFour);
  }

  @Test(expected = NeedsRequiredCommandException.class)
  public void noFilePathNorDirectory() throws Exception {
    String[] argFive = new String[] {"--csv-file", "--output-dir"};
    CommandLineParser sample = new CommandLineParser(argFive);
  }

  @Test(expected = NeedsRequiredCommandException.class)
  public void notCSVFile() throws Exception {
    String[] argFive = new String[] {"--csv-file", "boba.txt", "--output-dir", "/haha"};
    CommandLineParser sample = new CommandLineParser(argFive);
  }

  @Test(expected = IllegalEmailLetterException.class)
  public void noEmailTemplate() throws Exception {
    String[] arg = new String[] {"--csv-file", "boba.csv", "--output-dir", "/mochi", "--email"};
    CommandLineParser sample = new CommandLineParser(arg);
  }

  @Test(expected = IllegalEmailLetterException.class)
  public void noEmailandLetterTemplate() throws Exception {
    String[] arg = new String[] {"--csv-file", "boba.csv", "--output-dir", "/mochi", "--email",
        "--letter"};
    CommandLineParser sample = new CommandLineParser(arg);
  }

  @Test(expected = IllegalEmailLetterException.class)
  public void noLetterTemplate() throws Exception {
    String[] arg = new String[] {"--csv-file", "boba.csv", "--output-dir", "/mochi", "--letter"};
    CommandLineParser sample = new CommandLineParser(arg);
  }

  @Test(expected = IllegalEmailLetterException.class)
  public void noLetterOnlyEmailTemplate() throws Exception {
    String[] arg = new String[] {"--csv-file", "boba.csv", "--output-dir", "/mochi", "--letter",
        "--email", "--email-template", "mochinut.txt"};
    CommandLineParser sample = new CommandLineParser(arg);
  }

  @Test(expected = IllegalEmailLetterException.class)
  public void OnlyLetterNoEmailTemplate() throws Exception {
    String[] arg = new String[] {"--csv-file", "boba.csv", "--output-dir", "/mochi", "--letter",
        "--letter-template", "dochi.txt", "--email", "--email-template"};
    CommandLineParser sample = new CommandLineParser(arg);
  }

  @Test(expected = IllegalEmailLetterException.class)
  public void notTXTFile() throws Exception {
    String[] arg = new String[] {"--output-dir", "/mochi", "--letter", "--letter-template",
        "dochi.csv", "--email", "--email-template", "mochinut.csv", "--csv-file", "boba.csv"};
    CommandLineParser sample = new CommandLineParser(arg);
  }

  @Test(expected = NeedsRequiredCommandException.class)
  public void invalidOutputDirectoryPath() throws Exception {
    String[] arg = new String[] {"--output-dir", "boba", "--csv-file", "boba.csv"};
    CommandLineParser sample = new CommandLineParser(arg);
  }

  @Test
  public void correctArgOne() throws Exception {
    String[] arg = new String[] {"--output-dir", "/mochi/donut", "--letter", "--letter-template",
        "dochi.txt", "--email", "--email-template", "mochinut.txt", "--csv-file", "boba.csv"};
    CommandLineParser sample = new CommandLineParser(arg);
    assertEquals("/mochi/donut", sample.getOutputDir());
    assertEquals("boba.csv", sample.getFileToRead());
  }

  @Test
  public void correctArgTwo() throws Exception {
    String[] arg = new String[] {"--letter", "--letter-template", "dochi.txt", "--csv-file",
        "boba.csv", "--email", "--email-template", "mochinut.txt", "--output-dir", "/mochi"};
    CommandLineParser sample = new CommandLineParser(arg);
    assertEquals("dochi.txt", sample.getLetterTemplatePath());
    assertEquals("mochinut.txt", sample.getEmailTemplatePath());
  }

}