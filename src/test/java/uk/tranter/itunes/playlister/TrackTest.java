package uk.tranter.itunes.playlister;

import org.junit.jupiter.api.Test;

import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static org.assertj.core.api.Assertions.assertThat;


final class TrackTest {

    @Test
    void shouldDisplayOnlyArtistAndTitleSeparatedByDash() {
        assertThat(new Track("1", "Me", "My Song")).hasToString("Me - My Song");
    }

    @Test
    void shouldEqualsShouldConformToEqualsContract() {
        forClass(Track.class).verify();
    }
}