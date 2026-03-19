package problem2;

/**
 * Represents a creator of an item in the catalog.
 *
 * A creator can be:
 *   an individual person, such as an Author or RecordingArtist
 *   a group, such as a Band
 */
public abstract class Creator {

  /**
   * Returns a string representation of this creator.
   *
   * @return the creator name
   */
  @Override
  public abstract String toString();
}