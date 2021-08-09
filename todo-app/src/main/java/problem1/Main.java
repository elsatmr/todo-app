package problem1;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws Exception {
    try {
      CommandLineParser clp = new CommandLineParser(args);
      FileProcessor fp = new FileProcessor();
      OutputReader reader = new OutputReader(clp, fp);
      OutputEditor editor = new OutputEditor(reader);

      OutputGenerator generator = new OutputGenerator(editor, clp.getOutputDir());
      if (editor.getEmailOutputList() != null) {
        generator.generateEmail();
      }
      if (editor.getLetterOutputList() != null) {
        generator.generateLetter();
      }
    }
    catch (IllegalEmailLetterException | NeedsRequiredCommandException e) {
      System.out.print(e.toString());
    }
    catch (IOException io) {
      System.out.println("Input invalid. File not found or output directory is invalid.\n");
    }

  }



}
