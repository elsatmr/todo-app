package problem1;

import java.util.Objects;

/**
 * A class representing double indices
 */

public class DoubleIndices {

  private Integer x, y;

  /**
   * Constructor for double indices class
   * @param x integer x
   * @param y integer y
   */
  public DoubleIndices(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the int x
   * @return int x
   */
  public Integer getX() {
    return x;
  }

  /**
   * Returns the int y
   * @return the int y
   */
  public Integer getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DoubleIndices)) {
      return false;
    }
    DoubleIndices that = (DoubleIndices) o;
    return Objects.equals(getX(), that.getX()) && Objects
        .equals(getY(), that.getY());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}