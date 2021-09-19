package org.example.HelloMaven.DaoTest;

import org.example.JukeBox.DaoClass.PlaylistImpl;
import org.example.JukeBox.Interface.PlaylistDetailsType;
import org.example.JukeBox.ModelClass.PlaylistDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlaylistImplTest {

    PlaylistImpl playlistImpl;

    @BeforeEach
    public void setUp(){
        playlistImpl = new PlaylistImpl();
    }

    @AfterEach
    public void tearDown(){
        playlistImpl = null;
    }

    @Test
    public void givenPlaylistNameAndUserIdReturnPlaylist1(){
        List<PlaylistDetailsType> list = new ArrayList<>();
        list = playlistImpl.getPlayList("ShubhamPlaylist",1002);
        assertEquals(7,list.size());
        assertEquals("10002  Om Shanti Om  Raju  Party  Rising  00:08:57  OmShantiOm.wav",list.get(0).toString());
    }

    @Test
    public void givenPlaylistNameAndUserIdReturnPlaylist2(){
        List<PlaylistDetailsType> list = new ArrayList<>();
        list = playlistImpl.getPlayList("PL1",1001);
        assertEquals(4,list.size());
        assertEquals("10001  Wavin Flag  Knaan  Pop  World Cup  00:03:44  WavinFlag.wav",list.get(0).toString());
    }

    @Test
    public void givenPlaylistNameAndUserIdReturnPlaylist3(){
        List<PlaylistDetailsType> list = new ArrayList<>();
        list = playlistImpl.getPlayList("PL3",1001);
        assertEquals(7,list.size());
        assertEquals("10005  Rang de basanti  Shekhar  Jazz  Unity  00:00:37  RangdeBasanti.wav",list.get(0).toString());
    }

    @Test
    public void givenPlaylistNameAndUserIdReturnPlaylist4(){
        List<PlaylistDetailsType> list = new ArrayList<>();
        list = playlistImpl.getPlayList("PL2",1001);
        assertEquals(7,list.size());
        assertEquals("10001  Wavin Flag  Knaan  Pop  World Cup  00:03:44  WavinFlag.wav",list.get(0).toString());
    }
}
