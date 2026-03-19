package problem2;

/**
 * Represents a general item in the library catalog.
 *
 * Every item has:
 *   - a creator
 *   - a title
 *   - a release year
 */
public abstract class Item {
  private Creator creator;
  private String title;
  private int year;

  /**
   * Constructs an item with the given creator, title, and year.
   *
   * @param creator the creator
   * @param title the title
   * @param year the release/publication year
   * @throws IllegalArgumentException if creator is null
   * @throws IllegalArgumentException if title is null or blank
   */
  public Item(Creator creator, String title, int year) {
    setCreator(creator);
    setTitle(title);
    setYear(year);
  }

  /**
   * Returns the creator.
   *
   * @return the creator
   */
  public Creator getCreator() {
    return creator;
  }

  /**
   * Sets the creator.
   *
   * @param creator the new creator
   * @throws IllegalArgumentException if creator is null
   */
  protected void setCreator(Creator creator) {
    if (creator == null) {
      throw new IllegalArgumentException("Creator cannot be null.");
    }
    this.creator = creator;
  }

  /**
   * Returns the title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title.
   *
   * @param title the new title
   * @throws IllegalArgumentException if title is null or blank
   */
  public void setTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or blank.");
    }
    this.title = title;
  }

  /**
   * Returns the year.
   *
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * Sets the year.
   *
   * @param year the new year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Returns a string representation of this item.
   *
   * @return the title, creator, and year
   */
  @Override
  public String toString() {
    return title + " (" + creator + ", " + year + ")";
  }
}