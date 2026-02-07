import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

  private Circle c1; // center (0,0), r=2
  private Circle c2; // center (3,4), r=1.5

  @BeforeEach
  public void setUp() {
    c1 = new Circle(0, 0, 2.0);
    c2 = new Circle(3, 4, 1.5);
  }

  @Test
  public void testConstructor() {
    // create circle with coordinates and radius
    Circle c = new Circle(0, 0, 2.0);

    assertEquals(Math.PI * 4.0, c.area(), 1e-9);              // r=2 => area = 4π
    assertEquals(2.0 * Math.PI * 2.0, c.perimeter(), 1e-9);   // r=2 => perimeter = 4π

    // create circle with a point and radius
    Point2D center = new Point2D(3, 4);
    Circle c3 = new Circle(center, 1.5);

    assertEquals(5.0, c3.distanceFromOrigin(), 1e-9);          // center (3,4) => distance 5
    assertEquals(Math.PI * 2.25, c3.area(), 1e-9);             // r=1.5 => area = 2.25π

    // Test Exception for illegal radius
    assertThrows(IllegalArgumentException.class, () -> new Circle(0, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> new Circle(0, 0, -2));
  }

  @Test
  public void testGetRadius() {
    assertEquals(2.0, c1.getRadius(), 1e-9);
    assertEquals(1.5, c2.getRadius(), 1e-9);
  }

  @Test
  public void testArea() {
    assertEquals(Math.PI * 4.0, c1.area(), 1e-9);         // r=2 => pi*4
    assertEquals(Math.PI * 2.25, c2.area(), 1e-9);        // r=1.5 => pi*2.25
  }

  @Test
  public void testPerimeter() {
    assertEquals(2.0 * Math.PI * 2.0, c1.perimeter(), 1e-9);   // 2*pi*r
    assertEquals(2.0 * Math.PI * 1.5, c2.perimeter(), 1e-9);
  }

  @Test
  public void testDistanceFromOrigin() {
    // c1 center (0,0) -> distance 0
    assertEquals(0.0, c1.distanceFromOrigin(), 1e-9);

    // c2 center (3,4) -> distance 5
    assertEquals(5.0, c2.distanceFromOrigin(), 1e-9);
  }

  @Test
  public void testToString() {
    // These expected strings assume:
    // Point2D.toString() => "(%.2f, %.2f)"
    // Circle.toString()  => "Circle: center=%s, r=%.2f"
    assertEquals("Circle: center=(0.00, 0.00), r=2.00", c1.toString());
    assertEquals("Circle: center=(3.00, 4.00), r=1.50", c2.toString());
  }

  @Test
  public void testCompareTo() {
    // Reference points (centers):
    // near: (0,0)  distance = 0
    // far:  (3,4)  distance = 5
    // tie:  (0,0)  distance = 0  (same as near)
    Shape near = new Circle(0, 0, 2.0);
    Shape far = new Circle(3, 4, 1.0);
    Shape tie = new Circle(0, 0, 1.5);

    assertTrue(near.compareTo(far) < 0);   // smaller
    assertTrue(far.compareTo(near) > 0);   // bigger
    assertEquals(0, near.compareTo(tie));  // tie
  }
}

