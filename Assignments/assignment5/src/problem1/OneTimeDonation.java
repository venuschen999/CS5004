package problem1;

import java.time.LocalDateTime;

/**
 * Represents a one-time donation.
 *
 * A one-time donation contributes its amount only in the year
 * in which it was created.
 */
public class OneTimeDonation extends Donation {

  /**
   * Constructs a one-time donation.
   *
   * @param amount the donation amount
   * @param createdAt the creation date/time
   */
  public OneTimeDonation(double amount, LocalDateTime createdAt) {
    super(amount, createdAt);
  }

  /**
   * Returns this donation's contribution to the given year.
   *
   * the amount counts only in the creation year.
   *
   * @param year the year to evaluate
   * @return the amount if the creation year provided matches
   */
  @Override
  public double getTotalForYear(int year) {
    return getCreatedAt().getYear() == year ? getAmount() : 0.0; //return 0.0 if no match
  }
}