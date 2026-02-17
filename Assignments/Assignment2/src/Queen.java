/**
 * Queen chess piece.
 *
 * - A queen can move any number of squares:
 *   1) horizontally
 *   2) vertically
 *   3) diagonally
 *
 * Kill rule:
 * - For queens, kill uses the same pattern as movement, no override.
 *
 */
public class Queen extends ChessPiece {

  /**
   * Construct a Queen at the given position with the given color.
   *
   * @param row   initial row (0 - 7)
   * @param col   initial column (0 - 7)
   * @param color piece color (BLACK or WHITE)
   * @throws IllegalArgumentException if (row,col) is out of bounds or color is null
   */
  public Queen(int row, int col, Color color) {
    // Call the ChessPiece constructor
    super(row, col, color);
  }

  /**
   * Determine whether this queen can move to (row, col)
   *
   * Steps:
   * 1) Check out-of-bounds
   * 2) Check same square
   * 3) Compute dr and dc.
   * 4) Return true if the move is valid
   *
   * @param row target row
   * @param col target column
   * @return true if the move is valid, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    // Must be inbound; cannot be same spot
    if (!inBounds(row, col) || isSameSquare(row, col)) {
      return false;
    }
    // move in same row -> horizontal move
    if (row == getRow()) {
      return true;
    }

    // move in same column -> vertical move
    if (col == getColumn()) {
      return true;
    }

    // diagonal move -> row change and col change are equal size
    return Math.abs(row - getRow()) == Math.abs(col - getColumn());


  }
}

