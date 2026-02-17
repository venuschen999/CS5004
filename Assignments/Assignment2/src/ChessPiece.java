/**
 * Abstract base class for chess pieces.
 * include shared fields (row, col, color)
 */
public abstract class ChessPiece implements ChessPieceContract {
  // pieces are final as they will not change after created
  private final int row;
  private final int col;
  private final Color color;

  /**
   * Construct a chess piece at (row, col) with a given color.
   *
   * @param row initial row
   * @param col initial column
   * @param color BLACK or WHITE (must not be null)
   * @throws IllegalArgumentException if (row,col) is out of bounds or color is null
   */
  protected ChessPiece(int row, int col, Color color) {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null");
    }
    if (!inBounds(row, col)) {
      throw new IllegalArgumentException("Piece position out of bounds: (" + row + "," + col + ")");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  /**
   * Helper method: check if the piece is inbound or not
   * row is 0 - 7; col is 0 - 7; board is 8 * 8
   * @param r row
   * @param c col
   * @return true if 0 - 7, else false
   */
  protected static boolean inBounds(int r, int c) {
    return r >= 0 && r <= 7 && c >= 0 && c <= 7;
  }

  /**
   * Provide row difference from current row position to target.
   * dr = targetRow - currentRow
   *
   * @param targetRow target row
   * @return difference in rows
   */
  protected int dr(int targetRow) {
    return targetRow - this.row;
  }

  /**
   * Provide column difference from current column position to target.
   * dc = targetCol - currentCol
   *
   * @param targetCol target column
   * @return difference in columns
   */
  protected int dc(int targetCol) {
    return targetCol - this.col;
  }

  /**
   * Check whether (r,c) is the same square as this piece's current position.
   *
   * @param r row
   * @param c col
   * @return true if same square
   */
  protected boolean isSameSquare(int r, int c) {
    return this.row == r && this.col == c;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getColumn() {
    return col;
  }

  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Default kill rule for most pieces:
   * - can't kill null
   * - can't kill same color
   * - can kill if your move pattern can land on target positon
   *
   * Pawn overrides this because pawn kill != pawn move.
   *
   * @param piece target piece
   * @return true if kill is valid
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    if (piece == null) {
      return false;
    }
    // if friend, kill must be false
    if (piece.getColor() == this.color) {
      return false;
    }
    // For most pieces(except Pawn), kill the same as move pattern
    return this.canMove(piece.getRow(), piece.getColumn());
  }
}
