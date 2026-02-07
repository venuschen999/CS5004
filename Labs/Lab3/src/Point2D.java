/**
 * A 2D point.
 */
public class Point2D {
  private final double x;
  private final double y;

  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  /**
   * Distance from this point to the origin (0,0).
   */
  public double distanceToOrigin() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Distance from this point to another point.
   */
  public double distanceTo(Point2D other) {
    if (other == null) {
      throw new IllegalArgumentException("other point cannot be null");
    }
    double dx = this.x - other.x;
    double dy = this.y - other.y;
    return Math.sqrt(dx * dx + dy * dy);
  }

  @Override
  public String toString() {
    return String.format("(%.2f, %.2f)", x, y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point2D)) return false;
    Point2D that = (Point2D) o;
    return Double.compare(this.x, that.x) == 0
        && Double.compare(this.y, that.y) == 0;
  }

}
