package problem1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

/*
 *This is a class to process the input file.
 */
public class FileProcessor {
  private ArrayList<String> header;
  private ArrayList<String[]> csvList;
  private ArrayList<String[]> emailTemplate;
  private ArrayList<String[]> letterTemplate;
  /**
   * Constructor for fileProcessor, all field should be null by default.
   */
  public FileProcessor() {
    this.header = null;
    this.csvList = null;
    this.emailTemplate = null;
    this.letterTemplate = null;
  }

  /**
   * Function to process csv file to make a header list and a information list.
   * @param path the directory.
   * @throws IOException exception when there is trouble to read file from given directory.
   */
  public void readCsvFile(String path) throws IOException {
    String filePath = System.getProperty("user.dir") + File.separator + path;
    Reader reader = new FileReader(filePath);
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
   * this is a helper function to make all space delimited file to a list of list.
   * @param path the given directory.
   * @return a Arraylist of String Array.
   * @throws IOException exception when there is trouble to read file from given directory.
   */
  private ArrayList<String[]> fileToStringList(String path) throws IOException {
    String filePath = System.getProperty("user.dir") + File.separator + path;
    BufferedReader file = new BufferedReader(new FileReader(filePath));
    ArrayList<String[]> listString = new ArrayList<>();
    String fileLine;
    while((fileLine = file.readLine()) != null){
      String[] temp = fileLine.split(" ",0);
      listString.add(temp);
    }
    return listString;
  }

  /**
   * set the emailTemplate, if any.
   * @param path the given directory.
   * @throws IOException exception when there is trouble to read file from given directory.
   */
  public void setEmailTemplate(String path) throws IOException {
    this.emailTemplate = fileToStringList(path);
  }

  /**
   * set the letterTemplate, if any.
   * @param path the given directory.
   * @throws IOException exception when there is trouble to read file from given directory.
   */
  public void setLetterTemplate(String path) throws IOException {
    this.letterTemplate = fileToStringList(path);
  }

  public ArrayList<String> getHeader() {
    return this.header;
  }

  public ArrayList<String[]> getCsvList() {
    return this.csvList;
  }

  public ArrayList<String[]> getEmailTemplate() {
    return this.emailTemplate;
  }

  public ArrayList<String[]> getLetterTemplate() {
    return this.letterTemplate;
  }
}