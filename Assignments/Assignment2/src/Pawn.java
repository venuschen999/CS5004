/**
 * Pawn chess piece.
 * Move rules：
 * - A pawn moves straight forward in the same column.
 * - standard move: 1 square forward.
 * - From the starting row, it may optionally move 2 squares forward:
 *     White start row = 1  (can move from row 1 to row 3)
 *     Black start row = 6  (can move from row 6 to row 4)
 *
 * kill rules:
 * - A pawn kills 1 square diagonally forward
 *     White kill: (row + 1, col ± 1)
 *     Black kill: (row - 1, col ± 1)
 *
 * Special Royal Row rules:
 * - White pawns cannot be created on row 0 (royal row).
 * - Black pawns cannot be created on row 7 (royal row).
 * - If violated, throw IllegalArgumentException.
 */
public class Pawn extends ChessPiece {

  /**
   * Construct a Pawn at the given position and color.
   *
   * @param row   initial row (0 - 7)
   * @param col   initial column (0 -7)
   * @param color piece color (BLACK or WHITE)
   * @throws IllegalArgumentException if (row,col) is out of bounds, color is null,
   *                                  or pawn violates the "royal row" rule
   */
  public Pawn(int row, int col, Color color) {
    // Call parent
    super(row, col, color);

    // White pawns cannot start on row 0
    if (color == Color.WHITE && row == 0) {
      throw new IllegalArgumentException("White pawn cannot be created on row 0");
    }

    // Black pawns cannot start on row 7
    if (color == Color.BLACK && row == 7) {
      throw new IllegalArgumentException("Black pawn cannot be created on row 7");
    }
  }

  /**
   * Determine whether this pawn can move to (row, col),
   * For movement:
   * - Must stay in the same column
   * - Must move forward
   *     White forward = +1 row
   *     Black forward = -1 row
   * - Can move 2 squares only from the start row.
   *
   * @param row target row
   * @param col target column
   * @return true if this is a valid move, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    // Must be inbound; cannot be same spot
    if (!inBounds(row, col) || isSameSquare(row, col)) {
      return false;
    }
    // dRow = targetRow - currentRow
    // dCol = targetCol - currentCol
    int dRow = row - getRow();
    int dCol = col - getColumn();

    // must be straight move (same column)
    if (dCol != 0) {
      return false;
    }

    //  WHITE pawn moves
    if (getColor() == Color.WHITE) {
      // standard move: exactly 1 forward (row + 1)
      if (dRow == 1) {
        return true;
      }

      // if from start row 1, pawn can move 2 forward (row + 2)
      return getRow() == 1 && dRow == 2;
    }

    // BLACK pawn moves
    if (dRow == -1) {
      return true;
    }

    // if start from row 6, pawn may move 2 forward (row - 2)
    return getRow() == 6 && dRow == -2;
  }

  /**
   * Determine whether this pawn can kill the given piece.
   *
   * - Pawn kill diagonally forward by 1
   *
   * @param piece the target piece
   * @return true if this pawn can kill the target, false otherwise
   */

    @Override
    public boolean canKill(ChessPiece piece) {
    // target must not be null
    if (piece == null) {
      return false;
    }

    // cannot kill friend
    if (piece.getColor() == getColor()) {
      return false;
    }

    // Target location
    int targetRow = piece.getRow();
    int targetCol = piece.getColumn();

    // target must be on the board and not the same square
    if (!inBounds(targetRow, targetCol)) {
      return false;
    }
    if (targetRow == getRow() && targetCol == getColumn()) {
      return false;
    }

    // dr, dc from current pawn to target square
    int dRow = targetRow - getRow();
    int dCol = Math.abs(targetCol - getColumn()); //abs

    // WHITE kills one step forward diagonally:
    // dRow must be +1, and column must change by 1.
    if (getColor() == Color.WHITE) {
      return dRow == 1 && dCol == 1;
    }

    // BLACK kills one step forward diagonally:
    // dRow must be -1, and column must change by 1.
    return dRow == -1 && dCol == 1;
  }
}
