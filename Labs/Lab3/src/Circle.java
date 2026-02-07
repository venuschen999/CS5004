/**
 * A Circle shape defined by a reference point (center) and a radius.
 * The reference point is the center of the circle.
 */
public class Circle extends AbstractShape {

  private final double radius;

  /**
   * Construct a circle using center coordinates and radius.
   *
   * @param x x-coordinate of the center (reference point)
   * @param y y-coordinate of the center (reference point)
   * @param radius radius of the circle (must be > 0)
   */
  public Circle(double x, double y, double radius) {
    super(new Point2D(x, y)); // reference point is the center
    if (radius <= 0) {
      throw new IllegalArgumentException("radius must be > 0");
    }
    this.radius = radius;
  }

  /**
   * Construct a circle using a Point2D center and radius.
   *
   * @param center center point
   * @param radius radius of the circle, greater than 0
   */
  public Circle(Point2D center, double radius) {
    super(center); // AbstractShape rejects null
    if (radius <= 0) {
      throw new IllegalArgumentException("radius must be > 0");
    }
    this.radius = radius;
  }

  public double getRadius() {
    return radius;
  }

  @Override
  public double area() {
    return Math.PI * radius * radius;
  }

  @Override
  public double perimeter() {
    return 2.0 * Math.PI * radius;
  }

  @Override
  public String toString() {
    // uses AbstractShape's reference point
    return String.format("Circle: center=%s, r=%.2f", getReference(), radius);
  }
}
