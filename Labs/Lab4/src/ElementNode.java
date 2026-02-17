/**
 * Create a list of books.
 * each ElementNode stores one Book
 * the rest is a list
 */
public class ElementNode implements IListOfBooks {

  private final Book first;
  private final IListOfBooks rest;

  /**
   * Create a non-empty list node.
   *
   * @param first the book stored in this node
   * @param rest the rest of the list (another IListOfBooks)
   * @throws IllegalArgumentException if first or rest is null
   */
  public ElementNode(Book first, IListOfBooks rest) {
    if (first == null || rest == null) {
      throw new IllegalArgumentException("first and rest must NOT be null");
    }
    this.first = first;
    this.rest = rest;
  }

  @Override
  public int count() {
    // 1 (first node) + count of the rest
    return 1 + this.rest.count();
  }

  @Override
  public float totalPrice() {
    // price of this book + total price of the rest
    return this.first.getPrice() + this.rest.totalPrice();
  }

  @Override
  public IListOfBooks allBefore(int year) {
    // filter the book if published before the given year
    if (this.first.getYear() < year) {
      return new ElementNode(this.first, this.rest.allBefore(year));
    }
    // drop this book and keep filtering for the rest
    return this.rest.allBefore(year);
  }

  @Override
  public IListOfBooks addAtEnd(Book book) {
    // in case book is null
    if (book == null) {
      throw new IllegalArgumentException("book cannot be null");
    }
    // keep first and add to end of rest
    return new ElementNode(this.first, this.rest.addAtEnd(book));
  }

  @Override
  public String toString() {
    // create String for rest
    String restString = this.rest.toString(); // "[]" or "[..., ...]"
    if (restString.equals("[]")) {
      return "[" + this.first + "]";
    }
    // remove '[' of restString (leveraging substring String method)
    return "[" + this.first.toString() + ", " + restString.substring(1);
  }
}
