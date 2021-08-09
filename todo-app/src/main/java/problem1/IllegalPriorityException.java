package problem1;

/**
 * An exception that indicates an incorrect priority value is entered.
 */

public class IllegalPriorityException extends IllegalArgumentException {

  public IllegalPriorityException() {
    super("Priority of a todo item must be 1, 2, or 3.");
  }
}
