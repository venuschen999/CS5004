import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

  private Rectangle r1; // corner (0,0), w=3, h=4
  private Rectangle r2; // corner (1,2), w=2.5, h=1.5

  @BeforeEach
  public void setUp() {
    r1 = new Rectangle(0, 0, 3.0, 4.0);
    r2 = new Rectangle(1, 2, 2.5, 1.5);
  }

  @Test
  public void testConstructor() {
    // create rectangle with coordinates and dimensions
    Rectangle r = new Rectangle(0, 0, 3.0, 4.0);

    assertEquals(12.0, r.area(), 1e-9);          // 3*4
    assertEquals(14.0, r.perimeter(), 1e-9);     // 2*(3+4)

    // create rectangle with a point and dimensions
    Point2D corner = new Point2D(1, 2);
    Rectangle r3 = new Rectangle(corner, 2.5, 1.5);

    assertEquals(Math.sqrt(5.0), r3.distanceFromOrigin(), 1e-9); // corner (1,2) => sqrt(5)
    assertEquals(3.75, r3.area(), 1e-9);                          // 2.5*1.5

    // Test Exception for illegal dimensions
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 0, 0, 4));
    assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 0, 3, -1));
  }

  @Test
  public void testGetWidth() {
    assertEquals(3.0, r1.getWidth(), 1e-9);
    assertEquals(2.5, r2.getWidth(), 1e-9);
  }

  @Test
  public void testGetHeight() {
    assertEquals(4.0, r1.getHeight(), 1e-9);
    assertEquals(1.5, r2.getHeight(), 1e-9);
  }

  @Test
  public void testArea() {
    assertEquals(12.0, r1.area(), 1e-9);
    assertEquals(3.75, r2.area(), 1e-9);
  }

  @Test
  public void testPerimeter() {
    assertEquals(14.0, r1.perimeter(), 1e-9);
    assertEquals(8.0, r2.perimeter(), 1e-9); // 2*(2.5+1.5)=8
  }

  @Test
  public void testDistanceFromOrigin() {
    // r1 corner (0,0) -> distance 0
    assertEquals(0.0, r1.distanceFromOrigin(), 1e-9);

    // r2 corner (1,2) -> distance sqrt(5)
    assertEquals(Math.sqrt(5.0), r2.distanceFromOrigin(), 1e-9);
  }

  @Test
  public void testToString() {
    // These expected strings assume:
    // Point2D.toString()       => "(%.2f, %.2f)"
    // Rectangle.toString()     => "Rectangle: corner=%s, w=%.2f, h=%.2f"
    assertEquals("Rectangle: corner=(0.00, 0.00), w=3.00, h=4.00", r1.toString());
    assertEquals("Rectangle: corner=(1.00, 2.00), w=2.50, h=1.50", r2.toString());
  }

  @Test
  public void testCompareTo() {
    // Reference points (corners):
    // near: (0,0)  distance = 0
    // far:  (3,4)  distance = 5
    // tie:  (0,0)  distance = 0 (same as near)
    Shape near = new Rectangle(0, 0, 3.0, 4.0);
    Shape far  = new Rectangle(3, 4, 1.0, 1.0);
    Shape tie  = new Rectangle(0, 0, 2.0, 2.0);

    assertTrue(near.compareTo(far) < 0);   // smaller
    assertTrue(far.compareTo(near) > 0);   // bigger
    assertEquals(0, near.compareTo(tie));  // tie
  }
}
