/**
 * AbstractShape is the "shared base" for all of our shapes.
 * - Circle, Rectangle, Triangle all need a reference point.
 * - They all can compute distance-from-origin based on that reference point.
 * - They can all share the same compareTo rule (compare by distance from origin).
 * - We never want to create a plain "AbstractShape" object (and Cannot do so)
 * - Only real shapes like Circle/Rectangle/Triangle should be created.
 */

import java.util.Objects;

public abstract class AbstractShape implements Shape {

  // field
  private final Point2D reference;

  // constructor
  protected AbstractShape(Point2D reference) {
    if (reference == null) {
      throw new IllegalArgumentException("reference point cannot be null");
    }
    this.reference = reference;
  }


  protected Point2D getReference() {
    return reference;
  }

  @Override
  public double distanceFromOrigin() {
    return reference.distanceToOrigin(); // in Point2D.java
  }

  @Override
  public int compareTo(Shape other) {
    if (other == null) {
      throw new IllegalArgumentException("other shape cannot be null");
    }
    return Double.compare(this.distanceFromOrigin(), other.distanceFromOrigin());
  }
}
