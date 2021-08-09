package problem1;

import java.util.Objects;

/**
 * A class that represents a flag with a parameter
 */
public class ParameterizedFlag extends AbstractFlag {

  private String parameter;

  /**
   * Constructor; initializes the parameterized flag
   * @param name - name of the flag
   * @param parameter - parameter/argument of the flag
   */
  public ParameterizedFlag(String name, String parameter) {
    super(name);
    this.parameter = parameter;
  }

  /**
   * Getter
   * @return the parameter as a string
   */
  public String getParameter() {
    return parameter;
  }

  /**
   * Equals method
   * @param o - an object to be checked for equality
   * @return true if equal and false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ParameterizedFlag)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ParameterizedFlag that = (ParameterizedFlag) o;
    return Objects.equals(getParameter(), that.getParameter());
  }

  /**
   * Hashcode method
   * @return the hashcode of a ParameterizedFlag
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getParameter());
  }

  /**
   * toString
   * @return a string representation of the ParameterizedFlag
   */
  @Override
  public String toString() {
    return "ParameterizedFlags{" +
        "parameter=" + parameter +
        "} " + super.toString();
  }
}
