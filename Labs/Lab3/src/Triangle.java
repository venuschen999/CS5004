/**
 * Triangle made from three points (Point2D).
 *
 * The first point (p1) is the "reference point":
 * AbstractShape uses it to compute distance-from-origin and compareTo.
 * Area 0 is allowed.
 * Duplicate points are NOT allowed (throws IllegalArgumentException).
 */
public class Triangle extends AbstractShape {

  private final Point2D p1;
  private final Point2D p2;
  private final Point2D p3;

  /**
   * Construct a Triangle using three (x, y) coordinate pairs.
   * The first coordinate pair (x1, y1) is the reference point for this triangle.
   * @param x1 x-coordinate of the reference point
   * @param y1 y-coordinate of the reference point
   * @param x2 x-coordinate of the second point
   * @param y2 y-coordinate of the second point
   * @param x3 x-coordinate of the third point
   * @param y3 y-coordinate of the third point
   * @throws IllegalArgumentException if any two points are identical
   */
  public Triangle(double x1, double y1,
      double x2, double y2,
      double x3, double y3) {
    // this is call the other constructor
    this(new Point2D(x1, y1), new Point2D(x2, y2), new Point2D(x3, y3));
  }

  /**
   * Construct a Triangle using three Point2D objects.
   * p1 is the reference point for this triangle.
   * @param p1 the reference point (must not be null)
   * @param p2 the second point (must not be null)
   * @param p3 the third point (must not be null)
   * @throws IllegalArgumentException if any argument is null, or if any two points are identical
   */
  public Triangle(Point2D p1, Point2D p2, Point2D p3) {
    super(p1); // AbstractShape will reject null p1

    // reject nulls for the other points too
    if (p2 == null || p3 == null) {
      throw new IllegalArgumentException("Triangle points cannot be null");
    }

    // reject duplicates (any two points the same)
    if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
      throw new IllegalArgumentException("Triangle cannot have identical points");
    }

    // store the points as fields
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
  }

  /**
   * Compute and return the perimeter of this Triangle.
   * The perimeter is the sum of the lengths of the three sides
   *
   * @return the perimeter of this triangle
   */
  @Override
  public double perimeter() {
    double a = p1.distanceTo(p2);
    double b = p2.distanceTo(p3);
    double c = p3.distanceTo(p1);
    return a + b + c;
  }

  /**
   * Compute and return the area of this Triangle using Heron's formula.
   * If the three points are colinear, the area is 0.
   * @return the area of this triangle (0.0 is possible)
   */
  @Override
  public double area() {
    double a = p1.distanceTo(p2);
    double b = p2.distanceTo(p3);
    double c = p3.distanceTo(p1);

    // s = semi-perimeter
    double s = (a + b + c) / 2.0;

    // inside of sqrt: s(s-a)(s-b)(s-c)
    double under = s * (s - a) * (s - b) * (s - c);

    // 0 area allowed
    // Defensive coding: Floating-point rounding (prevents NaN) edge case
    if (under <= 0) {
      return 0.0;
    }

    return Math.sqrt(under);
  }

  /**
   * Return a String describing this Triangle.
   * e.g. Triangle: (0.00, 0.00), (3.00, 0.00), (0.00, 4.00)
   * @return a String containing the three points of this triangle
   */
  @Override
  public String toString() {
    return String.format("Triangle: %s, %s, %s", p1, p2, p3);
  }
}
