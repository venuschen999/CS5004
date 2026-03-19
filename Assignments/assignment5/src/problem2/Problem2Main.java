package problem2;

import java.util.List;

/**
 * Simple demo class for Problem 2.
 *
 * This class creates sample creators, items, and a catalog,
 * then demonstrates the three required search methods.
 */
public class Problem2Main {

  /**
   * example showing how the catalog system works.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {

    // Create some authors.
    Author rowling = new Author("J.K.", "Rowling");
    Author tolkien = new Author("J.R.R.", "Tolkien");

    // Create some recording artists.
    RecordingArtist taylor = new RecordingArtist("Taylor", "Swift");
    RecordingArtist paul = new RecordingArtist("Paul", "McCartney");
    RecordingArtist john = new RecordingArtist("John", "Lennon");

    // Create a band and add members to it.
    Band beatles = new Band("The Beatles");
    beatles.addMember(paul);
    beatles.addMember(john);

    // Create book items.
    Book book1 = new Book(rowling, "Harry Potter and the Goblet of Fire", 2000);
    Book book2 = new Book(tolkien, "The Hobbit", 1937);

    // Create music items.
    // music1 is created by a solo recording artist.
    Music music1 = new Music(taylor, "Love Story", 2008);

    // music2 and music3 are created by a band.
    Music music2 = new Music(beatles, "Hey Jude", 1968);
    Music music3 = new Music(beatles, "All You Need Is Love", 1967);

    // Create an empty catalog and add all items to it.
    Catalog catalog = new Catalog();
    catalog.addItem(book1);
    catalog.addItem(book2);
    catalog.addItem(music1);
    catalog.addItem(music2);
    catalog.addItem(music3);

    // Search by keyword in title (case-insensitive).
    // This should match titles containing "love".
    List<Item> keywordMatches = catalog.search("love");

    // Search by exact author match.
    // This should return Rowling's book(s).
    List<Item> authorMatches = catalog.search(rowling);

    // Search by exact recording artist match.
    // This should return music created directly by Paul,
    // or music created by a band that includes Paul.
    List<Item> artistMatches = catalog.search(paul);

    // Print search results.
    System.out.println("Search by keyword 'love': " + keywordMatches);
    System.out.println("Search by author J.K. Rowling: " + authorMatches);
    System.out.println("Search by recording artist Paul McCartney: " + artistMatches);
  }
}