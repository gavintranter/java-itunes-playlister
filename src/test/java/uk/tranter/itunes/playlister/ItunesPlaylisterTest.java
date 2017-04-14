package uk.tranter.itunes.playlister;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class ItunesPlaylisterTest {

    private final static List<String> track1 = Arrays.asList(
            "<key>1000</key>",
            "<key>Track ID</key><integer>1000</integer>",
            "<key>Name</key><string>Song 1</string>",
            "<key>Artist</key><string>Artist 1</string>");

    private final static List<String> track2 = Arrays.asList(
            "<key>1000</key>",
            "<key>Track ID</key><integer>2000</integer>",
            "<key>Name</key><string>Song 2</string>",
            "<key>Artist</key><string>Artist 2</string>");

    @Test
    void shouldParseSingleTrackPlaylistXmlIntoPlaylist() {
        List<String> lines = new ArrayList<>();
        lines.addAll(track1);
        lines.add("<key>Track ID</key><integer>1000</integer>");

        PlaylistParser playlistParser = new PlaylistParser();

        Playlist playlist = playlistParser.parse(lines);

        assertThat(playlist.getTracks()).contains(new Track("1000", "Artist 1", "Song 1"));
    }

    @Test
    void shouldMaintainOrderWhenMultipleTracks() {
        List<String> lines = new ArrayList<>();
        lines.addAll(track1);
        lines.addAll(track2);
        lines.add("<key>Track ID</key><integer>2000</integer>");
        lines.add("<key>Track ID</key><integer>1000</integer>");

        PlaylistParser playlistParser = new PlaylistParser();

        Playlist playlist = playlistParser.parse(lines);

        Track track1 = new Track("1000", "Artist 1", "Song 1");
        Track track2 = new Track("2000", "Artist 2", "Song 2");
        assertThat(playlist.getTracks()).containsExactly(track2, track1);
    }
}