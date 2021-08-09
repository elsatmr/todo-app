package problem1;

/**
 * An exception that indicates that an invalid ID value has been entered.
 */

public class IllegalIdException extends IllegalArgumentException {

  public IllegalIdException() {
    super("ID of a todo item must be a positive integer.");
  }
}
