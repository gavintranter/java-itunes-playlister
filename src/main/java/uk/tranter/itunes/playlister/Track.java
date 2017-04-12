package uk.tranter.itunes.playlister;

import java.util.Objects;

/**
 * Represents a track with in a {@link Playlist}
 */
public class Track {

    private final String id;
    private final String artist;
    private final String title;

    public Track(String id, String artist, String title) {
        this.id = id;
        this.artist = artist;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id) &&
                Objects.equals(artist, track.artist) &&
                Objects.equals(title, track.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist, title);
    }
}
