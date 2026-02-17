/**
 * Rules for all chess pieces
 * - report its current row
 * - report its current column
 * - report its color
 * - determine whether it can move to a target position
 * - determine whether it can kill another piece given its position
 */
public interface ChessPieceContract {

  int getRow();

  int getColumn();

  Color getColor();

  boolean canMove(int row, int col);

  boolean canKill(ChessPiece piece);
}
