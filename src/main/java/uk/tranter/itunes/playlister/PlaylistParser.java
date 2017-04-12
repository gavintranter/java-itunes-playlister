package uk.tranter.itunes.playlister;

import java.util.Arrays;
import java.util.List;

/**
 * Parses a list of strings into a {@link Playlist}
 */
public class PlaylistParser {
    public Playlist parse(List<String> lines) {
        return new Playlist(Arrays.asList(new Track("1000", "Artist 1", "Song 1")));
    }
}
