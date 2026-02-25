package problem1;

/**
 * Filmmaker: a type of Artist who works in movies, TV series, and other multimedia.
 * Extends MultimediaArtist, inheriting movies, series, and otherMultimedia fields.
 */
public class Filmmaker extends MultimediaArtist {

    /**
     * No-arg constructor: creates a Filmmaker with default empty values.
     * Useful when you want to create a Filmmaker first and set fields later via setters.
     */
    public Filmmaker() {
        this(new Name("Unknown", "Unknown"), 2000,
             new String[0], new String[0],
             new String[0], new String[0], new String[0]);
    }

    /**
     * Copy constructor: creates a new Filmmaker as an independent copy of an existing one.
     * Useful when you want to duplicate a Filmmaker without sharing references.
     *
     * @param other the Filmmaker to copy
     */
    public Filmmaker(Filmmaker other) {
        this(other.getName(), other.getBirthYear(),
             other.getGenres(), other.getAwards(),
             other.getMovies(), other.getSeries(), other.getOtherMultimedia());
    }

    /**
     * @param name            the filmmaker's name
     * @param birthYear       year the filmmaker was born
     * @param genres          genres the filmmaker works in (e.g. "Documentary", "Thriller")
     * @param awards          awards already received
     * @param movies          movies the filmmaker has worked on
     * @param series          TV series the filmmaker has worked on
     * @param otherMultimedia other multimedia content the filmmaker has worked on
     */
    public Filmmaker(Name name, int birthYear, String[] genres, String[] awards,
                     String[] movies, String[] series, String[] otherMultimedia) {
        super(name, birthYear, genres, awards, movies, series, otherMultimedia);
    }

    @Override
    public String toString() {
        return "Filmmaker: " + super.toString();
    }
}
