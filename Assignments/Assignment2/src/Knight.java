/**
 * Knight chess piece.
 *
 * Movement rule:
 * - A knight moves in an "L" shape:
 *   - 2 squares in one direction (rows or columns)
 *   - then 1 square in the other direction
 *
 * In coordinates, from current (r,c) to target (row,col):
 * - dr = row - r
 * - dc = col - c
 * A valid knight move must be either:
 *   1) |dr| == 2 AND |dc| == 1
 *   OR
 *   2) |dr| == 1 AND |dc| == 2
 *
 * kill rule:
 * - Knights kill the same way they move, no override
 */
public class Knight extends ChessPiece {

  /**
   * Construct a Knight at the given position and color.
   *
   * @param row   initial row (0 - 7)
   * @param col   initial column (0 - 7)
   * @param color piece color (BLACK or WHITE)
   * @throws IllegalArgumentException if (row,col) is out of bounds or color is null
   */
  public Knight(int row, int col, Color color) {
    // Call the parent
    super(row, col, color);
  }

  /**
   * Determine whether this knight can move to (row, col)
   *
   * Steps:
   * 1) Check out-of-bounds
   * 2) Check same square
   * 3) Compute how far the move
   *    - rowSteps = |row - currentRow|
   *    - colSteps = |col - currentCol|
   * 4) Check the two valid move patterns:
   *    - (2,1) or (1,2)
   *
   * @param row target row
   * @param col target column
   * @return true if the move is a valid knight move, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    // Must be inbound; cannot be same spot
    if (!inBounds(row, col) || isSameSquare(row, col)) {
      return false;
    }

    // Compute how many steps moved
    int rowSteps = Math.abs(row - getRow());
    int colSteps = Math.abs(col - getColumn());

    // valid step: 2 in one direction and 1 in the other
    return (rowSteps == 2 && colSteps == 1) || (rowSteps == 1 && colSteps == 2);
  }
}
