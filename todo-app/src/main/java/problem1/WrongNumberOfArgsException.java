package problem1;

public class WrongNumberOfArgsException extends Exception {
  private String message;

  public WrongNumberOfArgsException() {
    super("The number of arguments provided is incorrect for one or more options.\n"
        + "N.B.: Any arguments with a space needs to be wrapped"
        + " inside a pair of curly brackets, \"{}\"");
  }


}
