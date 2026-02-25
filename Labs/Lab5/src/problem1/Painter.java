package problem1;

/**
 * Painter: a type of Artist whose work is displayed in exhibits.
 * Extends VisualArtist, inheriting the exhibits field.
 */
public class Painter extends VisualArtist {

    /**
     * No-arg constructor: creates a Painter with default empty values.
     * Useful when you want to create a Painter first and set fields later via setters.
     */
    public Painter() {
        this(new Name("Unknown", "Unknown"), 2000,
             new String[0], new String[0], new String[0]);
    }

    /**
     * Copy constructor: creates a new Painter as an independent copy of an existing one.
     * Useful when you want to duplicate a Painter without sharing references.
     *
     * @param other the Painter to copy
     */
    public Painter(Painter other) {
        this(other.getName(), other.getBirthYear(),
             other.getGenres(), other.getAwards(), other.getExhibits());
    }

    /**
     * @param name       the painter's name
     * @param birthYear  year the painter was born
     * @param genres     genres the painter works in (e.g. "Abstract", "Realism")
     * @param awards     awards already received
     * @param exhibits   exhibits where the painter's art has been shown
     */
    public Painter(Name name, int birthYear, String[] genres, String[] awards,
                   String[] exhibits) {
        super(name, birthYear, genres, awards, exhibits);
    }

    @Override
    public String toString() {
        return "Painter: " + super.toString();
    }
}
