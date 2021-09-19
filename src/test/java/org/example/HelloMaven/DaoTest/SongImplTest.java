package org.example.HelloMaven.DaoTest;

import org.example.JukeBox.DaoClass.AccountImpl;
import org.example.JukeBox.DaoClass.SongImpl;
import org.example.JukeBox.ModelClass.Songs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SongImplTest {

    SongImpl songImpl;

    @BeforeEach
    void setUp(){
        songImpl = new SongImpl();

    }

    @AfterEach
    void tearDown(){
        songImpl = null;
    }

    @Test
    public void returnAllSongsInDatabase(){
        List<Songs> songsList = new ArrayList<>();
        songsList = songImpl.allSongs();
        assertEquals(7,songsList.size());
        assertNotEquals(0,songsList.size());
        assertEquals(10002,songsList.get(1).getSongId());
        assertEquals("Rising",songsList.get(3).getAlbum());
        assertEquals("Raju",songsList.get(5).getArtist());
        assertEquals("Pop",songsList.get(6).getGenre());
    }

    @Test
    public void givenGenreSortTheSonglist(){
        List<Songs> songsList = new ArrayList<>();
        songsList = songImpl.sortSongs("Genre","pop");
        assertEquals(3,songsList.size());
        assertNotEquals(0,songsList.size());
        assertEquals(10007,songsList.get(1).getSongId());
        assertEquals("World Cup",songsList.get(1).getAlbum());
        assertEquals("Raju",songsList.get(0).getArtist());
        assertEquals("Pop",songsList.get(1).getGenre());

    }

    @Test
    public void givenAlbumSortTheSonglist(){
        List<Songs> songsList = new ArrayList<>();
        songsList = songImpl.sortSongs("Album","rising");
        assertEquals(3,songsList.size());
        assertNotEquals(0,songsList.size());
        assertEquals(10006,songsList.get(1).getSongId());
        assertEquals("Rising",songsList.get(1).getAlbum());
        assertEquals("Raju",songsList.get(0).getArtist());
        assertEquals("Jazz",songsList.get(2).getGenre());

    }

    @Test
    public void givenArtistSortTheSonglist(){
        List<Songs> songsList = new ArrayList<>();
        songsList = songImpl.sortSongs("Artist","shekhar");
        assertEquals(2,songsList.size());
        assertNotEquals(0,songsList.size());
        assertEquals(10005,songsList.get(1).getSongId());
        assertEquals("Unity",songsList.get(1).getAlbum());
        assertEquals("Shekhar",songsList.get(0).getArtist());
        assertEquals("Jazz",songsList.get(1).getGenre());

    }
}
