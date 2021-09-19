package org.example.JukeBox.ModelClass;

import org.example.JukeBox.Interface.PlaylistDetailsType;

import java.sql.Time;

public class Songs implements PlaylistDetailsType {

    int songId;
    String songName;
    String artist;
    String genre;
    String album;
    String songSrcFile;
    Time songDuration;

    public Songs() {
    }
    public Songs(int songId, String songName, String artist, String genre, String album, Time songDuration,String songSrcFile ){
        this.songId = songId;
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.songSrcFile = songSrcFile;
        this.songDuration = songDuration;
    }


    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSongSrcFile() {
        return songSrcFile;
    }

    public void setSongSrcFile(String songSrcFile) {
        this.songSrcFile = songSrcFile;
    }

    public Time getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(Time songDuration) {
        this.songDuration = songDuration;
    }

    @Override
    public String toString() {
        return songId +
                "  " + songName +
                "  " + artist +
                "  " + genre +
                "  " + album +
                "  " + songDuration +
                "  " + songSrcFile;
    }
}


