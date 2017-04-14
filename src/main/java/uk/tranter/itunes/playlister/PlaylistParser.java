package uk.tranter.itunes.playlister;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Parses a list of strings into a {@link Playlist}
 */
public final class PlaylistParser {
    public Playlist parse(List<String> lines) {

        List<String> ids = lines.stream()
                .filter(e -> e.startsWith("<key>Track ID"))
                .map(e -> e.substring(e.indexOf("<integer>") + 9, e.lastIndexOf("</integer")))
                .collect(toList());

        List<String> titles = lines.stream()
                .filter(e -> e.startsWith("<key>Name"))
                .map(e -> e.substring(e.indexOf("<string>") + 8, e.lastIndexOf("</string")))
                .collect(toList());

        List<String> artists = lines.stream()
                .filter(e -> e.startsWith("<key>Artist"))
                .map(e -> e.substring(e.indexOf("<string>") + 8, e.lastIndexOf("</string")))
                .collect(toList());

        List<Track> tracks = new ArrayList<>(artists.size());
        for (int i = 0; i < artists.size(); i++) {
            tracks.add(new Track(ids.get(i), artists.get(i), titles.get(i)));
        }

        final List<String> orderedIds = ids.stream().skip(artists.size()).collect(toList());
        tracks.sort(Comparator.comparing(e -> orderedIds.indexOf(e.getId())));

        return new Playlist(tracks);
    }
}
