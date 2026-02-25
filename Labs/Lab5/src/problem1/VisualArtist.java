package problem1;
import java.util.Arrays;

/**
 * Abstract subclass for Artists who exhibit visual works:
 * Painters and Photographers.
 *
 * Additional field (from prompt):
 * - exhibits (String[])
 */
public abstract class VisualArtist extends Artist {
  private final String[] exhibits;

  public VisualArtist(Name name, int age, String[] genres, String[] awards, String[] exhibits) {
    super(name, age, genres, awards);
    this.exhibits = exhibits != null ? exhibits : new String[0];
  }

  public String[] getExhibits() {
    return Arrays.copyOf(exhibits, exhibits.length);
  }

  @Override
  public String toString() {
    return super.toString().replace("}",
        ", exhibits=" + Arrays.toString(exhibits) + "}");
  }

}