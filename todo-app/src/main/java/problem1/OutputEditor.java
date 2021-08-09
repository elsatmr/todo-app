package problem1;

import java.util.ArrayList;
import java.util.HashMap;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder.Output;

/**
 * A class that populates the template with the data from the database
 */
public class OutputEditor {
  private HashMap<DoubleIndices, String[]> letterPlaceholder;
  private HashMap<DoubleIndices, String[]> emailPlaceholder;
  private ArrayList<ArrayList<String[]>> letterOutputList;
  private ArrayList<ArrayList<String[]>> emailOutputList;
  private OutputReader reader;


  /**
   * Constructor for OutputEditor class
   * @param reader OutputReader object
   */
  public OutputEditor(OutputReader reader) {
    this.reader = reader;
    if (this.reader.getTemplateLetter() != null) {
      letterPlaceholder = this.locatePlaceholders(this.reader.getTemplateLetter());
      letterOutputList = this.populateDatabase(letterPlaceholder, reader.getTemplateLetter());
    }
    if (this.reader.getTemplateEmail() != null) {
      emailPlaceholder = this.locatePlaceholders(this.reader.getTemplateEmail());
      emailOutputList = this.populateDatabase(emailPlaceholder, reader.getTemplateEmail());
    }
  }

  /**
   * Core function; track down all placeholders, record their row and column numbers, strip the
   * double brackets, and putting all of them into a HashMap
   * @param template - the template to be processed
   * @return a HashMap of problem1.DoubleIndices as key, which records row and column numbers as fields X and Y, and
   * records field names as value
   */
  public HashMap<DoubleIndices, String[]> locatePlaceholders(ArrayList<String[]> template) {
    HashMap<DoubleIndices, String[]> output = new HashMap<>();
    Integer row = 0;
    for (String[] lines : template) {
      Integer col = 0;
      for (String element : lines) {
        if (element.contains("[[") && element.contains("]]")) { // Surrounded by double brackets
          String prefix = element.replaceAll("(.*)\\[\\[([a-zA-Z_]+)]](.*)", "$1");
          String content = element.replaceAll("(.*)\\[\\[([a-zA-Z_]+)]](.*)", "$2");
          String suffix = element.replaceAll("(.*)\\[\\[([a-zA-Z_]+)]](.*)", "$3");
          String[] wholeString = {prefix, content, suffix};
          output.put(new DoubleIndices(row, col), // Custom problem1.DoubleIndices object as key
              wholeString);
          // fields stripped of [[]]'s as value
        }
        col++;
      }
      row++;
    }
    return output;
  }

  /**
   * Core function; generate a single file with all placeholders replaced with actual data based on
   * a single template and a single row of actual data; template and actual data row number to be
   * supplied by user
   * @param placeholderDict - placeholder HashMap generated by locatePlaceholders()
   * @param template - template, which could be this.templateEmail or this.templateLetter
   * @param whichRowToReplace - row number of actual data to replace
   * @return a letter or email for a single person with all placeholder fields replaced and ready
   * to go
   */
  public ArrayList<String[]> replaceWithActualData(HashMap<DoubleIndices, String[]> placeholderDict,
      ArrayList<String[]> template, Integer whichRowToReplace) {
    ArrayList<String[]> output = template;
    String[] actualRow = this.reader.getActualData().get(whichRowToReplace);
    for (int i = 0; i < actualRow.length; i++) {
      actualRow[i] = actualRow[i].replaceAll("\"", "");
    }
    placeholderDict.forEach(
        (k, v) -> (output.get(k.getX()))[k.getY()] = v[0] +
            actualRow[this.reader.getHeaderFields().indexOf("\"" + v[1] + "\"")] + v[2]
    ); // lambda magic :))
    return output;
  }

  /**
   * Populates the template with the data from the CSV database
   * @param placeholder a hashmap with placeholders on where to fill in data
   * @param template template to fill in the data
   * @return an array list of array list of strings array filled with files filled with info.
   */
  public ArrayList<ArrayList<String[]>> populateDatabase(HashMap<DoubleIndices,
      String[]> placeholder, ArrayList<String[]> template) {
    ArrayList<ArrayList<String[]>> temp = new ArrayList<>();
    for (int i = 1; i < this.reader.getActualData().size(); i++) {
      temp.add(this.replaceWithActualData(placeholder, template, i));
    }
    return temp;
  }

  /**
   * Returns the an array list of letter templates that are filled with database
   * @return the an array list of letter templates that are filled with database
   */
  public ArrayList<ArrayList<String[]>> getLetterOutputList() {
    return letterOutputList;
  }

  /**
   * Returns the an array list of email templates that are filled with database
   * @return the an array list of email templates that are filled with database
   */
  public ArrayList<ArrayList<String[]>> getEmailOutputList() {
    return emailOutputList;
  }

  /**
   * Returns the OutputReader object
   * @return the output reader object
   */
  public OutputReader getReader() {
    return this.reader;
  }
}