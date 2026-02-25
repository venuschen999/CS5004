package problem1;

/**
 * Musician: a type of Artist who records music under a label.
 * Adds recordingCompany and lastRecordAlbum on top of what Artist provides.
 */
public class Musician extends Artist {

    private String recordingCompany;
    private String lastRecordAlbum;

    /**
     * No-arg constructor: creates a Musician with default empty values.
     * Useful when you want to create a Musician first and set fields later via setters.
     */
    public Musician() {
        this(new Name("Unknown", "Unknown"), 2000,
             new String[0], new String[0], "Unknown", "Unknown");
    }

    /**
     * Copy constructor: creates a new Musician as an independent copy of an existing one.
     * Useful when you want to duplicate a Musician without sharing references.
     *
     * @param other the Musician to copy
     */
    public Musician(Musician other) {
        this(other.getName(), other.getBirthYear(),
             other.getGenres(), other.getAwards(),
             other.recordingCompany, other.lastRecordAlbum);
    }

    /**
     * @param name              the musician's name
     * @param birthYear         year the musician was born
     * @param genres            genres the musician works in (e.g. "Jazz", "Rock")
     * @param awards            awards already received
     * @param recordingCompany  the label or company they record with
     * @param lastRecordAlbum   title of their most recently recorded album
     */
    public Musician(Name name, int birthYear, String[] genres, String[] awards,
                    String recordingCompany, String lastRecordAlbum) {
        super(name, birthYear, genres, awards);
        this.recordingCompany = recordingCompany;
        this.lastRecordAlbum  = lastRecordAlbum;
    }

    public String getRecordingCompany() { return recordingCompany; }
    public String getLastRecordAlbum()  { return lastRecordAlbum;  }

    /** Updates the recording company, e.g. if the musician switches labels. */
    public void setRecordingCompany(String recordingCompany) {
        this.recordingCompany = recordingCompany;
    }

    /** Updates the last album, e.g. when a new album is released. */
    public void setLastRecordAlbum(String lastRecordAlbum) {
        this.lastRecordAlbum = lastRecordAlbum;
    }

    @Override
    public String toString() {
        return "Musician: " + super.toString() +
               "\n  Recording Company: " + recordingCompany +
               "\n  Last Album: "        + lastRecordAlbum;
    }
}
