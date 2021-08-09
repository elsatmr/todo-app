package problem1;

/**
 * An exception that indicates a specified flag is not found.
 */

public class FlagNotFoundException extends Exception {

  public FlagNotFoundException() {
    super("Invalid command entered.");
  }
}
