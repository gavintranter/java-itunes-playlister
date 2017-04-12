package uk.tranter.itunes.playlister;

import java.util.List;

/**
 * A simple representation of a playlist
 */
public class Playlist {

    private final List<Track> tracks;

    public Playlist(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
