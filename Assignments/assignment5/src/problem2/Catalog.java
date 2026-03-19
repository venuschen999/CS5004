package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a catalog of library items.
 *
 * This class stores a collection of items and supports overloaded
 * search methods.
 */
public class Catalog {
  private final List<Item> items;

  /**
   * Constructs an empty catalog.
   */
  public Catalog() {
    this.items = new ArrayList<>();
  }

  /**
   * Constructs a catalog with the given initial items.
   *
   * @param items the initial items
   * @throws IllegalArgumentException if items is null
   */
  public Catalog(List<Item> items) {
    if (items == null) {
      throw new IllegalArgumentException("Items list cannot be null.");
    }
    this.items = new ArrayList<>(items);
  }

  /**
   * Returns an unmodifiable view of the items in the catalog.
   *
   * @return the catalog items
   */
  public List<Item> getItems() {
    return Collections.unmodifiableList(items);
  }

  /**
   * Adds an item to the catalog.
   *
   * @param item the item to add
   * @throws IllegalArgumentException if item is null
   */
  public void addItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null.");
    }
    items.add(item);
  }

  /**
   * Removes an item from the catalog.
   *
   * @param item the item to remove
   * @return true if removed; false otherwise
   */
  public boolean removeItem(Item item) {
    return items.remove(item);
  }

  /**
   * Searches for all items whose title contains the given keyword,
   * ignoring case.
   *
   * @param keyword the keyword to search for
   * @return a list of matching items
   * @throws IllegalArgumentException if keyword is null
   */
  public List<Item> search(String keyword) {
    if (keyword == null) {
      throw new IllegalArgumentException("Keyword cannot be null.");
    }

    List<Item> matches = new ArrayList<>();
    String lowerKeyword = keyword.toLowerCase();

    for (Item item : items) {
      if (item.getTitle().toLowerCase().contains(lowerKeyword)) {
        matches.add(item);
      }
    }

    return matches;
  }

  /**
   * Searches for all items that exactly match the given author.
   *
   * Only books can match this search.
   *
   * @param author the author to search for
   * @return a list of matching items
   * @throws IllegalArgumentException if author is null
   */
  public List<Item> search(Author author) {
    if (author == null) {
      throw new IllegalArgumentException("Author cannot be null.");
    }
    return searchByCreator(author); // helper method
  }

  /**
   * Searches for all items that exactly match the given recording artist.
   *
   * Only music can match this search. A match occurs when the recording artist is:
   *   - the direct creator of the music item, or</li>
   *   - a member of the band that created the music item</li>
   *
   * @param artist the recording artist to search for
   * @return a list of matching items
   * @throws IllegalArgumentException if artist is null
   */
  public List<Item> search(RecordingArtist artist) {
    if (artist == null) {
      throw new IllegalArgumentException("Recording artist cannot be null.");
    }
    return searchByCreator(artist);
  }

  /**
   * Searches for items based on the given creator.
   *
   * This helper method is used internally by the overloaded public search methods.
   * The matching rules depend on the specific runtime type of the creator:
   *   - If the creator is an Author, only books with an exact author match are returned.
   *   - If the creator is a RecordingArtist, only music items are considered.
   *       The music matches if the artist is either the direct creator or a member
   *       of the band creator.
   *
   * @param creator the creator to search for
   * @return a list of matching items
   */
  private List<Item> searchByCreator(Creator creator) {
    List<Item> matches = new ArrayList<>();

    for (Item item : items) {

      // Author search: only books can match.
      if (creator instanceof Author && item instanceof Book) {
        Book book = (Book) item;
        if (book.getAuthor().equals(creator)) {
          matches.add(book);
        }
      }

      // Recording artist search: only music can match.
      if (creator instanceof RecordingArtist && item instanceof Music) {
        RecordingArtist artist = (RecordingArtist) creator;
        Creator musicCreator = item.getCreator();

        // Match music created directly by this recording artist.
        if (musicCreator instanceof RecordingArtist && musicCreator.equals(artist)) {
          matches.add(item);
        }

        // Match music created by a band that contains this artist.
        if (musicCreator instanceof Band) {
          Band band = (Band) musicCreator;
          if (band.hasMember(artist)) {
            matches.add(item);
          }
        }
      }
    }

    return matches;
  }
}