package problem1;

import java.util.Arrays;

/**
 * MultimediaArtist: intermediate abstract class for Actor, Dancer, Filmmaker.
 *
 * Why exist:
 * Actor, Dancer, and Filmmaker all share movies/series/otherMultimedia.
 * we do not want to copy those 3 fields into all 3 classes — duplicate codes.

 *
 * Inheritance chain:
 *   Artist → MultimediaArtist → Actor
 *                             → Dancer
 *                             → Filmmaker
 */
public abstract class MultimediaArtist extends Artist {

    private final String[] movies;
    private final String[] series;
    private final String[] otherMultimedia;

    public MultimediaArtist(Name name, int age, String[] genres, String[] awards,
                            String[] movies, String[] series, String[] otherMultimedia) {
        super(name, age, genres, awards);  // pass shared fields up to Artist
        this.movies         = movies         != null ? movies         : new String[0];
        this.series         = series         != null ? series         : new String[0];
        this.otherMultimedia = otherMultimedia != null ? otherMultimedia : new String[0];
    }

    public String[] getMovies()          { return movies;          }
    public String[] getSeries()          { return series;          }
    public String[] getOtherMultimedia() { return otherMultimedia; }

  /**
   * Appends movies, series, and otherMultimedia to the base Artist description.
   */
    @Override
    public String toString() {
        return super.toString() +
               "\n  Movies: "          + Arrays.toString(movies) +
               "\n  Series: "          + Arrays.toString(series) +
               "\n  Other Multimedia: " + Arrays.toString(otherMultimedia);
    }
}
