package problem1;

import java.time.LocalDateTime;

/**
 * Represents a monthly recurring donation.
 * It repeats once a month, starting at its creation date/time,
 * until it is canceled.
 *
 * The cancellation date/time is optional at first, but can later be set.
 */
public class MonthlyDonation extends Donation {
  private LocalDateTime canceledAt; //can be set later

  /**
   * Constructs a monthly donation with no cancellation date/time.
   *
   * @param amount the monthly donation amount
   * @param createdAt the creation date/time
   */
  public MonthlyDonation(double amount, LocalDateTime createdAt) {
    super(amount, createdAt);
    this.canceledAt = null;
  }

  /**
   * Returns the cancellation date/time, or null if not canceled.
   *
   * @return the cancellation date/time or null
   */
  public LocalDateTime getCanceledAt() {
    return canceledAt;
  }

  /**
   * Sets the cancellation date/time for this monthly donation.
   *
   * The time cannot be earlier than the creation date/time.
   *
   * @param canceledAt the provided cancellation date/time
   * @throws IllegalArgumentException if canceledAt is before the creation date/time
   */
  public void setCanceledAt(LocalDateTime canceledAt) {
    if (canceledAt != null && canceledAt.isBefore(getCreatedAt())) {
      throw new IllegalArgumentException(
          "Cancellation date/time cannot be before creation date/time.");
    }
    this.canceledAt = canceledAt;
  }

  /**
   * Returns this donation's total contribution amount for the given year.
   *
   * This method counts every monthly payment that happens in the provided year,
   * starting from the creation date/time and ending at the cancellation date/time
   * if one exists.
   *
   * @param year the year to total
   * @return the total amount contributed in that year
   */
  @Override
  public double getTotalForYear(int year) {
    double total = 0.0; // set up base amount
    LocalDateTime occurrence = getCreatedAt();

    // keep checking monthly occurrences until passing the provided year
    while (occurrence.getYear() <= year) {

      // if there is a cancellation date and this occurrence happens after it,
      // stop as it should not be counted
      if (canceledAt != null && occurrence.isAfter(canceledAt)) {
        break;
      }
      // if this monthly occurrence happens in the requested year,
      // add one month's amount
      if (occurrence.getYear() == year) {
        total += getAmount();
      }
      // move to the next monthly occurrence
      occurrence = occurrence.plusMonths(1);
    }

    return total;
  }
}