/**
 * Creates implementation of Fraction with an integer numerator and
 * a positive integer denominator.
 */
public class FractionImpl implements Fraction {

  private int numerator;
  private int denominator;

  /**
   * Constructs a fraction with the given numerator and denominator.
   *
   * @param numerator the numerator
   * @param denominator the denominator (must be positive)
   * @throws IllegalArgumentException if denominator is less than or equal to 0
   */
  public FractionImpl(int numerator, int denominator) {
    /*
    helper method validateAndNormalize will
    1. validate denominator being positive;
    2. normalize 0 numerator to 0/1;
    3. simplify fractions into the simplest form;
    4. set numerator and denominator;
    */
    validateAndNormalize(numerator, denominator);
  }

  /**
   * Returns the numerator of this fraction.
   *
   * @return the numerator (can be negative, zero, or positive)
   */
  @Override
  public int getNumerator() {
    return numerator;
  }

  /**
   * Returns the denominator of this fraction.
   *
   * @return the denominator (always positive by class invariant)
   */
  @Override
  public int getDenominator() {
    return denominator;
  }


  /**
   * Updates the numerator and normalize the fraction
   *
   * @param n the new numerator
   */
  @Override
  public void setNumerator(int n) {
    // Normalize by calling helper method: validateAndNormalize
    validateAndNormalize(n, this.denominator);
  }

  /**
   * Updates the denominator and normalize the fraction
   *
   * @param d the new denominator (must be positive)
   * @throws IllegalArgumentException if d <= 0
   */
  @Override
  public void setDenominator(int d) {
    validateAndNormalize(this.numerator, d);
  }

  /**
   * Returns the decimal (double) value of this fraction.
   *
   * @return the numeric value as a double
   */
  @Override
  public double toDouble() {
    // Cast to double
    return ((double) numerator) / (double) denominator;
  }

  /**
   * Returns the reciprocal of this fraction.
   *
   * @return a new Fraction equal to 1 / (this fraction)
   * @throws IllegalArgumentException if this fraction is 0 (undefined)
   */
  @Override
  public Fraction reciprocal() {
    if (numerator == 0) {
      throw new IllegalArgumentException("Undefined: cannot take reciprocal of 0.");
    }

    // Reciprocal of n/d is d/n, but denominator must be positive.
    int newNum = this.denominator;
    int newDen = this.numerator;

    // If numerator is negative, move the sign to the new numerator
    if (newDen < 0) {
      newNum = -newNum;
      newDen = -newDen;
    }
    return new FractionImpl(newNum, newDen);
  }

  /**
   * Adds this fraction to another fraction and returns the sum as a new Fraction.
   *
   * @param other the fraction to add
   * @return a new Fraction representing the sum of this + other
   * @throws IllegalArgumentException if other is null
   */
  @Override
  public Fraction add(Fraction other) {
    // handles null other
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }

    // a/b + c/d = (ad + bc) / bd
    // Use long to reduce overflow risk for multiplication (safer than int)
    long a = this.numerator;
    long b = this.denominator;
    long c = other.getNumerator();
    long d = other.getDenominator();

    long newNum = a * d + c * b;
    long newDen = b * d;

    // Reduce before casting to int (may shrink values a lot)
    long g = gcdLong(Math.abs(newNum), newDen);
    newNum /= g;
    newDen /= g;

    // FractionImpl takes ints, must cast back to int.
    // range check before casting
    if (newNum < Integer.MIN_VALUE || newNum > Integer.MAX_VALUE
        || newDen > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Result out of int range.");
    }

    return new FractionImpl((int) newNum, (int) newDen);
  }
  /**
   * Compares this fraction to the other fraction
   *
   * @param other the fraction to compare against
   * @return negative if this < other, 0 if equal, positive if this > other
   * @throws IllegalArgumentException if other is null
   */
  @Override
  public int compareTo(Fraction other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }

    // Compare a/b and c/d by cross-multiplying: a*d vs c*b
    long longThis = (long) this.numerator * (long) other.getDenominator();
    long longOther = (long) other.getNumerator() * (long) this.denominator;

    return Long.compare(longThis, longOther);
  }

  /**
   * Returns a simplified string "n/d" in the simplest form
   *
   * @return simplified fraction string
   */
  @Override
  public String toString() {
    return numerator + " / " + denominator;
  }

  // Private Helper Methods

  /**
   * Validates the input numerator/denominator pair and stores the normalized
   * and simplified form
   * <p>
   *   Denominator is always positive (else IllegalArgumentException)
   *   Zero is stored as 0/1
   *   The fraction is always in simplified form
   *   Numerator and denominator will be set (private fields updated)
   * <p>
   *  This is a centralizing validation helper that avoids duplicating code.
   *
   * @param n the proposed numerator
   * @param d the proposed denominator (must be positive)
   * @throws IllegalArgumentException if d is less than or equal to 0
   */

  private void validateAndNormalize(int n, int d) {

    // Validates that the denominator input is legal (positive)
    validateDenominator(d); // the other helper method is called

    // set 0 to be 0/1 (simple for toString and toCompare)
    if (n == 0) {
      this.numerator = 0;
      this.denominator = 1;
      return;
    }

    int g = gcd(Math.abs(n), d);
    n /= g;
    d /= g;

    this.numerator = n;
    this.denominator = d;
  }

  /**
   * Validates that the denominator input is legal (positive)
   *
   * @param d the proposed denominator
   * @throws IllegalArgumentException if d is less than or equal to 0
   */
  private void validateDenominator(int d) {
    if (d <= 0) {
      throw new IllegalArgumentException("Denominator must be positive.");
    }
  }

  /**
   * Finds the greatest common divisor (gcd) of two non-negative integers
   * using Euclid's algorithm.
   * <p>
   * gcd(a, b) == gcd(b, a % b).
   * Repeatedly replacing (a, b) with (b, a % b)
   * until b becomes 0.
   * The remaining value is the gcd.
   * <p>
   * Precondition: a >= 0 and b >= 0.
   *
   * @param a a non-negative integer
   * @param b a non-negative integer
   * @return the greatest common divisor of a and b
   */
  private static int gcd(int a, int b) {
    // Euclid's algorithm
    while (b != 0) {
      int r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  /**
   * Finds the greatest common divisor (gcd) of two non-negative longs
   * using Euclid's algorithm.
   * <p>
   * gcd(a, b) == gcd(b, a % b).
   * Repeatedly replacing (a, b) with (b, a % b)
   * until b becomes 0.
   * The remaining value is the gcd.
   * <p>
   * Precondition: a >= 0 and b >= 0.
   *
   * @param a a non-negative long
   * @param b a non-negative long
   * @return the greatest common divisor of a and b
   */
  private static long gcdLong(long a, long b) {
    // take
    while (b != 0) {
      long r = a % b;
      a = b;
      b = r;
    }
    return a;
  }
}
