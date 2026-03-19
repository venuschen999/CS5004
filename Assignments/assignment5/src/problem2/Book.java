package problem2;

/**
 * Represents a book.
 *
 * A book has an Author as its creator.
 */
public class Book extends Item {

  /**
   * Constructs a book with the given author, title, and year.
   *
   * @param author the author
   * @param title the title
   * @param year the publication year
   */
  public Book(Author author, String title, int year) {
    super(author, title, year);
  }

  /**
   * Returns the author of this book.
   *
   * @return the author
   */
  public Author getAuthor() {
    return (Author) getCreator();
  }

  /**
   * Sets the author of this book.
   *
   * @param author the new author
   * @throws IllegalArgumentException if author is null
   */
  public void setAuthor(Author author) {
    setCreator(author);
  }
}