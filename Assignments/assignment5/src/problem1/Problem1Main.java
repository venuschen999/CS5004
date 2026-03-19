package problem1;

import java.time.LocalDateTime;

/**
 * demo class for Problem 1.
 */
public class Problem1Main {

  /**
   * Demo how the donation system works.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {

    // Create a non-profit organization named "ABC"
    NonProfit org = new NonProfit("ABC");

    // Create a one-time donation of $100 made on March 10, 2026 at 2:30 PM
    OneTimeDonation oneTime =
        new OneTimeDonation(100.0, LocalDateTime.of(2026, 3, 10, 14, 30));

    // Create a monthly donation of $25 starting on February 15, 2026 at 5:45 PM
    // it will repeat once every month until canceled
    MonthlyDonation monthly =
        new MonthlyDonation(25.0, LocalDateTime.of(2026, 2, 15, 17, 45));

    // Create a pledge of $500 created on April 1, 2026 at 9:00 AM
    // At this moment, it is only a promise, not processed
    Pledge pledge =
        new Pledge(500.0, LocalDateTime.of(2026, 4, 1, 9, 0));

    // Set the pledge to actually be processed on January 20, 2027 at 12:00 PM
    // so pledge amount should count in 2027
    pledge.setProcessingDate(LocalDateTime.of(2027, 1, 20, 12, 0));

    // Add all three donations to the donation collection
    org.addDonation(oneTime);
    org.addDonation(monthly);
    org.addDonation(pledge);

    // Print the total donations counted in 2026
    // Expected:
    // oneTime = 100
    // monthly = 11 occurrences in 2026 (Feb through Dec) = 11 * 25 = 275
    // pledge = 0 in 2026 because it is processed in 2027
    // total = 100 + 275 + 0 = 375
    System.out.println("2026 total: " + org.getTotalDonationsForYear(2026));

    // Print the total donations counted in 2027
    // Expected:
    // oneTime = 0 in 2027
    // monthly = 12 occurrences in 2027 if not canceled = 12 * 25 = 300
    // pledge = 500 in 2027 because that is its processing year
    // total = 0 + 300 + 500 = 800
    System.out.println("2027 total: " + org.getTotalDonationsForYear(2027));

    // Cancel the monthly donation on June 20, 2026 at 8:00 AM
    // This means monthly occurrences after that date should no longer count
    monthly.setCanceledAt(LocalDateTime.of(2026, 6, 20, 8, 0));

    // Print the 2026 total again after cancellation
    // Now the monthly donation only counts for:
    // Feb 15, Mar 15, Apr 15, May 15, Jun 15 = 5 occurrences
    // so monthly = 5 * 25 = 125
    // oneTime = 100
    // pledge = 0
    // updated total = 100 + 125 + 0 = 225
    System.out.println("2026 total after cancellation update: "
        + org.getTotalDonationsForYear(2026));
  }
}