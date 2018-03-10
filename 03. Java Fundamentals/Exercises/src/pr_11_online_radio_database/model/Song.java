package pr_11_online_radio_database.model;

import pr_11_online_radio_database.exceptions.*;

public class Song {

    private String name;
    private String artist;
    private int durationInSeconds;

    public Song(String name, String artist, String duration)
            throws InvalidSongException {
        this.setName(name);
        this.setArtist(artist);
        this.setDuration(duration);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) throws InvalidSongNameException {
        if (name == null || name.trim().length() < 3 || name.trim().length() > 30) {
            throw new InvalidSongNameException();
        }

        this.name = name.trim();
    }

    public String getArtist() {
        return this.artist;
    }

    private void setArtist(String artist) throws InvalidArtistNameException {
        if (artist == null || artist.trim().length() < 3 || artist.trim().length() > 20) {
            throw new InvalidArtistNameException();
        }

        this.artist = artist.trim();
    }

    public int getDuration() {
        return this.durationInSeconds;
    }

    private void setDuration(String duration)
            throws InvalidSongLengthException {
        String[] tokens = duration.split(":");

        if (tokens.length != 2) {
            throw new InvalidSongLengthException();
        }

        int minutes;
        int seconds;

        try {
            minutes = Integer.parseInt(tokens[0]);
            seconds = Integer.parseInt(tokens[1]);

        } catch (Exception e) {
            throw new InvalidSongLengthException();
        }

        if (minutes < 0 || minutes > 14) {
            throw new InvalidSongMinutesException();
        }

        if (seconds < 0 || seconds > 59) {
            throw new InvalidSongSecondsException();
        }

        this.durationInSeconds = minutes * 60 + seconds;
    }
}
