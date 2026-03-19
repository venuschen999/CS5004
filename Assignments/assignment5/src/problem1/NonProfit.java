package problem1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a non-profit organization that tracks donations.
 * A NonProfit has below fields:
 *   - a name
 *   - a collection of donations
 *
 * This class stores donations using the parent type, Donation, and each specific donation
 * subclass knows how to compute its own yearly total by own method.
 * The design allows new donation types to be added in the future without changing
 * this class's calculation logic.
 */
public class NonProfit {
  private final String name;
  private final List<Donation> donations;

  /**
   * Constructs a non-profit with the given name and an empty donation list.
   *
   * @param name the organization name
   * @throws IllegalArgumentException if name is null/blank
   */
  public NonProfit(String name) {
    this(name, new ArrayList<>());
  }

  /**
   * Constructs a non-profit with the given name and initial donations.
   *
   * @param name the organization name
   * @param donations the initial collection of donations
   * @throws IllegalArgumentException if name is null or blank
   * @throws IllegalArgumentException if donations is null
   */
  public NonProfit(String name, List<Donation> donations) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank.");
    }
    if (donations == null) {
      throw new IllegalArgumentException("Donations list cannot be null.");
    }

    this.name = name;
    this.donations = new ArrayList<>(donations);
  }

  /**
   * Returns the organization name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns an unmodifiable view of the donations list.
   * This design protects the list to be modified by outside code.
   *
   * @return the donation list (view)
   */
  public List<Donation> getDonations() {
    return Collections.unmodifiableList(donations);
  }

  /**
   * Adds a donation to this non-profit.
   *
   * @param donation the donation to add
   * @throws IllegalArgumentException if donation is null
   */
  public void addDonation(Donation donation) {
    if (donation == null) {
      throw new IllegalArgumentException("Donation cannot be null.");
    }
    donations.add(donation);
  }

  /**
   * Removes a donation from this non-profit.
   *
   * @param donation the donation to remove
   * @return true if the donation was removed; false otherwise
   */
  public boolean removeDonation(Donation donation) {
    return donations.remove(donation);
  }

  /**
   * Returns the total donations processed in the specified year.
   * Each donation subclass determines its own contribution for the requested year.
   *
   * @param year the year to evaluate
   * @return the total donations for that year
   */
  public double getTotalDonationsForYear(int year) {
    double total = 0.0;

    for (Donation donation : donations) {
      total += donation.getTotalForYear(year);
    }

    return total;
  }
}