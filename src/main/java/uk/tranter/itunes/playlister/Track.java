package uk.tranter.itunes.playlister;

import java.util.Objects;

/**
 * Represents a track with in a {@link Playlist}
 */
public final class Track {

    private static final String TRACK_FORMAT = "%s - %s";

    private final String id;
    private final String artist;
    private final String title;

    public Track(String id, String artist, String title) {
        this.id = id;
        this.artist = artist;
        this.title = title;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return String.format(TRACK_FORMAT, artist, title);
    }
}