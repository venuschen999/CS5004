import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for the IListOfBooks recursive list implementation.
 * Includes both EmptyNode (the empty list) and ElementNode (a non-empty list node: first + rest)
 */
public class IListOfBooksTest {

  // 3 samples books in tests
  private Book harryPotter;
  private Book hobbit;
  private Book prideAndPrejudice;

  // lists used in tests
  private IListOfBooks empty;  // []
  private IListOfBooks empty2; // []
  private IListOfBooks one;    // [harryPotter]
  private IListOfBooks three;  // [harryPotter, hobbit, prideAndPrejudice]

  /**
   * Build new test objects before each test
   */
  @BeforeEach
  public void setup() {
    harryPotter = new Book("Happy Potter", "J.K. Rowling", 1997, 10.0f);
    hobbit = new Book("The Hobbit", "J.R.R. Tolkien", 1937, 20.0f);
    prideAndPrejudice = new Book("Pride and Predudice", "Jane Austen", 1813, 5.0f);

    // base case - the Empty list
    empty = new EmptyNode();
    empty2 = new EmptyNode();

    // One-element list: [Happy Potter]
    one = new ElementNode(harryPotter, empty);

    // Three-element list: [Happy Potter, The Hobbit, Pride and Predudice]
    three = new ElementNode(harryPotter,
        new ElementNode(hobbit,
            new ElementNode(prideAndPrejudice, empty)));
  }

  // EmptyNode tests

  /**
   * EmptyNode.count(): expected result is 0
   */
  @Test
  public void testEmptyCount() {
    assertEquals(0, empty.count());
    assertEquals(0, empty2.count());
    assertNotEquals(1, empty.count());
  }

  /**
   * EmptyNode.totalPrice(): empty list total should be 0.
   */
  @Test
  public void testEmptyTotalPrice() {
    assertEquals(0.0f, empty.totalPrice(), 0.0001f);
    assertEquals(0.0f, empty2.totalPrice(), 0.0001f);
  }

  /**
   * EmptyNode.allBefore(year): expected result should be empty
   */
  @Test
  public void testEmptyAllBefore() {
    assertEquals("[]", empty.allBefore(2000).toString()); // stays empty
    assertEquals("[]", empty2.allBefore(2000).toString()); // stays empty
  }

  /**
   * EmptyNode.addAtEnd(book): adding to empty is expected to create a one-element list.
   */
  @Test
  public void testEmptyAddAtEnd() {
    IListOfBooks result = empty.addAtEnd(harryPotter);
    assertEquals(1, result.count()); // now 1
    assertEquals("[" + harryPotter.toString() + "]", result.toString());
    // write the string explicitly
    assertEquals("[Title: Happy Potter Author: J.K. Rowling Year: 1997 Price: 10.00]",
        result.toString());

  }

  /**
   * EmptyNode.toString(): expected output is "[]"
   */
  @Test
  public void testEmptyToString() {
    assertEquals("[]", empty.toString());
    assertEquals("[]", empty2.toString());
  }


  // ElementNode tests

  /**
   * ElementNode.count(): should count recursively (1 + rest.count()).
   */
  @Test
  public void testElementCount() {
    assertEquals(1, one.count());       // one-element list
    assertEquals(3, three.count());     // three-element list
  }

  /**
   * ElementNode.totalPrice(): should add first price + rest.totalPrice().
   */
  @Test
  public void testElementTotalPrice() {
    assertEquals(10.0f, one.totalPrice(), 0.0001f);   // only Happy Potter 10.00
    assertEquals(35.0f, three.totalPrice(), 0.0001f); // 10 + 20 + 5
  }

  /**
   * ElementNode.allBefore(year): should keep only books with year < input year.
   *
   * e.g. if year = 1950, Happy Potter (1997) is not kept,
   * The Hobbit (1937) is kept,
   * Pride and Predudice (1813) is kept;
   */
  @Test
  public void testElementAllBefore() {

    // 1950: 2 books kept
    IListOfBooks filtered1950 = three.allBefore(1950);
    assertEquals(2, filtered1950.count());

    // 2000: 3 books kept
    IListOfBooks filtered2000 = three.allBefore(2000);
    assertEquals(3, filtered2000.count());
  }

  /**
   * ElementNode.addAtEnd(book): expected to preserve order and add at the tail.
   */
  @Test
  public void testElementAddAtEnd() {
    IListOfBooks result = one.addAtEnd(hobbit); // [Happy Potter] + Hobbit
    assertEquals(2, result.count());            // now 2 elements

    // last item should be Hobbit (explicit test)
    assertTrue(result.toString().endsWith(
        "Title: The Hobbit Author: J.R.R. Tolkien Year: 1937 Price: 20.00]"
    ));

    // last item should be Hobbit (implicit test)
    assertTrue(result.toString().endsWith(hobbit.toString() + "]"));

    assertEquals(
        "[Title: Happy Potter Author: J.K. Rowling Year: 1997 Price: 10.00, " +
            "Title: The Hobbit Author: J.R.R. Tolkien Year: 1937 Price: 20.00]",
        result.toString()
    );
  }



  /**
   * ElementNode.toString(): should be proper format
   */
  @Test
  public void testElementToString() {
    // one-element
    assertEquals("[" + harryPotter.toString() + "]", one.toString());

    // 3-element
    assertEquals(
        "[Title: Happy Potter Author: J.K. Rowling Year: 1997 Price: 10.00, " +
            "Title: The Hobbit Author: J.R.R. Tolkien Year: 1937 Price: 20.00, " +
            "Title: Pride and Predudice Author: Jane Austen Year: 1813 Price: 5.00]",
        three.toString()
    );
  }
}
