import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

  private Triangle t345;      // (0,0) (3,0) (0,4) -> area 6, perimeter 12
  private Triangle tSameLine; // (0,0) (1,1) (2,2) -> area 0
  private Triangle tShifted;  // reference point shifted


  @BeforeEach
  public void setUp() {
    t345 = new Triangle(0, 0, 3, 0, 0, 4);
    tSameLine = new Triangle(0, 0, 1, 1, 2, 2);
    tShifted = new Triangle(3, 4, 4, 4, 3, 5); // reference point (3,4)
  }


// Constructor
  @Test
  public void testConstructorWith6Doubles() {
    Triangle t = new Triangle(0, 0, 3, 0, 0, 4);

    assertEquals(12.0, t.perimeter(), 1e-9);
    assertEquals(6.0, t.area(), 1e-9);
  }

  @Test
  public void testConstructorWithThreePoints() {
    Point2D p1 = new Point2D(0, 0);
    Point2D p2 = new Point2D(3, 0);
    Point2D p3 = new Point2D(0, 4);

    Triangle t = new Triangle(p1, p2, p3);
    assertEquals(12.0, t.perimeter(), 1e-9);
    assertEquals(6.0, t.area(), 1e-9);
  }

  @Test
  public void testConstructorRejectsDuplicatePoints() {
    // p1 == p2
    assertThrows(IllegalArgumentException.class,
        () -> new Triangle(0, 0, 0, 0, 1, 1));

    // p2 == p3
    assertThrows(IllegalArgumentException.class,
        () -> new Triangle(0, 0, 2, 2, 2, 2));
  }

  @Test
  public void testConstructorAllowsColinearPoints() {
    // flat triangle: area should be 0
    assertDoesNotThrow(() -> new Triangle(0, 0, 1, 1, 2, 2));

    Triangle t = new Triangle(0, 0, 1, 1, 2, 2);
    assertEquals(0.0, t.area(), 1e-9);
  }

  // test Perimeter
  @Test
  public void testPerimeter() {
    // Triangle 1: (0,0), (3,0), (0,4) -> 3 + 4 + 5 = 12
    assertEquals(12.0, t345.perimeter(), 1e-9);

    // Triangle 2: colinear (0,0), (1,1), (2,2)
    // perimeter = sqrt(2) + sqrt(2) + 2 = 2 + 2*sqrt(2)
    double expectedColinear = Math.sqrt(2.0) + Math.sqrt(2.0) + Math.sqrt(8.0);
    assertEquals(expectedColinear, tSameLine.perimeter(), 1e-9);

    // Triangle 3: shifted triangle (3,4), (4,4), (3,5)
    // This is a 1-1-sqrt(2) right triangle -> perimeter = 2 + sqrt(2)
    double expectedShifted = 2.0 + Math.sqrt(2.0);
    assertEquals(expectedShifted, tShifted.perimeter(), 1e-9);
  }

  @Test
  public void testArea() {
    // Triangle 1: (0,0), (3,0), (0,4) -> area = 6
    assertEquals(6.0, t345.area(), 1e-9);

    // Triangle 2: colinear (0,0), (1,1), (2,2) -> area = 0
    assertEquals(0.0, tSameLine.area(), 1e-9);

    // Triangle 3: (3,4), (4,4), (3,5) -> right triangle with legs 1 and 1 -> area = 0.5
    assertEquals(0.5, tShifted.area(), 1e-9);
  }

  @Test
  public void testDistanceFromOrigin() {
    // t345 reference point is (0,0) -> distance 0
    assertEquals(0.0, t345.distanceFromOrigin(), 1e-9);

    // tShifted reference point is (3,4) -> distance 5
    assertEquals(5.0, tShifted.distanceFromOrigin(), 1e-9);
  }

  @Test
  public void testToString() {
    // Triangle 1
    assertEquals(
        "Triangle: (0.00, 0.00), (3.00, 0.00), (0.00, 4.00)",
        t345.toString()
    );
    // Triangle 2
    assertEquals(
        "Triangle: (0.00, 0.00), (1.00, 1.00), (2.00, 2.00)",
        tSameLine.toString()
    );
    // Triangle 3
    assertEquals(
        "Triangle: (3.00, 4.00), (4.00, 4.00), (3.00, 5.00)",
        tShifted.toString()
    );
  }

  @Test
  public void testCompareTo() {
    // Reference distances:
    // t345:      ref (0,0)  -> distance 0
    // tSameLine: ref (0,0)  -> distance 0
    // tShifted:  ref (3,4)  -> distance 5

    // t345 and tSameLine have the same reference point, 0
    assertEquals(0, t345.compareTo(tSameLine));

    // t345 (0) is closer than tShifted (5), so should be "smaller"
    assertTrue(t345.compareTo(tShifted) < 0);

    // tShifted (5) is farther than tSameLine (0), so should be "bigger"
    assertTrue(tShifted.compareTo(tSameLine) > 0);
  }
}

