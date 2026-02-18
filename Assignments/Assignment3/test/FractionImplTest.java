import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for FractionImpl
 */
public class FractionImplTest {

  // test values
  private Fraction half;
  private Fraction third;
  private Fraction negHalf;
  private Fraction zero;

  /**
   * Creates common Fraction instances before each test.
   */
  @BeforeEach
  public void setUp() {
    half = new FractionImpl(1, 2);
    third = new FractionImpl(1, 3);
    negHalf = new FractionImpl(-1, 2);
    zero = new FractionImpl(0, 5); // should normalize to 0/1
  }

  // ***********************Constructor tests***********************

  /**
   * Denominator 0 is undefined.
   * The constructor should throw IllegalArgumentException.
   */
  @Test
  public void testConstructorRejectsZeroDenominator() {
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(1, 0));
  }

  /**
   * Denominators must be positive
   * Passing a negative denominator should throw IllegalArgumentException.
   */
  @Test
  public void testConstructorRejectsNegativeDenominator() {
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(1, -2));
  }

  /**
   * constructor doesn't throw for regular inputs
   */
  @Test
  public void testConstructorDoesNotThrowForValidInputs() {
    assertDoesNotThrow(() -> new FractionImpl(1, 2));
    assertDoesNotThrow(() -> new FractionImpl(-1, 2));
    assertDoesNotThrow(() -> new FractionImpl(0, 5)); // valid; should normalize to 0/1
  }

  /**
   * The fraction should be stored in the simplest form.
   * 4/2 simplifies to 2/1.
   */
  @Test
  public void testConstructorSimplifies() {
    Fraction f = new FractionImpl(4, 2);
    assertEquals("2 / 1", f.toString());
  }

  /**
   * Negative values must be represented by a negative numerator with a positive denominator.
   */
  @Test
  public void testConstructorKeepsNegativeOnNumerator() {
    Fraction f = new FractionImpl(-2, 4); // -2/4 -> -1/2
    assertEquals("-1 / 2", f.toString());
    assertEquals(-1, f.getNumerator());
    assertEquals(2, f.getDenominator());
  }

  /**
   * Zero numerator handling: any 0/d should be 0/1.
   */
  @Test
  public void testZeroNumerator() {
    // zero = new FractionImpl(0, 5); // should normalize to 0/1
    assertEquals("0 / 1", zero.toString());
    assertEquals(0, zero.getNumerator());
    assertEquals(1, zero.getDenominator());
  }

  // ***********************Getter / Setter tests***********************

  /**
   * getter should grab numerator and denominator.
   */
  @Test
  public void testGetters() {
    // half = new FractionImpl(1, 2);
    assertEquals(1, half.getNumerator());
    assertEquals(2, half.getDenominator());

    //  negHalf = new FractionImpl(-1, 2);
    assertEquals(-1, negHalf.getNumerator());
    assertEquals(2, negHalf.getDenominator());

    //  zero = new FractionImpl(0, 5); // should normalize to 0/1
    assertEquals(0, zero.getNumerator());
    assertEquals(1, zero.getDenominator());
  }

  /**
   * Setting the numerator
   * the normalization will happen
   * especially for the important special case numerator == 0.
   */
  @Test
  public void testSetNumeratorRenormalizes() {
    FractionImpl f = new FractionImpl(2, 4); // should normalize to 1/2
    assertEquals("1 / 2", f.toString());

    f.setNumerator(0);
    assertEquals("0 / 1", f.toString());
  }

  /**
   * Setting denominator to 0 or a negative value must throw IllegalArgumentException.
   * after a failed attempt, the original object remains valid.
   */
  @Test
  public void testSetDenominatorRejectsNonPositive() {
    FractionImpl f = new FractionImpl(1, 2);

    assertThrows(IllegalArgumentException.class, () -> f.setDenominator(0));
    assertThrows(IllegalArgumentException.class, () -> f.setDenominator(-5));

    // Invariant preserved: existing value will not be modified.
    assertEquals("1 / 2", f.toString());
  }

  /**
   * Positive denominators will not trigger exception
   */
  @Test
  public void testSetDenominatorDoesNotThrowForPositive() {
    FractionImpl f = new FractionImpl(1, 2);
    assertDoesNotThrow(() -> f.setDenominator(7));
    assertEquals("1 / 7", f.toString());
  }

  /**
   * Negative or 0 numerators will not trigger exception
   */
  @Test
  public void testSetNumeratorDoesNotThrow() {
    // negative
    FractionImpl f = new FractionImpl(1, 2);
    assertDoesNotThrow(() -> f.setNumerator(-3));
    assertEquals("-3 / 2", f.toString());

    // 0
    FractionImpl f2 = new FractionImpl(1, 2);
    assertDoesNotThrow(() -> f.setNumerator(0));
    assertEquals("0 / 1", f.toString());
  }

  /**
   * Setting a new valid denominator should update the value and re-normalize the fraction.
   */
  @Test
  public void testSetDenominatorRenormalizes() {
    FractionImpl f = new FractionImpl(2, 3);
    f.setDenominator(6); // 2/3 -> 2/6 -> 1/3
    assertEquals("1 / 3", f.toString());

  }

  // *********************** toString tests ***********************

  /**
   * Verifies that toString() always prints the fraction in simplest terms.
   * 4/2 -> 2/1 and 6/15 -> 2/5.
   */
  @Test
  public void testToStringSimplification() {
    // 4/2 should reduce to 2/1
    Fraction f = new FractionImpl(4, 2);
    assertEquals("2 / 1", f.toString());

    // 6/15 -> 2/5
    Fraction g = new FractionImpl(6, 15);
    assertEquals("2 / 5", g.toString());
  }

  /**
   * Verifies sign formatting rules for fractions.
   * denominator stays positive, neg sign will be carried by
   * the numerator.
   */
  @Test
  public void testToStringSignFormatting() {
    // Negative should be on numerator, denominator stays positive
    Fraction f = new FractionImpl(-1, 2);
    assertEquals("-1 / 2", f.toString());
    assertEquals(-1, f.getNumerator());
    assertEquals(2, f.getDenominator());

    // Zero should be formatted (0/1)
    Fraction z = new FractionImpl(0, 7);
    assertEquals("0 / 1", z.toString());
    assertEquals(0, z.getNumerator());
    assertEquals(1, z.getDenominator());
  }

  // *********************** gcd() tests ***********************

  /**
   * Verifies gcd() correctness through normalization/simplification.
   * gcd() is typically a private helper, do not call it directly.
   */
  @Test
  public void testGcdCorrectnessViaNormalizationNegativesAndZero() {
    // -2/4 should reduce to -1/2 (gcd(|-2|,4)=2).
    Fraction f = new FractionImpl(-2, 4);
    assertEquals("-1 / 2", f.toString());

    // gcd/normalization with zero numerator: 0/5 should to 0/1.
    // This ensures the implementation handles the "zero numerator" edge case safely.
    Fraction z = new FractionImpl(0, 5);
    assertEquals("0 / 1", z.toString());

    // 12/18 should reduce to 2/3 (gcd = 6).
    Fraction h = new FractionImpl(12, 18);
    assertEquals("2 / 3", h.toString());
  }

  // *********************** toDouble tests ***********************

  /**
   * toDouble() should return correct decimal values for typical and negative cases.
   * a small delta used for floating-point representation.
   */
  @Test
  public void testToDouble() {

    // positive
    assertEquals(0.5, half.toDouble(), 1e-9);

    // negative
    assertEquals(-0.5, negHalf.toDouble(), 1e-9);

    // 0
    Fraction f = new FractionImpl(0, 7);
    assertEquals(0.0, f.toDouble(), 1e-9);
  }

  // *********************** reciprocal tests ***********************

  /**
   * Reciprocal should flip numerator/denominator and return a new Fraction.
   */
  @Test
  public void testReciprocalNormal() {
    Fraction r = half.reciprocal(); // (1/2) -> (2/1)
    assertEquals("2 / 1", r.toString());
  }

  /**
   * Reciprocal must preserve sign correctly.
   * Example: (-1/2) -> (-2/1).
   */
  @Test
  public void testReciprocalKeepsSignCorrect() {
    Fraction r = negHalf.reciprocal();
    assertEquals("-2 / 1", r.toString());
  }

  /**
   * Reciprocal of zero is undefined
   * so it must throw IllegalArgumentException.
   */
  @Test
  public void testReciprocalThrowsOnlyForZero() {
    assertThrows(IllegalArgumentException.class, () -> zero.reciprocal());

    Fraction zero2 = new FractionImpl(0, 9);
    assertThrows(IllegalArgumentException.class, () -> zero2.reciprocal());

    // nonZero will not trigger exception
    Fraction nonZero = new FractionImpl(-1, 2);
    assertDoesNotThrow(() -> nonZero.reciprocal());
    assertEquals("-2 / 1", nonZero.reciprocal().toString());
  }

  // *********************** add tests ***********************

  /**
   * Adds fractions with the same denominator.
   */
  @Test
  public void testAddSameDenominator() {
    Fraction a = new FractionImpl(1, 5);
    Fraction b = new FractionImpl(4, 5);
    assertEquals("1 / 1", a.add(b).toString());
  }

  /**
   * Adds fractions with different denominators and checks simplification.
   *  1/2 + 1/3 = 5/6
   *  1/6 + 2/6 = 1/2
   */
  @Test
  public void testAddDifferentDenominators() {
    assertEquals("5 / 6", half.add(third).toString());

    Fraction a = new FractionImpl(1, 6);
    Fraction b = new FractionImpl(2, 6);
    assertEquals("1 / 2", a.add(b).toString());
  }

  /**
   * Adds fractions with negatives and confirms sign correctness and simplification.
   * Example: -1/2 + 1/3 = -1/6.
   */
  @Test
  public void testAddWithNegatives() {
    assertEquals("-1 / 6", negHalf.add(third).toString());
  }

  /**
   * Adding zero should return the original value
   */
  @Test
  public void testAddWithZero() {
    assertEquals("1 / 2", half.add(zero).toString());
    assertEquals("1 / 2", zero.add(half).toString());
  }

  /**
   * Passing null to add() is an invalid argument and should throw.
   */
  @Test
  public void testAddNullThrowsForNullOnly() {
    assertThrows(IllegalArgumentException.class, () -> half.add(null));

    Fraction a = new FractionImpl(1, 2);
    assertThrows(IllegalArgumentException.class, () -> a.add(null));

    Fraction b = new FractionImpl(1, 3);
    assertDoesNotThrow(() -> a.add(b)); // doesn't throw for regular adding
    assertEquals("5 / 6", a.add(b).toString());
  }

  // *********************** compareTo tests ***********************

  /**
   * Fractions with equal numeric value should return 0
   * even if constructed differently (e.g., 1/2 vs 2/4).
   */
  @Test
  public void testCompareToEqualValues() {
    Fraction a = new FractionImpl(1, 2);
    Fraction b = new FractionImpl(2, 4);
    assertEquals(0, a.compareTo(b));
  }

  /**
   * Verifies less-than and greater-than ordering.
   */
  @Test
  public void testCompareToLessGreater() {
    // 1/3 < 1/2
    assertTrue(third.compareTo(half) < 0);
    assertTrue(half.compareTo(third) > 0);
  }

  /**
   * Cross-sign compare: negative values < 0; negative < positive
   */
  @Test
  public void testCompareToCrossSign() {
    assertTrue(negHalf.compareTo(zero) < 0);
    assertTrue(zero.compareTo(negHalf) > 0);
    assertTrue(negHalf.compareTo(half) < 0);
    assertTrue(half.compareTo(negHalf) > 0);
    assertTrue(half.compareTo(zero) > 0);
    assertTrue(zero.compareTo(half) < 0);
  }

  /**
   * Passing compareTo() should throw null only
   */
  @Test
  public void testCompareToThrowsForNullOnly() {
    assertThrows(IllegalArgumentException.class, () -> half.compareTo(null));

    Fraction a = new FractionImpl(1, 2);
    assertThrows(IllegalArgumentException.class, () -> a.compareTo(null));

    Fraction b = new FractionImpl(2, 4);
    assertDoesNotThrow(() -> a.compareTo(b)); // normal case, no throw
    assertEquals(0, a.compareTo(b));
  }

  /**
   * Large Value should avoid overflow
   */
  @Test
  public void testCompareToLargeValuesAvoidsOverflow() {
    // values that are valid ints but whose cross-products exceed int range.
    // a = 2,000,000,000 / 1  (very large)
    // b = 1 / 2,000,000,000  (very small)
    //
    // compareTo uses cross-multiplication:
    // this = a.num * b.den -> overflows int, fits long
    // other = b.num * a.den = 1 * 1 = 1
    // So a > b and compareTo should be positive.

    Fraction a = new FractionImpl(2_000_000_000, 1);
    Fraction b = new FractionImpl(1, 2_000_000_000);

    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }

}
