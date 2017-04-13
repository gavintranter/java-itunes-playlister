package uk.tranter.itunes.playlister;

import java.util.Arrays;
import java.util.List;

/**
 * Parses a list of strings into a {@link Playlist}
 */
public class PlaylistParser {
    public Playlist parse(List<String> lines) {
        Track track1 = new Track("1000", "Artist 1", "Song 1");
        Track track2 = new Track("2000", "Artist 2", "Song 2");
        return new Playlist(Arrays.asList(track2, track1));
    }
}
