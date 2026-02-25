package problem1;

/**
 * Dancer: a type of Artist who works in movies, TV series, and other multimedia.
 * Extends MultimediaArtist, inheriting movies, series, and otherMultimedia fields.
 */
public class Dancer extends MultimediaArtist {

    /**
     * No-arg constructor: creates a Dancer with default empty values.
     * Useful when you want to create a Dancer first and set fields later via setters.
     */
    public Dancer() {
        this(new Name("Unknown", "Unknown"), 2000,
             new String[0], new String[0],
             new String[0], new String[0], new String[0]);
    }

    /**
     * Copy constructor: creates a new Dancer as an independent copy of an existing one.
     * Useful when you want to duplicate a Dancer without sharing references.
     *
     * @param other the Dancer to copy
     */
    public Dancer(Dancer other) {
        this(other.getName(), other.getBirthYear(),
             other.getGenres(), other.getAwards(),
             other.getMovies(), other.getSeries(), other.getOtherMultimedia());
    }

    /**
     * @param name            the dancer's name
     * @param birthYear       year the dancer was born
     * @param genres          genres the dancer works in (e.g. "Ballet", "Hip-Hop")
     * @param awards          awards already received
     * @param movies          movies the dancer has worked on
     * @param series          TV series the dancer has worked on
     * @param otherMultimedia other multimedia content the dancer has worked on
     */
    public Dancer(Name name, int birthYear, String[] genres, String[] awards,
                  String[] movies, String[] series, String[] otherMultimedia) {
        super(name, birthYear, genres, awards, movies, series, otherMultimedia);
    }

    @Override
    public String toString() {
        return "Dancer: " + super.toString();
    }
}
