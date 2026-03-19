package problem1;

import java.time.LocalDateTime;

/**
 * Represents a general donation made to a non-profit.
 *
 * All donations have:
 *   an amount
 *   a creation date/time (LocalDateTime object)
 *
 * This class is abstract because we can not create a generic Donation directly.
 * Instead, we create specific type of donations such as OneTimeDonation,
 * MonthlyDonation, and Pledge.
 */
public abstract class Donation {
  private final double amount;
  private final LocalDateTime createdAt;

  /**
   * Constructs a donation with the given amount and creation date/time.
   *
   * @param amount the donation amount
   * @param createdAt the date/time the donation was created
   * @throws IllegalArgumentException if amount is negative
   * @throws IllegalArgumentException if createdAt is null
   */
  public Donation(double amount, LocalDateTime createdAt) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative.");
    }
    if (createdAt == null) {
      throw new IllegalArgumentException("Creation date/time cannot be null.");
    }

    this.amount = amount;
    this.createdAt = createdAt;
  }

  /**
   * Returns the donation amount.
   *
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Returns the creation date/time of the donation.
   *
   * @return the creation date/time e.g. 2026-03-10T14:30
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * Returns how much this donation contributes to the given year.
   *
   * Each subclass implements its own method logic:
   *   One-time donation: amount counts only in creation year
   *   Monthly donation: amount counts once per monthly occurrence in that year
   *   Pledge: amount counts only in processing year, if processed
   *
   * @param year the year to consider
   * @return the amount contributed by this donation in that year
   */
  public abstract double getTotalForYear(int year);
}