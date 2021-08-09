package problem1;

/**
 * A class that represents a standalone flag that does not need arguments
 */
public class StandaloneFlag extends AbstractFlag {

  /**
   * Constructor; initializes the standalone flag
   * @param name - the name of the flag
   */
  public StandaloneFlag(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return "StandaloneFlag{} " + super.toString();
  }
}