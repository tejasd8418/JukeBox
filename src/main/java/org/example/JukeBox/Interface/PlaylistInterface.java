package org.example.JukeBox.Interface;

import org.example.JukeBox.ModelClass.Playlist;
import org.example.JukeBox.ModelClass.PlaylistDetails;

import java.util.List;
import java.util.Set;

public interface PlaylistInterface {

    public List<String> getPlaylistName(int userId);
    public void createPlaylist(List<PlaylistDetails> createPlaylistList, int userId, String playlistName);
    public List<PlaylistDetailsType> getPlayList(String playListName, int userId);
    public void editPlayList(List<PlaylistDetails>editPlayListlist, String playListName, String albumName, int userId, String type);
}
