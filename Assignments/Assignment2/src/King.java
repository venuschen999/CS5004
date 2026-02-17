/**
 * King chess piece.
 *
 * Movement rule:
 * - A king can move 1 square in any direction:
 *   - up, down, left, right, diagonal
 *
 * - Let dr = targetRow - currentRow
 * - Let dc = targetCol - currentCol
 * - A king move is valid if:
 *     |dr| <= 1  and |dc| <= 1
 *
 * kill rule:
 * - A king kill the same way it moves, no override canKill.
 */
public class King extends ChessPiece {

  /**
   * Construct a King at the given position and color.
   *
   * @param row   initial row (0 - 7)
   * @param col   initial column (0 - 7)
   * @param color piece color (BLACK or WHITE)
   * @throws IllegalArgumentException if (row,col) is out of bounds or color is null
   */
  public King(int row, int col, Color color) {
    // Call the parent
    super(row, col, color);
  }

  /**
   * Determine whether this king can move to (row, col)
   *
   * Steps:
   *  1) Check out-of-bounds
   *  2) Check same square
   *  3) Compute dr and dc.
   *  4) Return true if the move is valid
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

    // How many rows/cols moved (absolute value)
    int rowSteps = Math.abs(row - getRow());        // 0 or 1 allowed
    int colSteps = Math.abs(col - getColumn());     // 0 or 1 allowed

    // King can move at most 1 square in row direction and at most 1 in col direction.
    // only (1,0), (0,1), and (1,1) moves are legal
    return rowSteps <= 1 && colSteps <= 1;
  }
}
