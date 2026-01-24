import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
  private Book book1;
  private Book book2;
  private Book book3;

  @BeforeEach
  void setUp() {
    this.book1 = new Book("Harry Potter 1", "J.K. Rowling", 200);
    this.book2 = new Book("1984", "George Orwell", 400);
    this.book3 = new Book("To Kill a Mockingbird", "Harper Lee", 189);
  }

  @Test
  void getTitle() {
    Assertions.assertEquals("Harry Potter 1", this.book1.getTitle());
    Assertions.assertEquals("1984", this.book2.getTitle());
    Assertions.assertEquals("To Kill a Mockingbird", this.book3.getTitle());
  }

  @Test
  void getAuthor() {
    Assertions.assertEquals("J.K. Rowling", this.book1.getAuthor());
    Assertions.assertEquals("George Orwell", this.book2.getAuthor());
    Assertions.assertEquals("Harper Lee", this.book3.getAuthor());
  }

  @Test
  void getPages() {
    Assertions.assertEquals(200, book1.getPages());
    Assertions.assertEquals(400, book2.getPages());
    Assertions.assertEquals(189, book3.getPages());
  }
}