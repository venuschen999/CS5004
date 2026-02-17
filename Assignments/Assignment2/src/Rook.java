/**
 * Rook chess piece.
 *
 * Movement rule:
 * - A rook can move any number of squares in a straight line:
 *   - horizontally 
 *   - vertically
 *
 * kill rule:
 * - kill uses the same pattern as movement, no overrides.
 */
public class Rook extends ChessPiece {

  /**
   * Construct a Rook at the given position and color.
   *
   * @param row   initial row (0..7)
   * @param col   initial column (0..7)
   * @param color piece color (BLACK or WHITE)
   * @throws IllegalArgumentException if (row,col) is out of bounds or color is null
   */
  public Rook(int row, int col, Color color) {
    // Call the parent
    super(row, col, color);
  }

  /**
   * Determine whether this rook can move to (row, col)
   *   * Steps:
   *    *  1) Check out-of-bounds
   *    *  2) Check same square
   *    *  3) Compute dr and dc.
   *    *  4) Return true if the move is valid
   * Steps:
   * 1) Check out-of-bounds
   * 2) Check same square
   * 3) Return true if horizontal or vertical move
   *
   * @param row target row
   * @param col target column
   * @return true if the move is a valid rook move, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    // Must be inbound; cannot be same spot
    if (!inBounds(row, col) || isSameSquare(row, col)) {
      return false;
    }

    // Horizontal move: same row, different column.
    if (row == getRow()) {
      return true;
    }

    // Vertical move: same column, different row.
    return col == getColumn();
  }
}
