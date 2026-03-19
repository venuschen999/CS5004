package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a band.
 *
 * A band has a name and a collection of recording artist members.
 */
public class Band extends Creator {
  private String name;
  private final List<RecordingArtist> members;

  /**
   * Constructs a band with the given name and no members initially.
   *
   * @param name the band name
   * @throws IllegalArgumentException if name is null or blank
   */
  public Band(String name) {
    this(name, new ArrayList<>());
  }

  /**
   * Constructs a band with the given name and initial members.
   *
   * @param name the band name
   * @param members the initial members
   * @throws IllegalArgumentException if name is null or blank
   * @throws IllegalArgumentException if members is null
   */
  public Band(String name, List<RecordingArtist> members) {
    if (members == null) {
      throw new IllegalArgumentException("Members list cannot be null.");
    }
    this.members = new ArrayList<>(members);
    setName(name);
  }

  /**
   * Returns the band name.
   *
   * @return the band name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the band name.
   *
   * @param name the new band name
   * @throws IllegalArgumentException if name is null or blank
   */
  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Band name cannot be null or blank.");
    }
    this.name = name;
  }

  /**
   * Returns an unmodifiable view of the band members.
   *
   * @return the members list
   */
  public List<RecordingArtist> getMembers() {
    return Collections.unmodifiableList(members);
  }

  /**
   * Adds a member to the band.
   *
   * @param artist the artist to add
   * @throws IllegalArgumentException if artist is null
   */
  public void addMember(RecordingArtist artist) {
    if (artist == null) {
      throw new IllegalArgumentException("Artist cannot be null.");
    }
    members.add(artist);
  }

  /**
   * Removes a member from the band.
   *
   * @param artist the artist to remove
   * @return true if removed; false otherwise
   */
  public boolean removeMember(RecordingArtist artist) {
    return members.remove(artist);
  }

  /**
   * Returns whether the given artist is a member of the band.
   *
   * @param artist the artist to search for
   * @return true if artist is a member; false otherwise
   */
  public boolean hasMember(RecordingArtist artist) {
    return members.contains(artist);
  }

  /**
   * Returns the band name.
   *
   * @return the band name
   */
  @Override
  public String toString() {
    return name;
  }

  /**
   * Compares this band to another object for equality.
   *
   * Two bands are equal if they have the same name and same members.
   *
   * @param o the other object
   * @return true if equal; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Band)) {
      return false;
    }
    Band band = (Band) o;
    return name.equals(band.name) && members.equals(band.members);
  }

  /**
   * Returns a hash code for this band.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, members);
  }
}