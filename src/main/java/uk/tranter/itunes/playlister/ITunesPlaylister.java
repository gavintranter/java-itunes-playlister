package uk.tranter.itunes.playlister;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Prints a simple representation of an iTunes Playlist to the console.
 */
public final class ITunesPlaylister {
    public static void main(String[] args) throws IOException {
        final PlaylistParser playlistParser = new PlaylistParser();

        File[] files = new File("/Users/Gavin/Documents/playlists").listFiles();

        Arrays.stream(files)
                .filter(f -> f.isFile() && !f.isHidden())
                .map(f -> {
                    List<String> lines = null;
                    try {
                        lines = Files.readAllLines(Paths.get(f.getAbsolutePath()));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    return playlistParser.parse(lines);
                })
                .forEach(p -> {
                    System.out.println("\n\n====================\n" + p.getName() + "\n");
                    p.getTracks().forEach(System.out::println);
                });
    }
}