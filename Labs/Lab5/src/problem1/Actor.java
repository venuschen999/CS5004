package problem1;

/**
 * Actor: a type of Artist who works in movies, TV series, and other multimedia.
 * Extends MultimediaArtist, inheriting movies, series, and otherMultimedia fields.
 */
public class Actor extends MultimediaArtist {

    /**
     * No-arg constructor: creates an Actor with default empty values.
     * Useful when you want to create an Actor first and set fields later via setters.
     */
    public Actor() {
        this(new Name("Unknown", "Unknown"), 2000,
             new String[0], new String[0],
             new String[0], new String[0], new String[0]);
    }

    /**
     * Copy constructor: creates a new Actor as an independent copy of an existing one.
     * Useful when you want to duplicate an Actor without sharing references.
     *
     * @param other the Actor to copy
     */
    public Actor(Actor other) {
        this(other.getName(), other.getBirthYear(),
             other.getGenres(), other.getAwards(),
             other.getMovies(), other.getSeries(), other.getOtherMultimedia());
    }

    /**
     * @param name            the actor's name
     * @param birthYear       year the actor was born
     * @param genres          genres the actor works in (e.g. "Drama", "Comedy")
     * @param awards          awards already received
     * @param movies          movies the actor has worked on
     * @param series          TV series the actor has worked on
     * @param otherMultimedia other multimedia content the actor has worked on
     */
    public Actor(Name name, int birthYear, String[] genres, String[] awards,
                 String[] movies, String[] series, String[] otherMultimedia) {
        super(name, birthYear, genres, awards, movies, series, otherMultimedia);
    }

    @Override
    public String toString() {
        return "Actor: " + super.toString();
    }
}
