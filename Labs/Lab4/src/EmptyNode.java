/**
 * The class represents an empty list of books.
 *
 * base case for recursive  list structure.</p>
 */
public class EmptyNode implements IListOfBooks {

  /**
   * Create an empty list of books.
   */
  public EmptyNode() {
    // no fields as required
  }

  @Override
  public int count() {
    return 0;
  }

  @Override
  public float totalPrice() {
    return 0.0f;
  }

  @Override
  public IListOfBooks allBefore(int year) {
    return this;
  }

  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(book, this);
  }

  @Override
  public String toString() {
    return "[]";
  }
}
