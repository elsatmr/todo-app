package problem1;

import java.util.regex.Pattern;

/**
 * A class representing a CommandLineParser object
 */

public class CommandLineParser {
  private static final String OUTPUT_DIR = "--output-dir";
  private static final String FILE_TO_READ = "--csv-file";
  private static final String EMAIL = "--email";
  private static final String EMAIL_TEMPLATE = "--email-template";
  private static final String LETTER = "--letter";
  private static final String LETTER_TEMPLATE = "--letter-template";

  private boolean hasOutputDir = false;
  private String outputDir;
  private boolean hasFileToRead = false;
  private String fileToRead;
  private boolean isEmail = false;
  private String emailTemplatePath;
  private boolean isLetter = false;
  private String letterTemplatePath;
  private boolean isValidDirectory = false;

  /**
   * Constructor for CommandLineParser
   * @param args a string array of arguments
   * @throws Exception if the input is not per specification, refer to indiv. exception classes.
   */
  public CommandLineParser(String[] args) throws Exception {
    this.checkOutputDir(args);
    this.checkFileToRead(args);
    this.checkIfEmailRequested(args);
    if (this.isEmail) {
      this.checkEmailTemplate(args);
    }
    this.checkIfLetterRequested(args);
    if (this.isLetter) {
      this.checkLetterTemplate(args);
    }
  }

  /**
   * Returns the output directory
   * @return output directory
   */
  public String getOutputDir() {
    return this.outputDir;
  }

  /**
   * Returns the CSV file to read
   * @return csv file to read
   */
  public String getFileToRead() {
    return this.fileToRead;
  }

  /**
   * Returns the path for letter template
   * @return the path for letter template
   */
  public String getLetterTemplatePath() {
    return this.letterTemplatePath;
  }

  /**
   * Returns the path for email template
   * @return the path for email template
   */
  public String getEmailTemplatePath() {
    return this.emailTemplatePath;
  }

  /**
   * Returns true if user wants to generate email, false otherwise
   * @return true if user wants to generate email, false otherwise
   */
  public boolean getIsEmail() {
    return this.isEmail;
  }

  /**
   * Returns true if user wants to generate letter, false otherwise
   * @return true if user wants to generate letter, false otherwise
   */
  public boolean getIsLetter() {
    return this.isLetter;
  }

  /**
   * Checks if the user gives an output directory with a valid path and sets the directory path
   * with the validated path.
   * @param args User's comand
   * @throws NeedsRequiredCommandException if user does not give --output-dir command with valid path
   */
  public void checkOutputDir(String[] args) throws NeedsRequiredCommandException {
    int idx = this.idxAt(OUTPUT_DIR, args);
    if (idx != -1 && idx+1 < args.length) {
      this.checkDirectory(args[idx+1]);
      if (this.isValidDirectory) {
        this.hasOutputDir = true;
        this.setOutputDir(args[idx + 1]);
        return;
      }
    }
    throw new NeedsRequiredCommandException();
  }

  /**
   * Helper method, ensuring the directory is valid for both Windows and MS
   * @param path user-given output directory path
   */
  private void checkDirectory(String path) {
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("windows")) {
      this.validWindowsDirectory(path);
    } else {
      this.validMacDirectory(path);
    }
  }

  /**
   * Ensures if user's machine is MAC, it is a valid directory
   * @param path user given output directory
   */
  private void validMacDirectory(String path) {
    Pattern validDirectory = Pattern.compile("^/|(/[a-zA-Z0-9_-]+)+$");
    if (validDirectory.matcher(path).matches()) {
      this.isValidDirectory = true;
    }
  }

  /**
   * Ensures if user's machine is Windows, it is a valid directory
   * @param path user given output directory
   */
  private void validWindowsDirectory(String path) {
    Pattern validDirectory = Pattern.compile("^[A-Z]:\\\\[^<>:\"/|?*]*$");
    if (validDirectory.matcher(path).matches()) {
      this.isValidDirectory = true;
    }
  }

  /**
   * Helper method, sets the output directory with a parameter
   * @param directory directory path
   */
  private void setOutputDir(String directory) {
    this.outputDir = directory;
  }

  /**
   * Checks if the user provides the right command to give a database to read and
   * with the correct file format (.csv)
   * @param args user's command line
   * @throws NeedsRequiredCommandException if user doesn't give the command/ file not in .csv
   */
  public void checkFileToRead(String[] args) throws NeedsRequiredCommandException {
    int idx = this.idxAt(FILE_TO_READ, args);
    if (idx != -1 && idx+1 < args.length && !args[idx + 1].startsWith("--")
        && args[idx + 1].endsWith(".csv")) {
      this.hasFileToRead = true;
      this.setFileToRead(args[idx+1]);
    } else {
      throw new NeedsRequiredCommandException();
    }
  }

  /**
   * Sets the .csv file to read
   * @param file file to read
   */
  private void setFileToRead(String file) {
    this.fileToRead = file;
  }

  /**
   * Checks if the user requested to generate an email
   * @param args user's command line
   */
  public void checkIfEmailRequested(String[] args) {
    int idx = this.idxAt(EMAIL, args);
    if (idx != -1) {
      this.isEmail = true;
    }
  }

  /**
   * Checks if the user provides an email template with the right file format
   * @param args user's command line
   * @throws IllegalEmailLetterException if user doesn't provide a template or does but w/o .txt format
   */
  public void checkEmailTemplate(String[] args) throws IllegalEmailLetterException {
    int idx = this.idxAt(EMAIL, args);
    if (idx + 2 < args.length && args[idx + 1].equals(EMAIL_TEMPLATE) && args[idx + 2]
        .endsWith(".txt")) {
      this.setEmailTemplate(args[idx + 2]);
    } else {
      throw new IllegalEmailLetterException();
    }
  }

  /**
   * Sets the email template path
   * @param email email template path
   */
  private void setEmailTemplate(String email) {
    this.emailTemplatePath = email;
  }

  /**
   * Checks if the user requested to generate a letter
   * @param args user's command line
   */
  public void checkIfLetterRequested(String[] args) {
    int idx = this.idxAt(LETTER, args);
    if (idx != -1) {
      this.isLetter = true;
    }
  }

  /**
   * Checks if the user provides a letter template with the right file format
   * @param args user's command line
   * @throws IllegalEmailLetterException if user doesn't provide a template or does but w/o .txt format
   */
  public void checkLetterTemplate(String[] args) throws IllegalEmailLetterException {
    int idx = this.idxAt(LETTER, args);
    if (idx + 2 < args.length && args[idx + 1].equals(LETTER_TEMPLATE) && args[idx + 2]
        .endsWith(".txt")) {
      this.setLetterTemplate(args[idx + 2]);
    } else {
      throw new IllegalEmailLetterException();
    }
  }

  /**
   * Sets the letter template path
   * @param letter letter template path
   */
  private void setLetterTemplate(String letter) {
    this.letterTemplatePath = letter;
  }

  /**
   * Returns the index where the string is in an array
   * @param find the string to find
   * @param args user's command line
   * @return the index, an int.
   */
  private int idxAt(String find, String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals(find)) {
        return i;
      }
    }
    return -1;
  }

}
