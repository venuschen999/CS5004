
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {

  @Test
  public void testConstructorOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> new Bishop(-1, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Bishop(0, -1, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Bishop(8, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Bishop(0, 8, Color.WHITE));
  }

  @Test
  public void testCanMoveDiagonal() {
    Bishop b = new Bishop(3, 3, Color.WHITE);
    assertTrue(b.canMove(5, 5));
    assertTrue(b.canMove(1, 1));
    assertTrue(b.canMove(0, 6));
    assertFalse(b.canMove(3, 3)); // same square
    assertFalse(b.canMove(3, 6)); // straight
    assertFalse(b.canMove(4, 6)); // not diagonal
  }

  @Test
  public void testCanMoveOutOfBounds() {
    Bishop b = new Bishop(3, 3, Color.WHITE);
    assertFalse(b.canMove(-1, 0));
    assertFalse(b.canMove(8, 8));
  }

  @Test
  public void testCanKillEnemy() {
    Bishop b = new Bishop(3, 3, Color.WHITE);
    ChessPiece enemy = new Rook(6, 6, Color.BLACK);
    assertTrue(b.canKill(enemy));
  }

  @Test
  public void testCannotKillFriend() {
    Bishop b = new Bishop(3, 3, Color.WHITE);
    ChessPiece friend = new Rook(6, 6, Color.WHITE);
    assertFalse(b.canKill(friend));
  }

  @Test
  public void testCannotKillIfNotReachable() {
    Bishop b = new Bishop(3, 3, Color.WHITE);
    ChessPiece enemy = new Rook(3, 6, Color.BLACK);
    assertFalse(b.canKill(enemy));
  }
}
