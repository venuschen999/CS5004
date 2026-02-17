/**
 * Bishop chess piece.
 *
 * Movement rule:
 * - A bishop can move diagonally (any number of squares).
 * - Diagonal means
 *     |targetRow - currentRow| == |targetCol - currentCol|
 *
 * Kill rule:
 * - For bishops, capture uses the same pattern as movement so no override.
 */

public class Bishop extends ChessPiece {
  /**
   * Construct a Bishop at the given position and color.
   *
   * @param row   initial row (0 - 7)
   * @param col   initial column (0 - 7)
   * @param color piece color (BLACK or WHITE)
   * @throws IllegalArgumentException if (row,col) is out of bounds or color is null
   */

  public Bishop(int row, int col, Color color) {
    super(row, col, color);
  }

  /**
   * Determine whether this bishop can move to (row, col)
   *
   * - Target must be within bounds (0 - 7).
   * - Target cannot be the current square.
   * - Move must be diagonal: |dr| == |dc|.
   *
   * @param row target row
   * @param col target column
   * @return true if the move is valid, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if (!inBounds(row, col) || isSameSquare(row, col)) {
      return false;
    }
    return Math.abs(dr(row)) == Math.abs(dc(col));
  }
}
