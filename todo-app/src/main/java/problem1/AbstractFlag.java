package problem1;

import java.util.Objects;

/**
 * An abstract class that represents a "Flag", which is either a
 * standalone command (--XXX) or a command with parameters (--XXX YYY)
 */
public abstract class AbstractFlag {
  private String name;

  /**
   * Constructor; initialize the name of the flag
   * @param name
   */
  public AbstractFlag(String name) {
    this.name = name;
  }

  /**
   * Getter
   * @return the name of the flag
   */
  public String getName() {
    return name;
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
    if (!(o instanceof AbstractFlag)) {
      return false;
    }
    AbstractFlag that = (AbstractFlag) o;
    return Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  /**
   * toString method
   * @return a string representation of the Flag
   */
  @Override
  public String toString() {
    return "AbstractFlag{" +
        "name='" + name + '\'' +
        '}';
  }
}