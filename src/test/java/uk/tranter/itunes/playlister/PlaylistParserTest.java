package uk.tranter.itunes.playlister;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class PlaylistParserTest {

    private final static List<String> track1 = Arrays.asList(
            "<key>1000</key>",
            "<key>Track ID</key><integer>1000</integer>",
            "<key>Name</key><string>Track 1</string>",
            "<key>Artist</key><string>Artist 1</string>");

    private final static List<String> track2 = Arrays.asList(
            "<key>1000</key>",
            "<key>Track ID</key><integer>2000</integer>",
            "<key>Name</key><string>Track 2</string>",
            "<key>Artist</key><string>Artist 2</string>");

    private PlaylistParser playlistParser = new PlaylistParser();

    @Test
    void shouldParseSingleTrackPlaylistXmlIntoPlaylist() {
        List<String> lines = new ArrayList<>();
        lines.addAll(track1);
        lines.add("<key>Track ID</key><integer>1000</integer>");

        Playlist playlist = playlistParser.parse(lines);

        assertThat(playlist.getTracks()).containsExactly(new Track("1000", "Artist 1", "Track 1"));
    }

    @Test
    void shouldMaintainOrderWhenMultipleTracks() {
        List<String> lines = new ArrayList<>();
        lines.addAll(track1);
        lines.addAll(track2);
        lines.add("<key>Track ID</key><integer>2000</integer>");
        lines.add("<key>Track ID</key><integer>1000</integer>");

        Playlist playlist = playlistParser.parse(lines);

        Track track1 = new Track("1000", "Artist 1", "Track 1");
        Track track2 = new Track("2000", "Artist 2", "Track 2");
        assertThat(playlist.getTracks()).containsExactly(track2, track1);
    }

    @Test
    void shouldNamePlaylistUsingPlaylistNameFromPlaylistXml() {
        List<String> lines = new ArrayList<>();
        lines.addAll(track1);
        lines.add("<key>Name</key><string>Playlist 01</string>");
        lines.add("<key>Track ID</key><integer>1000</integer>");

        Playlist playlist = playlistParser.parse(lines);

        assertThat(playlist.getName()).isEqualTo("Playlist 01");
    }

    @Test
    void shouldHandleAmpersandInTrackTitle() {
        List<String> lines = Arrays.asList("<key>1000</key>",
                "<key>Track ID</key><integer>1000</integer>",
                "<key>Name</key><string>Track 1 &#38;</string>",
                "<key>Artist</key><string>Artist 1</string>",
                "<key>Name</key><string>Playlist 01</string>",
                "<key>Track ID</key><integer>1000</integer>");

        Playlist playlist = playlistParser.parse(lines);

        assertThat(playlist.getTracks()).containsExactly(new Track("1000", "Artist 1", "Track 1 &"));
    }

    @Test
    void shouldHandleAmpersandInTrackArtist() {
        List<String> lines = Arrays.asList("<key>1000</key>",
                "<key>Track ID</key><integer>1000</integer>",
                "<key>Name</key><string>Track 1</string>",
                "<key>Artist</key><string>Artist 1 &#38;</string>",
                "<key>Name</key><string>Playlist 01</string>",
                "<key>Track ID</key><integer>1000</integer>");

        Playlist playlist = playlistParser.parse(lines);

        assertThat(playlist.getTracks()).containsExactly(new Track("1000", "Artist 1 &", "Track 1"));
    }

    @Test
    void shouldHandleITunesPlaylistFile() throws IOException {
        Path path = Paths.get("src/test/resources/test_playlist.xml");

        Playlist playlist = playlistParser.parse(Files.readAllLines(path));

        assertThat(playlist.getTracks()).hasSize(19).containsSubsequence(
                new Track("1008", "Artist 08", "Track 08"),
                new Track("1009", "Artist 09", "Track 09"),
                new Track("1010", "Artist 10", "Track 10")
        );
    }
}