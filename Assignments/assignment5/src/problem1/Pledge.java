package problem1;

import java.time.LocalDateTime;

/**
 * Represents a pledge donation.
 *
 * A pledge is a promise to donate an amount in the future.
 * The processing date/time is different from the creation date/time and can be:
 *   - set later
 *   - modified later
 *   - removed later (set to null)
 *
 * The processing date/time cannot be earlier than the creation date/time.
 */
public class Pledge extends Donation {
  private LocalDateTime processingDate;

  /**
   * Constructs a pledge with no processing date.
   *
   * @param amount the pledged amount
   * @param createdAt the creation date/time
   */
  public Pledge(double amount, LocalDateTime createdAt) {
    super(amount, createdAt);
    this.processingDate = null;
  }

  /**
   * Constructs a pledge with an initial processing date.
   *
   * @param amount the pledged amount
   * @param createdAt the creation date/time
   * @param processingDate the processing date/time
   * @throws IllegalArgumentException if processingDate is before createdAt
   */
  public Pledge(double amount, LocalDateTime createdAt, LocalDateTime processingDate) {
    super(amount, createdAt);
    setProcessingDate(processingDate);
  }

  /**
   * Returns the processing date/time.
   *
   * @return the processing date/time or null
   */
  public LocalDateTime getProcessingDate() {
    return processingDate;
  }

  /**
   * Sets or changes the processing date/time.
   *
   * The processing date/time can also be removed (set to null).
   *
   * @param processingDate the new processing date/time, or null
   * @throws IllegalArgumentException if processingDate is before the creation date/time
   */
  public void setProcessingDate(LocalDateTime processingDate) {
    if (processingDate != null && processingDate.isBefore(getCreatedAt())) {
      throw new IllegalArgumentException(
          "Processing date/time cannot be before creation date/time.");
    }
    this.processingDate = processingDate;
  }

  /**
   * Removes the processing date/time from this pledge.
   */
  public void removeProcessingDate() {
    this.processingDate = null;
  }

  /**
   * Returns this pledge's contribution for the given year.
   *
   * The pledged amount only counts in the year in which it is processed.
   * If no processing date, this pledge contributes 0.
   *
   * @param year the year to evaluate
   * @return the pledged amount if processed in the specified year; otherwise 0
   */
  @Override
  public double getTotalForYear(int year) {
    if (processingDate == null) {
      return 0.0;
    }
    return processingDate.getYear() == year ? getAmount() : 0.0;
  }
}