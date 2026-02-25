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
    this.exhibits = safeCopy(exhibits);
  }

  public String[] getExhibits() {
    return Arrays.copyOf(exhibits, exhibits.length);
  }

  @Override
  public String toString() {
    return super.toString().replace("}",
        ", exhibits=" + Arrays.toString(exhibits) + "}");
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    VisualArtist that = (VisualArtist) o;
    return Arrays.equals(exhibits, that.exhibits);
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + Arrays.hashCode(exhibits);
  }
}