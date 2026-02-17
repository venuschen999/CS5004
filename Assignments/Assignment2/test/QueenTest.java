import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

  @Test
  public void testConstructorRejectsOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> new Queen(-1, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Queen(0, 8, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Queen(8, 0, Color.BLACK));
  }

  @Test
  public void testCanMove() {
    ChessPiece q = new Queen(3, 3, Color.WHITE);

    // horizontal (same row)
    assertTrue(q.canMove(3, 7));
    assertTrue(q.canMove(3, 0));

    // vertical (same column)
    assertTrue(q.canMove(0, 3));
    assertTrue(q.canMove(7, 3));

    // diagonal
    assertTrue(q.canMove(6, 6));
    assertTrue(q.canMove(0, 0));
    assertTrue(q.canMove(6, 0));
    assertTrue(q.canMove(0, 6));
  }

  @Test
  public void testCanMoveInvalid() {
    ChessPiece q = new Queen(3, 3, Color.WHITE);

    // same square is not a move
    assertFalse(q.canMove(3, 3));

    // not straight and not diagonal
    assertFalse(q.canMove(5, 6));
    assertFalse(q.canMove(4, 6));
  }


  @Test
  public void testCanMoveOutOfBoundsFalse() {
    ChessPiece q = new Queen(3, 3, Color.WHITE);
    assertFalse(q.canMove(-1, 3));
    assertFalse(q.canMove(8, 3));
    assertFalse(q.canMove(3, -1));
    assertFalse(q.canMove(3, 8));
  }


  @Test
  public void testCanKillNull() {
    ChessPiece q = new Queen(3, 3, Color.WHITE);
    assertFalse(q.canKill(null));
  }

  @Test
  public void testCanKillBlackIfReachable() {
    ChessPiece whiteQueen = new Queen(3, 3, Color.WHITE);

    ChessPiece blackOnSameRow = new Rook(3, 7, Color.BLACK);
    ChessPiece blackOnSameCol = new Bishop(0, 3, Color.BLACK);
    ChessPiece blackOnDiagonal = new Knight(6, 6, Color.BLACK);

    assertTrue(whiteQueen.canKill(blackOnSameRow));
    assertTrue(whiteQueen.canKill(blackOnSameCol));
    assertTrue(whiteQueen.canKill(blackOnDiagonal));

    ChessPiece blackQueen = new Queen(4, 4, Color.BLACK);

    ChessPiece whiteOnSameRow = new Rook(4, 1, Color.WHITE);
    ChessPiece whiteOnSameCol = new Bishop(7, 4, Color.WHITE);
    ChessPiece whiteOnDiagonal = new Knight(1, 1, Color.WHITE);

    assertTrue(blackQueen.canKill(whiteOnSameRow));
    assertTrue(blackQueen.canKill(whiteOnSameCol));
    assertTrue(blackQueen.canKill(whiteOnDiagonal));
  }

  @Test
  public void testCannotKillSameColorEvenIfReachable() {
    ChessPiece whiteQueen = new Queen(3, 3, Color.WHITE);
    ChessPiece whiteFriend = new Rook(3, 7, Color.WHITE);
    assertFalse(whiteQueen.canKill(whiteFriend));

    ChessPiece blackQueen = new Queen(4, 4, Color.BLACK);
    ChessPiece blackFriend = new Rook(4, 1, Color.BLACK);
    assertFalse(blackQueen.canKill(blackFriend));
  }

  @Test
  public void testCannotKillEnemyIfNotReachable() {
    ChessPiece q = new Queen(3, 3, Color.WHITE);

    // (5,6) is not straight and not diagonal from (3,3)
    ChessPiece enemy = new Rook(5, 6, Color.BLACK);

    assertFalse(q.canKill(enemy));
  }
}
