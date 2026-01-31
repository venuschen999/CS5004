import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoeTest {

  // 4 shoes created for all tests
  private Shoe athleticNike;
  private Shoe casualAdidas;
  private Shoe dressGucci;
  private Shoe athleticAdidas;

  @BeforeEach
  void setUp() {
    athleticNike = new Shoe(Kind.ATHLETIC, Color.BLACK, Brand.NIKE, 9.5);
    casualAdidas = new Shoe(Kind.CASUAL, Color.PASTEL, Brand.ADIDAS, 10.0);
    dressGucci   = new Shoe(Kind.DRESS, Color.WHITE, Brand.GUCCI, 8.0);
    athleticAdidas = new Shoe(Kind.ATHLETIC, Color.NEUTRAL, Brand.ADIDAS, 7.0);
  }

  // Constructor tests

  @Test
  void testConstructorValid() {
    Shoe s = new Shoe(Kind.CASUAL, Color.BLACK, Brand.ADIDAS, 11.0);

    assertEquals(Kind.CASUAL, s.getKind());
    assertEquals(11.0, s.getSize());
  }

  @Test
  void testConstructorThrowsOnlyForNikeDress() {
    // illegal case - expects exception
    assertThrows(IllegalArgumentException.class, () -> {
      new Shoe(Kind.DRESS, Color.BLACK, Brand.NIKE, 8.0);
    });

    // other brands should allow dress
    assertEquals(Kind.DRESS, dressGucci.getKind());
  }

  // Getter tests

  @Test
  void testGetKind() {
    assertEquals(Kind.ATHLETIC, athleticNike.getKind());
    assertEquals(Kind.CASUAL, casualAdidas.getKind());
  }

  @Test
  void testGetColor() {
    assertEquals(Color.BLACK, athleticNike.getColor());
    assertEquals(Color.PASTEL, casualAdidas.getColor());
  }

  @Test
  void testGetBrand() {
    assertEquals(Brand.NIKE, athleticNike.getBrand());
    assertEquals(Brand.GUCCI, dressGucci.getBrand());
  }

  @Test
  void testGetSize() {
    assertEquals(9.5, athleticNike.getSize());
    assertEquals(10.0, casualAdidas.getSize());
  }

  // toString test

  @Test
  void testToStringIncludesAllAttributes() {
    String s = athleticNike.toString();

    // Make sure all 4 attributes appear in some way
    assertTrue(s.contains("Athletic")); // kind formatting from your switch
    assertTrue(s.contains("Black"));    // color formatting from your switch/default
    assertTrue(s.contains("Nike"));     // brand formatting from your switch
    assertTrue(s.contains("9.5"));      // size
  }
}
