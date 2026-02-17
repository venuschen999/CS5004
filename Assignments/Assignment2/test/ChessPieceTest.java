import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for behavior implemented in the abstract ChessPiece base class.
 */
public class ChessPieceTest {

  @Test
  public void testConstructorRejectsNullColor() {
    assertThrows(IllegalArgumentException.class, () -> new Rook(0, 0, null));
  }

  @Test
  public void testConstructorRejectsOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> new Rook(-1, 0, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Rook(0, -3, Color.WHITE));
    assertThrows(IllegalArgumentException.class, () -> new Rook(8, 0, Color.BLACK));
    assertThrows(IllegalArgumentException.class, () -> new Rook(0, 8, Color.BLACK));
  }

  @Test
  public void testGetters() {
    ChessPiece p = new Rook(2, 5, Color.BLACK);
    assertEquals(2, p.getRow());
    assertEquals(5, p.getColumn());
    assertEquals(Color.BLACK, p.getColor());
  }

  @Test
  public void testCanKillNull() {
    ChessPiece a= new Rook(3, 3, Color.WHITE);
    assertFalse(a.canKill(null));
  }

  @Test
  public void testCanKillFriendOrFoe() {
    ChessPiece a = new Rook(3, 3, Color.WHITE);
    ChessPiece friend = new Bishop(3, 7, Color.WHITE); // reachable but same color
    ChessPiece foe = new Bishop(3, 7, Color.BLACK); // reachable, can kill
    assertFalse(a.canKill(friend));
    assertTrue(a.canKill(foe));
  }

  @Test
  public void testCanKillEnemyButNotReachable() {
    ChessPiece a = new Rook(3, 3, Color.WHITE);
    ChessPiece foe = new Bishop(5, 5, Color.BLACK); // rook cannot move diagonally
    assertFalse(a.canKill(foe));
  }

  @Test
  public void testDefaultCanKillEnemyAndReachableIsTrue() {
    ChessPiece attacker = new Rook(3, 3, Color.WHITE);
    ChessPiece enemy = new Bishop(3, 7, Color.BLACK); // rook can move horizontally
    assertTrue(attacker.canKill(enemy));
  }

  @Test
  public void testPawnOverridesCanKillSoItDoesNotUseCanMove() {
    // White pawn at (3,3):
    // - canMove to (4,3) is true (forward)
    // - but it cannot KILL straight forward (4,3)
    Pawn pawn = new Pawn(3, 3, Color.WHITE);
    ChessPiece enemyStraightAhead = new Rook(4, 3, Color.BLACK);

    assertTrue(pawn.canMove(4, 3));          // legal MOVE
    assertFalse(pawn.canKill(enemyStraightAhead)); // NOT a legal CAPTURE
  }
}
