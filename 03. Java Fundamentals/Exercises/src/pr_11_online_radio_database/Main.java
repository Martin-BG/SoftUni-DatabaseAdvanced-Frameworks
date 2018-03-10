package pr_11_online_radio_database;

import pr_11_online_radio_database.exceptions.*;
import pr_11_online_radio_database.model.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Song> songs = getSongs();

        int playlistLength = songs.stream()
                .map(Song::getDuration)
                .reduce(0, (d1, d2) -> d1 + d2);

        System.out.printf("Songs added: %d%n", songs.size());
        System.out.printf("Playlist length: %s%n", getPlaylistDurationString(playlistLength));
    }

    private static List<Song> getSongs() {
        List<Song> songs = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = Integer.parseInt(reader.readLine().trim());

            while (lines-- > 0) {
                String[] tokens = reader.readLine().trim().split(";");

                try {
                    if (tokens.length != 3) {
                        throw new InvalidSongException();
                    }

                    String artist = tokens[0];
                    String name = tokens[1];
                    String duration = tokens[2];

                    Song song = new Song(name, artist, duration);

                    songs.add(song);

                    System.out.println("Song added.");

                } catch (InvalidArtistNameException e) {
                    System.out.println("Artist name should be between 3 and 20 symbols.");
                } catch (InvalidSongNameException e) {
                    System.out.println("Song name should be between 3 and 30 symbols.");
                } catch (InvalidSongMinutesException e) {
                    System.out.println("Song minutes should be between 0 and 14.");
                } catch (InvalidSongSecondsException e) {
                    System.out.println("Song seconds should be between 0 and 59.");
                } catch (InvalidSongLengthException e) {
                    System.out.println("Invalid song length.");
                } catch (InvalidSongException e) {
                    System.out.println("Invalid song.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songs;
    }

    private static String getPlaylistDurationString(int playlistLength) {

        int hours = playlistLength / (60 * 60);
        playlistLength %= 60 * 60;
        int minutes = playlistLength / 60;
        int seconds = playlistLength % 60;

        return String.format("%dh %dm %ds", hours, minutes, seconds);
    }
}
