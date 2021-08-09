package problem1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * A class that represents a Flag factory, which produces flags to be used in the rest of the program
 */
public class FlagFactory {

  // Dictionary of all flags and the number of arguments they take
  private static final HashMap<String, Integer> ARGS_REQUIRED = new HashMap<>();
  private ArrayList<? extends AbstractFlag> flags;

  // Static block to populate ARGS_REQUIRED
  static {
    ARGS_REQUIRED.put("--csv-file", 1);
    ARGS_REQUIRED.put("--add-todo", 0);
    ARGS_REQUIRED.put("--todo-text", 1);
    ARGS_REQUIRED.put("--completed", 0);
    ARGS_REQUIRED.put("--due", 1);
    ARGS_REQUIRED.put("--priority", 1);
    ARGS_REQUIRED.put("--category", 1);
    ARGS_REQUIRED.put("--complete-todo", 1);
    ARGS_REQUIRED.put("--display", 0);
    ARGS_REQUIRED.put("--show-incomplete", 0);
    ARGS_REQUIRED.put("--show-category", 1);
    ARGS_REQUIRED.put("--sort-by-date", 0);
    ARGS_REQUIRED.put("--sort-by-priority", 0);
  }

  /**
   * Core function of FlagFactory; check errors, compatibility, and automatically adds
   * flag objects to an ArrayList, with actual type depending on the number of arguments
   * a flag has
   * @param flagsOrder - flags order parsed by CLP
   * @throws Exception - if there are any errors or incompatibilities in the flags order
   */
  // Producer extends, consumer super (PECS)
  @SuppressWarnings("unchecked")
  public void produceFlags(ArrayList<String> flagsOrder)
      throws Exception {
    ArrayList<? super AbstractFlag> output = new ArrayList<>();
    this.checkAllErrors(flagsOrder);
    this.checkFlagCompatibility(flagsOrder);
    for (String s : flagsOrder) {
      String[] splitFlag = s.split(" ", 2);
      if (splitFlag.length == 1 || splitFlag[1].equals("")) {
        StandaloneFlag added = new StandaloneFlag(splitFlag[0]);
        output.add(added);
      } else {
        ParameterizedFlag added = new ParameterizedFlag(splitFlag[0], splitFlag[1]);
        output.add(added);
      }
    }
    /* The following two lines won't compile
    output.add("Test");
    output.add(5);
    */
    this.flags = (ArrayList<? extends AbstractFlag>) output;
  }

  /**
   * Getter
   * @return an ArrayList of subclasses of AbstractFlags to be used by FlagProcessor
   */
  public ArrayList<? extends AbstractFlag> getFlags() {
    return flags;
  }

  /**
   * Check all fundamental errors of the flag order
   * @param flagsOrder - an ArrayList of Strings parsed by CLP
   * @throws FlagNotFoundException - if there is an invalid flag name
   * @throws WrongNumberOfArgsException - if there are too few or too many arguments for any of the
   * options
   */
  private void checkAllErrors(ArrayList<String> flagsOrder)
      throws FlagNotFoundException, WrongNumberOfArgsException {
    for (String s : flagsOrder) {
      String[] splitFlag = s.split(" ", 2);
      if (!ARGS_REQUIRED.containsKey(splitFlag[0])) {
        throw new FlagNotFoundException();
      }
      if (ARGS_REQUIRED.get(splitFlag[0]) + 1 !=
          splitFlag.length - ((splitFlag[1].equals("")) ? 1 : 0)) {
        throw new WrongNumberOfArgsException();
      }
      this.checkParameterErrors(splitFlag);
    }
  }

  /**
   * Check if there are parameter errors in four "primary" classes
   * @param s - option name whose parameter needs to be checked
   */
  private void checkParameterErrors(String[] s) {
    switch (s[0]) {
      case "--csv-file":
        checkDirectory(s[1]);
        break;
      case "--due":
        checkDateFormat(s[1]);
        break;
      case "--priority":
        checkPriority(s[1]);
        break;
      case "--complete-todo":
        checkId(s[1]);
        break;
    }
  }

  /**
   * Helper method, ensuring the directory is valid for both Windows and MS
   * @param s - A directory path in either Windows or *nix format
   */
  private void checkDirectory(String s) {
    String os = System.getProperty("os.name").toLowerCase();
    Pattern validMacDirectory = Pattern.compile("(/[a-zA-Z0-9_-]+)+.csv");
    Pattern validWindowsDirectory = Pattern.compile("[A-Z]:\\\\[^<>:\"/|?*]*");
    if (os.contains("windows")) {
      if (!validWindowsDirectory.matcher(s).matches()) {
        System.out.println(s);
        throw new IllegalPathException("Windows");
      }
    } else {
      if (!validMacDirectory.matcher(s).matches() || !s.endsWith(".csv")) {
        throw new IllegalPathException("Mac");
      }
    }
  }

  /**
   * Check the date format as a string
   * @param s - date as a string, expected to be in mm/dd/yyyy format
   * @throws DateTimeParseException if the string cannot be parsed in this fashion
   */
  private void checkDateFormat(String s) throws DateTimeParseException {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate.parse(s, format);
  }

  /**
   * Check the specified priority as a string
   * @param s - priority as a string, expected to be "1" or "2" or "3"
   */
  private void checkPriority(String s) {
    int priority = Integer.parseInt(s);
    if (priority != 1 && priority != 2 && priority != 3) {
      throw new IllegalPriorityException();
    }
  }

  /**
   * Check the specified id as a string
   * @param s - id as a string, expected to be a positive integer
   */
  private void checkId(String s) {
    int id = Integer.parseInt(s);
    if (id < 1) {
      throw new IllegalIdException();
    }
  }

  /**
   * A method that checks for any incompatibilities for well-formated flags
   * @param flagsOrder - the flag order parsed by CLP
   * @throws Exception - various Exceptions indicating different categories incompatibilities
   */
  private void checkFlagCompatibility(ArrayList<String> flagsOrder) throws Exception {
    HashMap<String, Integer> flagCounts = this.countAllFlags(flagsOrder);
    String[] addTodoOptionals = new String[]{"--completed", "--due", "--priority"};
    String[] displayOptionals = new String[]{"--show-incomplete", "--show-category",
        "--sort-by-date", "--sort-by-priority"};

    if (flagCounts.get("--csv-file") != 1) {
      throw new Exception("There must be exactly one --csv-file option.");
    }

    if (flagCounts.get("--add-todo") + flagCounts.get("--complete-todo") + flagCounts
        .get("--display") == 0) {
      throw new Exception("There must be at least one of --add-todo, "
          + "--complete-todo, and --display, with their respective optional parameters.");
    }

    if (flagCounts.get("--add-todo") > 1) {
      throw new Exception("There can be at most one --add-todo option.");
    }

    if (flagCounts.get("--add-todo") == 1) {
      if (flagCounts.get("--todo-text") != 1) {
        throw new Exception("If --add-todo option is supplied,"
            + " there must be exactly one --todo-text option.");
      }
      for (String s : addTodoOptionals) {
        if (flagCounts.get(s) > 1) {
          throw new Exception(
              "If --add-todo option is supplied," + " there can be at most one " + s + " option.");
        }
      }
    }

    if (flagCounts.get("--display") > 1) {
      throw new Exception("There can be at most one --display option.");
    }
    if (flagCounts.get("--display") == 1) {
      if (flagCounts.get("--sort-by-date") == 1 && flagCounts.get("--sort-by-priority") == 1) {
        throw new Exception(
            "--sort-by-date is not combinable with --sort-by-priority in --display");
      }

      for (String s : displayOptionals) {
        if (flagCounts.get(s) > 1) {
          throw new Exception(
              "If --display option is supplied," + " there can be at most one " + s + " option.");
        }
      }
    }

    if (flagCounts.get("--add-todo") == 0) {
      for (String s: addTodoOptionals) {
        if (flagCounts.get(s) > 0) {
          throw new Exception("If --add-todo option is not supplied, there could not be any of" +
              " the --complete, --due or --priority options.");
        }
      }
    }

    if (flagCounts.get("--display") == 0) {
      for (String s: displayOptionals) {
        if (flagCounts.get(s) > 0) {
          throw new Exception("If --display option is not supplied, there could not be any of" +
              " the --show-incomplete, --show-category, --sort-by-date, or --sort-by-priority" +
              " options.");
        }
      }
    }
  }

  /**
   * A helper method for checkFlagCompatibility() that counts the number of all flags
   * @param flagsOrder - the flag order parsed by CLP
   * @return a HashMap containing the option names and their counts
   */
  private HashMap<String, Integer> countAllFlags(ArrayList<String> flagsOrder) {
    HashMap<String, Integer> output = new HashMap<>();
    ARGS_REQUIRED.forEach((k, v) -> output.put(k, 0));
    for (String s : flagsOrder) {
      String[] splitFlag = s.split(" ", 2);
      output.merge(splitFlag[0], 1, Integer::sum);
    }
    return output;
  }
}