package uk.tranter.itunes.playlister;

import java.util.List;

/**
 * A simple representation of a playlist
 */
public final class Playlist {

    private final List<Track> tracks;
    private final String name;

    public Playlist(String name, List<Track> tracks) {
        this.tracks = tracks;
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }


    public String getName() {
        return name;
    }
}
