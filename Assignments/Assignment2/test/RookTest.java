import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

  @Test
  public void testConstructorRejectsOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> new Rook(-1, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Rook(0, 8, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Rook(8, 0, Color.BLACK));
    assertThrows(IllegalArgumentException.class, () -> new Rook(0, -1, Color.BLACK));
  }

  @Test
  public void testCanMoveStraight() {
    // white
    ChessPiece whiteRook = new Rook(3, 3, Color.WHITE);

    assertTrue(whiteRook.canMove(3, 7));   // horizontal
    assertTrue(whiteRook.canMove(0, 3));   // vertical
    assertFalse(whiteRook.canMove(5, 5));  // diagonal
    assertFalse(whiteRook.canMove(3, 3));  // same square

   // Black
    ChessPiece blackRook = new Rook(4, 4, Color.BLACK);

    assertTrue(blackRook.canMove(4, 0));   // horizontal
    assertTrue(blackRook.canMove(7, 4));   // vertical
    assertFalse(blackRook.canMove(6, 6));  // diagonal
    assertFalse(blackRook.canMove(4, 4));  // same square
  }

  @Test
  public void testCanMoveOutOfBounds() {
    ChessPiece rook = new Rook(3, 3, Color.WHITE);

    assertFalse(rook.canMove(-1, 3));
    assertFalse(rook.canMove(3, 8));
  }

  @Test
  public void testCanKillIfReachable() {
    ChessPiece whiteRook = new Rook(3, 3, Color.WHITE);
    ChessPiece blackTarget = new Bishop(3, 7, Color.BLACK);

    assertTrue(whiteRook.canKill(blackTarget));

    ChessPiece blackRook = new Rook(4, 4, Color.BLACK);
    ChessPiece whiteTarget = new Bishop(7, 4, Color.WHITE);

    assertTrue(blackRook.canKill(whiteTarget));
  }

  @Test
  public void testCannotKillSameColorEvenIfReachable() {
    ChessPiece whiteRook = new Rook(3, 3, Color.WHITE);
    ChessPiece whiteFriend = new Bishop(3, 7, Color.WHITE);

    assertFalse(whiteRook.canKill(whiteFriend));

    ChessPiece blackRook = new Rook(4, 4, Color.BLACK);
    ChessPiece blackFriend = new Bishop(7, 4, Color.BLACK);

    assertFalse(blackRook.canKill(blackFriend));
  }

  @Test
  public void testCannotKillEnemyIfNotReachable() {
    ChessPiece rook = new Rook(3, 3, Color.WHITE);
    ChessPiece enemy = new Bishop(5, 5, Color.BLACK); // diagonal -> rook cannot reach

    assertFalse(rook.canKill(enemy));
  }

  @Test
  public void testCanNotKillNull() {
    ChessPiece rook = new Rook(3, 3, Color.WHITE);
    assertFalse(rook.canKill(null));
  }
}
