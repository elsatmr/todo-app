package problem1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class representing OutputGenerator. Writes the populated files into individual .txt files
 */

public class OutputGenerator {
  private OutputEditor outputEditor;
  private String folder;

  /**
   * Constructor for OutputGenerator
   * @param outputEditor OutputEditor object
   * @param folder file path it will write to
   */
  public OutputGenerator(OutputEditor outputEditor,String folder) {
    this.outputEditor = outputEditor;
    this.folder = folder;
  }

  /**
   * Generates and write email files
   * @throws IOException if folder is incorrect or file name is incorrect
   */
  public void generateEmail() throws IOException {
    for (int i = 0; i < outputEditor.getEmailOutputList().size(); i++) {
      String fileName = this.outputEditor.getReader().getActualData().get(i)[0] +
          this.outputEditor.getReader().getActualData().get(i)[1] + "_email.txt";
      File f = new File(this.folder + File.separator + fileName);
      if (f.createNewFile()) {
        BufferedWriter write = new BufferedWriter(new FileWriter(f));
        for (int j = 0; j < this.outputEditor.getEmailOutputList().get(i).size(); j++) {
          for (String str : this.outputEditor.getEmailOutputList().get(i).get(j)) {
            write.write(str + " ");
          }
          write.newLine();
        }
        write.close();
      }
    }
  }

  /**
   * Generates and write letter files
   * @throws IOException if folder is incorrect or file name is incorrect
   */
  public void generateLetter() throws IOException {
    for (int i = 0; i < outputEditor.getLetterOutputList().size(); i++) {
      String fileName = this.outputEditor.getReader().getActualData().get(i)[0] +
          this.outputEditor.getReader().getActualData().get(i)[1] + "_letter.txt";
      File f = new File(this.folder + File.separator + fileName);
      if (f.createNewFile()) {
        BufferedWriter write = new BufferedWriter(new FileWriter(f));
        for (int j = 0; j < this.outputEditor.getLetterOutputList().get(i).size(); j++) {
          for (String str : this.outputEditor.getLetterOutputList().get(i).get(j)) {
            write.write(str + " ");
          }
          write.newLine();
        }
        write.close();
      }
    }
  }
}
