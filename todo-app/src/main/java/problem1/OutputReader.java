package problem1;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class represents an OutputReader, setting up the template and database to process.
 */

public class OutputReader {
  private FileProcessor fp;
  private CommandLineParser clp;
  private ArrayList<String> headerFields;
  private ArrayList<String[]> actualData, templateEmail, templateLetter;

  /**
   * Constructor for OutputReader object
   * @param clp CommandLineParser object
   * @param fp FileProcessor object
   * @throws IOException if folder is incorrect or file name is incorrect
   */
  public OutputReader(CommandLineParser clp, FileProcessor fp) throws IOException {
    this.clp = clp;
    this.fp = fp;
    this.loadEverything();
  }

  /**
   * Returns the email template file in a form of an array list of a string array
   * @return the email template file in a form of an array list of a string array
   */
  public ArrayList<String[]> getTemplateEmail() {
    return templateEmail;
  }

  /**
   * Returns the letter template file in a form of an array list of a string array
   * @return the letter template file in a form of an array list of a string array
   */
  public ArrayList<String[]> getTemplateLetter() {
    return templateLetter;
  }

  /**
   * Returns the header fields in a form of an array list of strings.
   * @return the header fields in a form of an array list of strings.
   */
  public ArrayList<String> getHeaderFields() {
    return headerFields;
  }

  /**
   * Returns the actual CSV database in a form of an array list of strings array
   * @return the actual CSV database in a form of an array list of strings array
   */
  public ArrayList<String[]> getActualData() {
    return actualData;
  }

  /**
   * Loads everything from the CSV database and sets things up so the output file can be generated
   * @throws IOException if folder is incorrect or file name is incorrect
   */
  public void loadEverything() throws IOException {
    String path = this.clp.getFileToRead();
    this.fp.readCsvFile(path);
    this.headerFields = this.fp.getHeader();
    this.actualData = this.fp.getCsvList();
    boolean produceEmail = this.clp.getIsEmail();
    boolean produceLetter = this.clp.getIsLetter();
    if (produceEmail) {
      this.fp.setEmailTemplate(this.clp.getEmailTemplatePath());
      this.templateEmail = this.fp.getEmailTemplate();
    }
    if (produceLetter) {
      this.fp.setLetterTemplate(this.clp.getLetterTemplatePath());
      this.templateLetter = this.fp.getLetterTemplate();
    }
  }

}