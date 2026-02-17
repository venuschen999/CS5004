import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

  @Test
  public void testConstructorRejectsOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> new Pawn(-1, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Pawn(0, -1, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Pawn(8, 0, Color.BLACK));
    assertThrows(IllegalArgumentException.class, () -> new Pawn(0, 8, Color.BLACK));
  }

  @Test
  public void testConstructorRejectsRoyalRow() {
    // white pawns cannot be created on row 0
    assertThrows(IllegalArgumentException.class, () -> new Pawn(0, 3, Color.WHITE));

    // black pawns cannot be created on row 7
    assertThrows(IllegalArgumentException.class, () -> new Pawn(7, 3, Color.BLACK));
  }


  @Test
  public void testWhitePawnMoveFromStartRow() {
    Pawn p = new Pawn(1, 3, Color.WHITE);

    // forward 1
    assertTrue(p.canMove(2, 3));

    // optional forward 2 from start row 1
    assertTrue(p.canMove(3, 3));

    // cannot move sideways
    assertFalse(p.canMove(2, 4));

    // cannot move backwards
    assertFalse(p.canMove(0, 3));

    // cannot stay on same square
    assertFalse(p.canMove(1, 3));
  }

  @Test
  public void testWhitePawnMoveNotFromStartRow() {
    Pawn p = new Pawn(3, 3, Color.WHITE);

    // forward 1 is ok
    assertTrue(p.canMove(4, 3));

    // forward 2 is NOT allowed because not on start row 1
    assertFalse(p.canMove(5, 3));
  }

  @Test
  public void testBlackPawnMoveFromStartRow() {
    Pawn p = new Pawn(6, 4, Color.BLACK);

    // forward 1 (down the board: row decreases)
    assertTrue(p.canMove(5, 4));

    // optional forward 2 from start row 6
    assertTrue(p.canMove(4, 4));

    // cannot move sideways
    assertFalse(p.canMove(5, 5));

    // cannot move backwards
    assertFalse(p.canMove(7, 4));

    // cannot stay on same square
    assertFalse(p.canMove(6, 4));
  }

  @Test
  public void testBlackPawnMoveNotFromStartRow() {
    Pawn p = new Pawn(4, 4, Color.BLACK);

    // forward 1 is ok
    assertTrue(p.canMove(3, 4));

    // forward 2 is NOT allowed because not on start row 6
    assertFalse(p.canMove(2, 4));
  }

  @Test
  public void testCanMoveOutOfBounds() {
    Pawn p = new Pawn(1, 0, Color.WHITE);
    assertFalse(p.canMove(-1, 0));
    assertFalse(p.canMove(8, 0));
    assertFalse(p.canMove(2, -1));
    assertFalse(p.canMove(2, 8));
  }
  @Test
  public void testWhitePawnCanKillDiagonalForwardOnly() {
    Pawn p = new Pawn(3, 3, Color.WHITE);

    // White kills (row+1, col±1)
    ChessPiece enemyDiagRight = new Rook(4, 4, Color.BLACK);
    ChessPiece enemyDiagLeft  = new Bishop(4, 2, Color.BLACK);

    assertTrue(p.canKill(enemyDiagRight));
    assertTrue(p.canKill(enemyDiagLeft));

    // cannot kill straight forward
    ChessPiece enemyStraight = new Knight(4, 3, Color.BLACK);
    assertFalse(p.canKill(enemyStraight));

    // cannot kill backwards diagonally
    ChessPiece enemyBackDiag = new Queen(2, 2, Color.BLACK);
    assertFalse(p.canKill(enemyBackDiag));
  }

  @Test
  public void testBlackPawnCanKillDiagonalForwardOnly() {
    Pawn p = new Pawn(4, 4, Color.BLACK);

    // Black kills (row-1, col±1)
    ChessPiece enemyDiagRight = new Rook(3, 5, Color.WHITE);
    ChessPiece enemyDiagLeft  = new Bishop(3, 3, Color.WHITE);

    assertTrue(p.canKill(enemyDiagRight));
    assertTrue(p.canKill(enemyDiagLeft));

    // cannot kill straight forward
    ChessPiece enemyStraight = new Knight(3, 4, Color.WHITE);
    assertFalse(p.canKill(enemyStraight));

    // cannot kill backwards diagonally
    ChessPiece enemyBackDiag = new Queen(5, 5, Color.WHITE);
    assertFalse(p.canKill(enemyBackDiag));
  }

  @Test
  public void testPawnCannotKillNull() {
    Pawn p = new Pawn(3, 3, Color.WHITE);
    assertFalse(p.canKill(null));
  }

  @Test
  public void testPawnCannotKillFriend() {
    Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
    ChessPiece whiteFriend = new Rook(4, 4, Color.WHITE);
    assertFalse(whitePawn.canKill(whiteFriend));

    Pawn blackPawn = new Pawn(4, 4, Color.BLACK);
    ChessPiece blackFriend = new Rook(3, 3, Color.BLACK);
    assertFalse(blackPawn.canKill(blackFriend));
  }
}