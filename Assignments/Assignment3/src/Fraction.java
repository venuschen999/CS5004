/**
 * a protocol for fractions
 * includes an integer numerator and a positive integer denominator.
 */
public interface Fraction extends Comparable<Fraction> {

  /**
   * Returns the numerator of this fraction.
   *
   * @return the numerator (can be +, -, or 0)
   */
  int getNumerator();

  /**
   * Returns the denominator of this fraction.
   *
   * @return the denominator (must be positive)
   */
  int getDenominator();

  /**
   * Sets the numerator of this fraction.
   *
   * @param n the new numerator
   */
  void setNumerator(int n);

  /**
   * Sets the denominator of this fraction.
   *
   * @param d the new denominator (must be positive)
   * @throws IllegalArgumentException if d <= 0
   */
  void setDenominator(int d);

  /**
   * Returns the decimal (double) value of this fraction.
   *
   * @return the value as a double
   */
  double toDouble();

  /**
   * Returns the reciprocal of this fraction (1 / this).
   *
   * @return a new Fraction equal to the reciprocal of this fraction
   * @throws IllegalArgumentException if this fraction's numerator is 0
   */
  Fraction reciprocal();

  /**
   * Adds this fraction to another fraction.
   *
   * @param other the other fraction to add
   * @return a new Fraction representing the sum
   * @throws IllegalArgumentException if other is null
   */
  Fraction add(Fraction other);

  /**
   * Compares this fraction to another fraction.
   *
   * @param other the other fraction
   * @return negative if this < other, 0 if equal, positive if this > other
   * @throws IllegalArgumentException if other is null
   */
  @Override
  int compareTo(Fraction other);
}
