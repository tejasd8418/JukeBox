package org.example.JukeBox.ModelClass;

import java.util.List;
import java.util.Set;

public class Playlist {

    int playlistId;
    int userId;
    String playlistName;
    Set<PlaylistDetails> playlistDetails;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Set<PlaylistDetails> getPlaylistDetails() {
        return playlistDetails;
    }

    public void setPlaylistDetails(Set<PlaylistDetails> playlistDetails) {
        this.playlistDetails = playlistDetails;
    }
}
