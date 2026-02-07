/**
 * A Rectangle defined by a reference point, width and height.
 *the reference point is the rectangle's lower-left corner.
 */
public class Rectangle extends AbstractShape {

  private final double width;
  private final double height;

  /**
   * Construct a rectangle using lower-left corner coordinates and dimensions.
   *
   * @param x x-coordinate of the reference corner
   * @param y y-coordinate of the reference corner
   * @param width width of rectangle (must be > 0)
   * @param height height of rectangle (must be > 0)
   */
  public Rectangle(double x, double y, double width, double height) {
    super(new Point2D(x, y)); // reference point is lower-left
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height must be > 0");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Construct a rectangle using a Point2D reference corner and dimensions.
   *
   * @param corner reference corner (must not be null)
   * @param width width of rectangle (must be > 0)
   * @param height height of rectangle (must be > 0)
   */
  public Rectangle(Point2D corner, double width, double height) {
    super(corner); // AbstractShape rejects null
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height must be > 0");
    }
    this.width = width;
    this.height = height;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  @Override
  public double area() {
    return width * height;
  }

  @Override
  public double perimeter() {
    return 2.0 * (width + height);
  }

  @Override
  public String toString() {
    return String.format("Rectangle: corner=%s, w=%.2f, h=%.2f", getReference(), width, height);
  }
}

