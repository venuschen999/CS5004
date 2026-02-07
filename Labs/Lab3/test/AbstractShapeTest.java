import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for shared behavior implemented in AbstractShape.
 * These tests verify:
 * - distanceFromOrigin is based on the reference point
 * - compareTo returns negative / zero / positive as required
 */
public class AbstractShapeTest {

  @Test
  public void testDistanceFromOrigin() {
    // reference at (0,0) => distance 0
    Shape near = new Circle(0, 0, 1.0);
    assertEquals(0.0, near.distanceFromOrigin(), 1e-9);

    // reference at (3,4) => distance 5
    Shape far = new Rectangle(3, 4, 2.0, 2.0);
    assertEquals(5.0, far.distanceFromOrigin(), 1e-9);
  }

  @Test
  public void testCompareTo() {
    // near and tie have same reference point distance (0)
    Shape near = new Triangle(0, 0, 1, 0, 0, 1);
    Shape tie  = new Rectangle(0, 0, 2.0, 3.0);

    // far has reference point distance 5
    Shape far  = new Circle(3, 4, 1.0);

    assertEquals(0, near.compareTo(tie));    // 0
    assertTrue(near.compareTo(far) < 0);     // negative
    assertTrue(far.compareTo(near) > 0);     // positive
  }

  @Test
  public void testCompareToRejectsNull() {
    Shape s = new Circle(0, 0, 1.0);

    assertThrows(IllegalArgumentException.class, () -> s.compareTo(null));
    assertDoesNotThrow(() -> s.compareTo(s));
  }
}
