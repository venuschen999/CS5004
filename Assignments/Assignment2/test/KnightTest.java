import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

  @Test
  public void testConstructorRejectsOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> new Knight(-1, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Knight(0, 8, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Knight(8, 0, Color.BLACK));
    assertThrows(IllegalArgumentException.class, () -> new Knight(0, -1, Color.BLACK));
  }

  @Test
  public void testCanMove() {
    ChessPiece n = new Knight(3, 3, Color.WHITE);

    // All 8 possible L moves from (3,3)
    assertTrue(n.canMove(5, 4));
    assertTrue(n.canMove(5, 2));
    assertTrue(n.canMove(1, 4));
    assertTrue(n.canMove(1, 2));
    assertTrue(n.canMove(4, 5));
    assertTrue(n.canMove(2, 5));
    assertTrue(n.canMove(4, 1));
    assertTrue(n.canMove(2, 1));

  }

  @Test
  public void testCanMoveInvalid() {
    ChessPiece n = new Knight(3, 3, Color.WHITE);

    assertFalse(n.canMove(3, 3)); // same square
    assertFalse(n.canMove(4, 4)); // diagonal 1
    assertFalse(n.canMove(3, 5)); // horizontal 2
    assertFalse(n.canMove(6, 6)); // diagonal 3
  }


  @Test
  public void testCanMoveOutOfBounds() {
    ChessPiece n = new Knight(0, 0, Color.WHITE);

    // out-of-bounds targets should always be false
    assertFalse(n.canMove(-1, 2));
    assertFalse(n.canMove(1, -2));
    assertFalse(n.canMove(8, 8));
  }

  @Test
  public void testCanKillNull() {
    ChessPiece n = new Knight(3, 3, Color.WHITE);
    assertFalse(n.canKill(null));
  }

  @Test
  public void testCanKillIfReachable() {
    ChessPiece whiteKnight = new Knight(3, 3, Color.WHITE);
    ChessPiece blackTarget = new Rook(5, 4, Color.BLACK); // valid L target
    assertTrue(whiteKnight.canKill(blackTarget));

    ChessPiece blackKnight = new Knight(4, 4, Color.BLACK);
    ChessPiece whiteTarget = new Bishop(2, 3, Color.WHITE); // |dr|=2, |dc|=1
    assertTrue(blackKnight.canKill(whiteTarget));
  }

  @Test
  public void testCannotKillSameColorEvenIfReachable() {
    ChessPiece whiteKnight = new Knight(3, 3, Color.WHITE);
    ChessPiece whiteFriend = new Rook(5, 4, Color.WHITE);
    assertFalse(whiteKnight.canKill(whiteFriend));
  }

  @Test
  public void testCannotKillEnemyIfNotReachable() {
    ChessPiece n = new Knight(3, 3, Color.WHITE);
    ChessPiece enemyNotL = new Rook(3, 7, Color.BLACK); // not an L move
    assertFalse(n.canKill(enemyNotL));
  }
}

