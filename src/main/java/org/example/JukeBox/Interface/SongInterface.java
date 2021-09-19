package org.example.JukeBox.Interface;

import org.example.JukeBox.ModelClass.Songs;

import java.util.List;

public interface SongInterface {
    public List<Songs> allSongs();
    public List<Songs> sortSongs(String sortByCategory, String searchName);
}
