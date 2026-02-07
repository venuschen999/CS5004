import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Shape interface contract using a few Shapes.
 * These tests focus on behavior that should be true for ALL shapes:
 * - area is non-negative
 * - perimeter is positive
 * Put shape-specific tests (like Circle radius rules, Triangle duplicate-point rules, etc.)
 * in CircleTest / RectangleTest / TriangleTest.
 */
public class ShapeTest {

  private Shape s1;
  private Shape s2;
  private Shape s3;

  @BeforeEach
  public void setUp() {
    s1 = new Circle(0, 0, 2.0);
    s2 = new Rectangle(0, 0, 3.0, 4.0);
    s3 = new Triangle(3, 4, 4, 4, 3, 5); // reference point (3,4)
  }

  @Test
  public void testAreaIsNonNegative() {
    assertTrue(s1.area() >= 0);
    assertTrue(s2.area() >= 0);
    assertTrue(s3.area() >= 0);
  }

  @Test
  public void testPerimeterIsPositive() {
    assertTrue(s1.perimeter() > 0);
    assertTrue(s2.perimeter() > 0);
    assertTrue(s3.perimeter() > 0);
  }

  @Test
  public void testDistanceFromOrigin() {
    assertEquals(0.0, s1.distanceFromOrigin(), 1e-9);  // (0,0)
    assertEquals(0.0, s2.distanceFromOrigin(), 1e-9);  // (0,0)
    assertEquals(5.0, s3.distanceFromOrigin(), 1e-9);  // (3,4)
  }
}