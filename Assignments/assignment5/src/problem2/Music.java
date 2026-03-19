package problem2;

/**
 * Represents a music item.
 *
 * Music can have either:
 *   - a RecordingArtist as its creator
 *   - a Band as its creator
 */
public class Music extends Item {

  /**
   * Constructs a music item with a recording artist as creator.
   *
   * @param artist the recording artist
   * @param title the title
   * @param year the release year
   */
  public Music(RecordingArtist artist, String title, int year) {
    super(artist, title, year);
  }

  /**
   * Constructs a music item with a band as creator.
   *
   * @param band the band
   * @param title the title
   * @param year the release year
   */
  public Music(Band band, String title, int year) {
    super(band, title, year);
  }

  /**
   * Returns the creator of this music item.
   *
   * @return the creator
   */
  public Creator getMusicCreator() {
    return getCreator();
  }

  /**
   * Sets the creator of this music item to a recording artist.
   *
   * @param artist the new recording artist
   */
  public void setMusicCreator(RecordingArtist artist) {
    setCreator(artist);
  }

  /**
   * Sets the creator of this music item to a band.
   *
   * @param band the new band
   */
  public void setMusicCreator(Band band) {
    setCreator(band);
  }
}