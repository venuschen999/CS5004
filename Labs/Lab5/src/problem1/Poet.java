package problem1;

/**
 * Poet: a type of Artist who publishes poetry collections.
 * Adds publishingCompany and lastPublishedCollection on top of what Artist provides.
 */
public class Poet extends Artist {

    private String publishingCompany;
    private String lastPublishedCollection;

    /**
     * No-arg constructor: creates a Poet with default empty values.
     * Useful when you want to create a Poet first and set fields later via setters.
     */
    public Poet() {
        this(new Name("Unknown", "Unknown"), 2000,
             new String[0], new String[0], "Unknown", "Unknown");
    }

    /**
     * Copy constructor: creates a new Poet as an independent copy of an existing one.
     * Useful when you want to duplicate a Poet without sharing references.
     *
     * @param other the Poet to copy
     */
    public Poet(Poet other) {
        this(other.getName(), other.getBirthYear(),
             other.getGenres(), other.getAwards(),
             other.publishingCompany, other.lastPublishedCollection);
    }

    /**
     * @param name                    the poet's name
     * @param birthYear               year the poet was born
     * @param genres                  genres the poet works in (e.g. "Lyric", "Spoken Word")
     * @param awards                  awards already received
     * @param publishingCompany       the publishing company they work with
     * @param lastPublishedCollection title of their most recently published collection
     */
    public Poet(Name name, int birthYear, String[] genres, String[] awards,
                String publishingCompany, String lastPublishedCollection) {
        super(name, birthYear, genres, awards);
        this.publishingCompany       = publishingCompany;
        this.lastPublishedCollection = lastPublishedCollection;
    }

    public String getPublishingCompany()       { return publishingCompany;       }
    public String getLastPublishedCollection() { return lastPublishedCollection; }

    /** Updates the publishing company, e.g. if the poet switches publishers. */
    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    /** Updates the last collection, e.g. when a new collection is released. */
    public void setLastPublishedCollection(String lastPublishedCollection) {
        this.lastPublishedCollection = lastPublishedCollection;
    }

    @Override
    public String toString() {
        return "Poet: " + super.toString() +
               "\n  Publishing Company: " + publishingCompany +
               "\n  Last Collection: "    + lastPublishedCollection;
    }
}
