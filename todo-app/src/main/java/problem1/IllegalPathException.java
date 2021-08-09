package problem1;

/**
 * An exception that indicates the path is illegal.
 */

public class IllegalPathException extends IllegalArgumentException {

  public IllegalPathException(String os) {
    super("The inputted path is an invalid " + os + " file path.");
  }
}
