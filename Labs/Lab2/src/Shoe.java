/**
 * Represents a shoe with a kind, color, brand, and size.
 */
public class Shoe {

  private Kind kind;
  private Color color;
  private Brand brand;
  private double size;

  /**
   * Creates a Shoe with attributes: kind, color, brand, size
   *
   * @param kind the type of shoe
   * @param color the color of the shoe
   * @param brand the brand of the shoe
   * @param size the size of the shoe
   * @throws IllegalArgumentException if brand is NIKE and kind is DRESS
   */
  public Shoe(Kind kind, Color color, Brand brand, double size) {
    if (brand == Brand.NIKE && kind == Kind.DRESS) {
      throw new IllegalArgumentException("NIKE cannot be a DRESS shoe.");
    }

    this.kind = kind;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }

  /**
   * Gets the kind of the shoe.
   *
   * @return the shoe kind/type
   */
  public Kind getKind() {
    return kind;
  }

  /**
   * Gets the color of the shoe.
   *
   * @return the shoe color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets the brand of the shoe.
   *
   * @return the shoe brand
   */
  public Brand getBrand() {
    return brand;
  }

  /**
   * Gets the size of the shoe.
   *
   * @return the shoe size
   */
  public double getSize() {
    return size;
  }

  /**
   * Returns a string summary of the shoe.
   *
   * @return a formatted string describing the shoe
   */

  public String toString() {
    String kindText = "";
    String colorText = "";
    String brandText = "";

    switch (kind) {
      case ATHLETIC:
        kindText = "Athletic";
        break;
      case DRESS:
        kindText = "Dress";
        break;
      case CASUAL:
        kindText = "Casual";
        break;
    }

    switch (brand) {
      case NIKE:
        brandText = "Nike";
        break;
      case ADIDAS:
        brandText = "Adidas";
        break;
      case GUCCI:
        brandText = "Gucci";
        break;
    }

    switch (color) {
      case BLACK:
        colorText = "Black";
        break;
      case WHITE:
        colorText = "White";
        break;
      default:
        String s = color.name().toLowerCase();
        colorText = s.substring(0, 1).toUpperCase() + s.substring(1);
        break;
    }

    return "Shoe [kind=" + kindText +
        ", color=" + colorText +
        ", brand=" + brandText +
        ", size=" + size + "]";
  }
}
