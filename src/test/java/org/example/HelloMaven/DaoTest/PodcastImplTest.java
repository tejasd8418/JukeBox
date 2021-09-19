package org.example.HelloMaven.DaoTest;

import org.example.JukeBox.DaoClass.PodcastImpl;
import org.example.JukeBox.ModelClass.Podcast;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PodcastImplTest {
    PodcastImpl podcastImpl;

    @BeforeEach
    void setUp(){
        podcastImpl = new PodcastImpl();

    }

    @AfterEach
    void tearDown(){
        podcastImpl = null;
    }

    @Test
    public void returnAllPodcastInDatabase(){
        List<Podcast> podcastList = new ArrayList<>();
        podcastList = podcastImpl.allPodcast();
        assertEquals(5,podcastList.size());
        assertNotEquals(0,podcastList.size());
        assertEquals(20002,podcastList.get(1).getPodcastId());
        assertEquals("WavinFlag.wav",podcastList.get(0).getPodcastSrcFile());
        assertEquals("Raju",podcastList.get(3).getCelebrityName());
    }

    @Test
    public void givenDateReturnPodcast(){
        List<Podcast> podcastList = new ArrayList<>();
        podcastList = podcastImpl.searchPodcast("08/08/2020","Date");
        assertEquals(1,podcastList.size());
        assertNotEquals(0,podcastList.size());
        assertEquals(20002,podcastList.get(0).getPodcastId());
        assertEquals("RangdeBasanti.wav",podcastList.get(0).getPodcastSrcFile());
        assertEquals("Shekhar",podcastList.get(0).getCelebrityName());
    }

    @Test
    public void givenCelebrityNameReturnPodcast(){
        List<Podcast> podcastList = new ArrayList<>();
        podcastList = podcastImpl.searchPodcast("Knaan","Celebrity");
        assertEquals(1,podcastList.size());
        assertNotEquals(0,podcastList.size());
        assertEquals(20001,podcastList.get(0).getPodcastId());
        assertEquals("WavinFlag.wav",podcastList.get(0).getPodcastSrcFile());
        assertEquals("Knaan",podcastList.get(0).getCelebrityName());

    }

}
