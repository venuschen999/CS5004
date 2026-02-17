import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

  @Test
  public void testConstructorRejectsOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> new King(-1, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new King(0, -1, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new King(8, 0, Color.BLACK));
    assertThrows(IllegalArgumentException.class, () -> new King(0, 8, Color.BLACK));
  }

  @Test
  public void testCanMove() {
    ChessPiece k = new King(3, 3, Color.WHITE);


    assertTrue(k.canMove(3, 4)); // right
    assertTrue(k.canMove(3, 2)); // left
    assertTrue(k.canMove(4, 3)); // up
    assertTrue(k.canMove(2, 3)); // down
    assertTrue(k.canMove(4, 4));
    assertTrue(k.canMove(4, 2));
    assertTrue(k.canMove(2, 4));
    assertTrue(k.canMove(2, 2));

    ChessPiece a = new King(4, 4, Color.BLACK);

    assertTrue(a.canMove(4, 5));
    assertTrue(a.canMove(4, 3));
    assertTrue(a.canMove(5, 4));
    assertTrue(a.canMove(3, 4));

    assertTrue(a.canMove(5, 5));
    assertTrue(a.canMove(5, 3));
    assertTrue(a.canMove(3, 5));
    assertTrue(a.canMove(3, 3));
  }

  @Test
  public void testCanMoveInvalid() {
    ChessPiece k = new King(3, 3, Color.WHITE);

    // same square is not a move
    assertFalse(k.canMove(3, 3));

    // too far
    assertFalse(k.canMove(5, 3));
    assertFalse(k.canMove(3, 5));
    assertFalse(k.canMove(5, 5));
    assertFalse(k.canMove(1, 3));
  }


  @Test
  public void testCanMoveOutOfBounds() {
    ChessPiece k = new King(0, 0, Color.WHITE);

    assertFalse(k.canMove(-1, 0));
    assertFalse(k.canMove(0, -1));
    assertFalse(k.canMove(-1, -1));

    // still out of bounds
    ChessPiece k2 = new King(7, 7, Color.BLACK);
    assertFalse(k2.canMove(8, 7));
    assertFalse(k2.canMove(7, 8));
    assertFalse(k2.canMove(8, 8));
  }

  @Test
  public void testCanKillNull() {
    ChessPiece k = new King(3, 3, Color.WHITE);
    assertFalse(k.canKill(null));
  }

  @Test
  public void testCanKillBlack() {
    ChessPiece whiteKing = new King(3, 3, Color.WHITE);

    ChessPiece blackAdjacent1 = new Pawn(4, 4, Color.BLACK);
    ChessPiece blackAdjacent2 = new Rook(3, 4, Color.BLACK);

    assertTrue(whiteKing.canKill(blackAdjacent1));
    assertTrue(whiteKing.canKill(blackAdjacent2));

    ChessPiece blackKing = new King(4, 4, Color.BLACK);

    ChessPiece whiteAdjacent1 = new Pawn(3, 3, Color.WHITE);
    ChessPiece whiteAdjacent2 = new Bishop(4, 3, Color.WHITE);

    assertTrue(blackKing.canKill(whiteAdjacent1));
    assertTrue(blackKing.canKill(whiteAdjacent2));
  }

  @Test
  public void testCannotKillSameColor() {
    ChessPiece whiteKing = new King(3, 3, Color.WHITE);
    ChessPiece whiteFriend = new Pawn(4, 4, Color.WHITE);
    assertFalse(whiteKing.canKill(whiteFriend));

    ChessPiece blackKing = new King(4, 4, Color.BLACK);
    ChessPiece blackFriend = new Pawn(3, 3, Color.BLACK);
    assertFalse(blackKing.canKill(blackFriend));
  }

  @Test
  public void testCannotKillEnemyIfTooFar() {
    ChessPiece k = new King(3, 3, Color.WHITE);
    ChessPiece enemyFar = new Pawn(5, 5, Color.BLACK); // too far for king
    assertFalse(k.canKill(enemyFar));
  }
}
