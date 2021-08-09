package problem1;

import java.util.ArrayList;

public class CommandLineParser {

  private ArrayList<String> flags;

  /**
   * Constructor for command line parser
   * @param args a string of array for command line arguments
   * @throws WrongNumberOfArgsException Throws an exception if any arguments with space (" ")
   * are passed in without being wrapped in curly brackets or the number of arguments passed into a
   * command is incorrect. (e.g.: --show-category can only have one argument.) or
   * if the command is not passed in with "--"
   */
  public CommandLineParser(String[] args) throws WrongNumberOfArgsException {
    this.parseArgs(args);
  }

  /**
   * Returns an array list of strings of the parsed command line argument
   * @return an array list of strings of the parsed command line argument
   */
  public ArrayList<String> getFlags() {
    return this.flags;
  }

  /**
   * Parses command line argument, stores it into an array list of strings.
   * Throws an exception if any arguments with space (" ") are passed in without
   * being wrapped in curly brackets or the number of arguments passed into a
   * command is incorrect. (e.g.: --show-category can only have one argument.) or
   * if the command is not passed in with "--"
   */
  private void parseArgs(String[] args) throws WrongNumberOfArgsException {
    String joinedArgs = "";
    joinedArgs = String.join(" ", args);
    joinedArgs = joinedArgs.replaceAll(
        "(--\\S+)\\s*(?!--)((\\{[^{}]*})|(\\S*))\\s*",
        "$1 $2\n");
    String[] flagArray = joinedArgs.split("\n");
    ArrayList<String> output = new ArrayList<>();

    for (String line : flagArray) {
      if (!line.startsWith("--")) {
        throw new WrongNumberOfArgsException();
      } else {
        output.add(line);
      }
    }
    this.flags = output;
  }
}
