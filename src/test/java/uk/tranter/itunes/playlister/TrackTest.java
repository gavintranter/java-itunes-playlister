package uk.tranter.itunes.playlister;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class TrackTest {

    @Test
    void shouldDisplayOnlyArtistAndTitleSeparatedByDash() {
        assertThat(new Track("1", "Me", "My Song")).hasToString("Me - My Song");
    }
}