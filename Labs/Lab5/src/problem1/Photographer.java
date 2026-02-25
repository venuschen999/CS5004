package problem1;

/**
 * Photographer: a type of Artist whose work is displayed in exhibits.
 * Extends VisualArtist, inheriting the exhibits field.
 */
public class Photographer extends VisualArtist {

    /**
     * No-arg constructor: creates a Photographer with default empty values.
     * Useful when you want to create a Photographer first and set fields later via setters.
     */
    public Photographer() {
        this(new Name("Unknown", "Unknown"), 2000,
             new String[0], new String[0], new String[0]);
    }

    /**
     * Copy constructor: creates a new Photographer as an independent copy of an existing one.
     * Useful when you want to duplicate a Photographer without sharing references.
     *
     * @param other the Photographer to copy
     */
    public Photographer(Photographer other) {
        this(other.getName(), other.getBirthYear(),
             other.getGenres(), other.getAwards(), other.getExhibits());
    }

    /**
     * @param name       the photographer's name
     * @param birthYear  year the photographer was born
     * @param genres     genres the photographer works in (e.g. "Portrait", "Landscape")
     * @param awards     awards already received
     * @param exhibits   exhibits where the photographer's work has been shown
     */
    public Photographer(Name name, int birthYear, String[] genres, String[] awards,
                        String[] exhibits) {
        super(name, birthYear, genres, awards, exhibits);
    }

    @Override
    public String toString() {
        return "Photographer: " + super.toString();
    }
}
