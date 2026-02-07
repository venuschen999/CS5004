/**
 * A shape contract. Shapes can calculate area/perimeter
 * and can be compared by distance of their reference point from origin.
 */
public interface Shape extends Comparable<Shape> {

  double area();

  double perimeter();

  // based on reference point
  double distanceFromOrigin();
}
