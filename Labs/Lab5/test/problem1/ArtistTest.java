package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for all Artist subclasses.
 * Testing strategy:
 *   - Test no-arg constructor (default values)
 *   - Test full constructor (field assignment)
 *   - Test copy constructor (independence from original)
 *   - Test receiveAward() (inherited from Artist)
 *   - Test setBirthYear() and getAge() (computed field)
 *   - Test subclass-specific getters and setters
 */
class ArtistTest {

    // Shared test data
    private Name name;
    private String[] genres;
    private String[] awards;
    private String[] movies;
    private String[] series;
    private String[] otherMultimedia;
    private String[] exhibits;

    @BeforeEach
    void setUp() {
        name            = new Name("John", "Doe");
        genres          = new String[]{"Drama", "Comedy"};
        awards          = new String[]{"Oscar"};
        movies          = new String[]{"Movie A", "Movie B"};
        series          = new String[]{"Series A"};
        otherMultimedia = new String[]{"Short Film A"};
        exhibits        = new String[]{"Gallery A", "Gallery B"};
    }

    // ------------------------------------------------------------------
    // Name
    // ------------------------------------------------------------------

    @Test
    void testName_getFirstName() {
        assertEquals("John", name.getFirstName());
    }

    @Test
    void testName_getLastName() {
        assertEquals("Doe", name.getLastName());
    }

    @Test
    void testName_getFullName() {
        assertEquals("John Doe", name.getFullName());
    }

    // ------------------------------------------------------------------
    // Actor
    // ------------------------------------------------------------------

    @Test
    void testActor_noArgConstructor_defaultValues() {
        Actor actor = new Actor();
        assertEquals("Unknown", actor.getName().getFirstName());
        assertEquals(2000, actor.getBirthYear());
        assertEquals(0, actor.getGenres().length);
        assertEquals(0, actor.getAwards().length);
        assertEquals(0, actor.getMovies().length);
    }

    @Test
    void testActor_fullConstructor_fieldsAssignedCorrectly() {
        Actor actor = new Actor(name, 1990, genres, awards, movies, series, otherMultimedia);
        assertEquals("John Doe", actor.getName().getFullName());
        assertEquals(1990, actor.getBirthYear());
        assertArrayEquals(genres, actor.getGenres());
        assertArrayEquals(movies, actor.getMovies());
        assertArrayEquals(series, actor.getSeries());
        assertArrayEquals(otherMultimedia, actor.getOtherMultimedia());
    }

    @Test
    void testActor_copyConstructor_isIndependentCopy() {
        Actor original = new Actor(name, 1990, genres, awards, movies, series, otherMultimedia);
        Actor copy     = new Actor(original);

        // same values
        assertEquals(original.getName().getFullName(), copy.getName().getFullName());
        assertEquals(original.getBirthYear(), copy.getBirthYear());

        // modifying original does not affect copy
        original.setName(new Name("Jane", "Smith"));
        assertEquals("John Doe", copy.getName().getFullName());
    }

    @Test
    void testActor_getAge_computedFromBirthYear() {
        Actor actor = new Actor(name, 1990, genres, awards, movies, series, otherMultimedia);
        int expectedAge = java.time.Year.now().getValue() - 1990;
        assertEquals(expectedAge, actor.getAge());
    }

    @Test
    void testActor_receiveAward_addsToAwards() {
        Actor actor = new Actor(name, 1990, genres, awards, movies, series, otherMultimedia);
        actor.receiveAward("Golden Globe");
        assertEquals(2, actor.getAwards().length);
        assertEquals("Golden Globe", actor.getAwards()[1]);
    }

    // ------------------------------------------------------------------
    // Dancer
    // ------------------------------------------------------------------

    @Test
    void testDancer_noArgConstructor_defaultValues() {
        Dancer dancer = new Dancer();
        assertEquals("Unknown", dancer.getName().getFirstName());
        assertEquals(0, dancer.getMovies().length);
    }

    @Test
    void testDancer_fullConstructor_fieldsAssignedCorrectly() {
        Dancer dancer = new Dancer(name, 1985, genres, awards, movies, series, otherMultimedia);
        assertEquals("John Doe", dancer.getName().getFullName());
        assertArrayEquals(movies, dancer.getMovies());
    }

    @Test
    void testDancer_copyConstructor_isIndependentCopy() {
        Dancer original = new Dancer(name, 1985, genres, awards, movies, series, otherMultimedia);
        Dancer copy     = new Dancer(original);
        assertEquals(original.getBirthYear(), copy.getBirthYear());
        original.setBirthYear(1990);
        assertEquals(1985, copy.getBirthYear());
    }

    // ------------------------------------------------------------------
    // Filmmaker
    // ------------------------------------------------------------------

    @Test
    void testFilmmaker_noArgConstructor_defaultValues() {
        Filmmaker filmmaker = new Filmmaker();
        assertEquals("Unknown", filmmaker.getName().getFirstName());
        assertEquals(0, filmmaker.getSeries().length);
    }

    @Test
    void testFilmmaker_fullConstructor_fieldsAssignedCorrectly() {
        Filmmaker filmmaker = new Filmmaker(name, 1975, genres, awards, movies, series, otherMultimedia);
        assertEquals("John Doe", filmmaker.getName().getFullName());
        assertArrayEquals(series, filmmaker.getSeries());
    }

    @Test
    void testFilmmaker_copyConstructor_isIndependentCopy() {
        Filmmaker original = new Filmmaker(name, 1975, genres, awards, movies, series, otherMultimedia);
        Filmmaker copy     = new Filmmaker(original);
        assertEquals(original.getName().getFullName(), copy.getName().getFullName());
        original.setName(new Name("Jane", "Smith"));
        assertEquals("John Doe", copy.getName().getFullName());
    }

    // ------------------------------------------------------------------
    // Painter
    // ------------------------------------------------------------------

    @Test
    void testPainter_noArgConstructor_defaultValues() {
        Painter painter = new Painter();
        assertEquals("Unknown", painter.getName().getFirstName());
        assertEquals(0, painter.getExhibits().length);
    }

    @Test
    void testPainter_fullConstructor_fieldsAssignedCorrectly() {
        Painter painter = new Painter(name, 1980, genres, awards, exhibits);
        assertEquals("John Doe", painter.getName().getFullName());
        assertArrayEquals(exhibits, painter.getExhibits());
    }

    @Test
    void testPainter_copyConstructor_isIndependentCopy() {
        Painter original = new Painter(name, 1980, genres, awards, exhibits);
        Painter copy     = new Painter(original);
        assertEquals(original.getBirthYear(), copy.getBirthYear());
        original.setBirthYear(1990);
        assertEquals(1980, copy.getBirthYear());
    }

    // ------------------------------------------------------------------
    // Photographer
    // ------------------------------------------------------------------

    @Test
    void testPhotographer_noArgConstructor_defaultValues() {
        Photographer photographer = new Photographer();
        assertEquals("Unknown", photographer.getName().getFirstName());
        assertEquals(0, photographer.getExhibits().length);
    }

    @Test
    void testPhotographer_fullConstructor_fieldsAssignedCorrectly() {
        Photographer photographer = new Photographer(name, 1978, genres, awards, exhibits);
        assertEquals("John Doe", photographer.getName().getFullName());
        assertArrayEquals(exhibits, photographer.getExhibits());
    }

    @Test
    void testPhotographer_copyConstructor_isIndependentCopy() {
        Photographer original = new Photographer(name, 1978, genres, awards, exhibits);
        Photographer copy     = new Photographer(original);
        assertEquals(original.getName().getFullName(), copy.getName().getFullName());
        original.setName(new Name("Jane", "Smith"));
        assertEquals("John Doe", copy.getName().getFullName());
    }

    // ------------------------------------------------------------------
    // Musician
    // ------------------------------------------------------------------

    @Test
    void testMusician_noArgConstructor_defaultValues() {
        Musician musician = new Musician();
        assertEquals("Unknown", musician.getName().getFirstName());
        assertEquals("Unknown", musician.getRecordingCompany());
        assertEquals("Unknown", musician.getLastRecordAlbum());
    }

    @Test
    void testMusician_fullConstructor_fieldsAssignedCorrectly() {
        Musician musician = new Musician(name, 1988, genres, awards, "Sony Music", "Greatest Hits");
        assertEquals("John Doe", musician.getName().getFullName());
        assertEquals("Sony Music", musician.getRecordingCompany());
        assertEquals("Greatest Hits", musician.getLastRecordAlbum());
    }

    @Test
    void testMusician_copyConstructor_isIndependentCopy() {
        Musician original = new Musician(name, 1988, genres, awards, "Sony Music", "Greatest Hits");
        Musician copy     = new Musician(original);
        assertEquals(original.getRecordingCompany(), copy.getRecordingCompany());
        original.setRecordingCompany("Warner Bros");
        assertEquals("Sony Music", copy.getRecordingCompany());
    }

    @Test
    void testMusician_setters_updateFieldsCorrectly() {
        Musician musician = new Musician(name, 1988, genres, awards, "Sony Music", "Greatest Hits");
        musician.setRecordingCompany("Warner Bros");
        musician.setLastRecordAlbum("New Album");
        assertEquals("Warner Bros", musician.getRecordingCompany());
        assertEquals("New Album", musician.getLastRecordAlbum());
    }

    // ------------------------------------------------------------------
    // Poet
    // ------------------------------------------------------------------

    @Test
    void testPoet_noArgConstructor_defaultValues() {
        Poet poet = new Poet();
        assertEquals("Unknown", poet.getName().getFirstName());
        assertEquals("Unknown", poet.getPublishingCompany());
        assertEquals("Unknown", poet.getLastPublishedCollection());
    }

    @Test
    void testPoet_fullConstructor_fieldsAssignedCorrectly() {
        Poet poet = new Poet(name, 1970, genres, awards, "Penguin Books", "Leaves of Verse");
        assertEquals("John Doe", poet.getName().getFullName());
        assertEquals("Penguin Books", poet.getPublishingCompany());
        assertEquals("Leaves of Verse", poet.getLastPublishedCollection());
    }

    @Test
    void testPoet_copyConstructor_isIndependentCopy() {
        Poet original = new Poet(name, 1970, genres, awards, "Penguin Books", "Leaves of Verse");
        Poet copy     = new Poet(original);
        assertEquals(original.getPublishingCompany(), copy.getPublishingCompany());
        original.setPublishingCompany("HarperCollins");
        assertEquals("Penguin Books", copy.getPublishingCompany());
    }

    @Test
    void testPoet_setters_updateFieldsCorrectly() {
        Poet poet = new Poet(name, 1970, genres, awards, "Penguin Books", "Leaves of Verse");
        poet.setPublishingCompany("HarperCollins");
        poet.setLastPublishedCollection("New Collection");
        assertEquals("HarperCollins", poet.getPublishingCompany());
        assertEquals("New Collection", poet.getLastPublishedCollection());
    }

    // ------------------------------------------------------------------
    // Artist.receiveAward() and setBirthYear() — shared behavior
    // ------------------------------------------------------------------

    @Test
    void testReceiveAward_multipleAwards_allAppended() {
        Musician musician = new Musician(name, 1988, genres, awards, "Sony Music", "Greatest Hits");
        musician.receiveAward("Grammy");
        musician.receiveAward("MTV Award");
        assertEquals(3, musician.getAwards().length);
        assertEquals("Grammy",    musician.getAwards()[1]);
        assertEquals("MTV Award", musician.getAwards()[2]);
    }

    @Test
    void testSetBirthYear_updatesAgeAutomatically() {
        Actor actor = new Actor(name, 1990, genres, awards, movies, series, otherMultimedia);
        actor.setBirthYear(2000);
        int expectedAge = java.time.Year.now().getValue() - 2000;
        assertEquals(2000, actor.getBirthYear());
        assertEquals(expectedAge, actor.getAge());
    }

    @Test
    void testSetBirthYear_invalidYear_throwsException() {
        Actor actor = new Actor(name, 1990, genres, awards, movies, series, otherMultimedia);
        assertThrows(IllegalArgumentException.class, () -> actor.setBirthYear(3000));
    }

    @Test
    void testConstructor_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Actor(null, 1990, genres, awards, movies, series, otherMultimedia));
    }
}
