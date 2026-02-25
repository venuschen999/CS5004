package problem1;

import java.time.Year;
import java.util.Arrays;

/**
 * Artist: the abstract base class for all artist types.
 *
 * Design decisions:
 * - birthYear is stored as the raw data
 * - age is computed from birthYear and kept in sync
 * - all fields are mutable via setters (except awards which uses receiveAward())
 * - no setAge() — age is always derived from birthYear, never set directly
 */
public abstract class Artist implements Awardable {

  private Name     name;
  private int      birthYear;
  private int      age;        // always kept in sync with birthYear
  private String[] genres;
  private String[] awards;

  // -------------------------------------------------------------------------
  // Constructor
  // -------------------------------------------------------------------------

  public Artist(Name name, int birthYear, String[] genres, String[] awards) {
    if (name == null) throw new IllegalArgumentException("Name cannot be null");

    int currentYear = Year.now().getValue();
    if (birthYear > currentYear || birthYear < currentYear - 128) {
      throw new IllegalArgumentException("Invalid birth year: " + birthYear);
    }

    this.name      = name;
    this.birthYear = birthYear;
    this.age       = currentYear - birthYear;  // computed at construction
    this.genres    = genres != null ? genres : new String[0];
    this.awards    = awards != null ? awards : new String[0];
  }

  // -------------------------------------------------------------------------
  // Awardable implementation
  // -------------------------------------------------------------------------

  /**
   * Adds a new award by resizing the awards array.
   * No setAwards() exists — awards only ever grow via this method.
   */
  @Override
  public void receiveAward(String award) {
    String[] newAwards = new String[awards.length + 1];
    System.arraycopy(awards, 0, newAwards, 0, awards.length);
    newAwards[awards.length] = award;
    this.awards = newAwards;
  }

  // -------------------------------------------------------------------------
  // Getters
  // -------------------------------------------------------------------------

  public Name     getName()      { return name;      }
  public int      getBirthYear() { return birthYear; }
  public int      getAge()       { return age;       }
  public String[] getGenres()    { return genres;    }
  public String[] getAwards()    { return awards;    }

  // -------------------------------------------------------------------------
  // Setters
  // -------------------------------------------------------------------------

  public void setName(Name name) {
    if (name == null) throw new IllegalArgumentException("Name cannot be null");
    this.name = name;
  }

  /**
   * Updating birthYear automatically recomputes age.
   * This is the only way age changes — no setAge() exists.
   */
  public void setBirthYear(int birthYear) {
    int currentYear = Year.now().getValue();
    if (birthYear > currentYear || birthYear < currentYear - 128) {
      throw new IllegalArgumentException("Invalid birth year: " + birthYear);
    }
    this.birthYear = birthYear;
    this.age       = currentYear - birthYear;  // keep in sync!
  }

  public void setGenres(String[] genres) {
    this.genres = genres != null ? genres : new String[0];
  }

  // -------------------------------------------------------------------------
  // toString
  // -------------------------------------------------------------------------

  @Override
  public String toString() {
    return name.getFullName() +
        "\n  Age: "        + age +
        "\n  Genres: "     + Arrays.toString(genres) +
        "\n  Awards: "     + Arrays.toString(awards);
  }
}