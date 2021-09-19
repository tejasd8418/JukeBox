package org.example.JukeBox.ModelClass;

import org.example.JukeBox.Interface.PlaylistDetailsType;

public class PlaylistDetails {

    int userId;
    int playlistId;
    int detailId;
    String type;
    int songOrPodcastId;
    String albumName;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public PlaylistDetails(){

    }

    public PlaylistDetails(int songOrPodcastId, String type){
        this.songOrPodcastId = songOrPodcastId;
        this.type = type;
    }

    public PlaylistDetails(int userId, String type, String albumName){
        this.userId = userId;
        this.type = type;
        this.albumName = albumName;
    }

    public PlaylistDetails(int userId, int songOrPodcastId, String type){
        this.userId = userId;
        this.songOrPodcastId = songOrPodcastId;
        this.type = type;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSongOrPodcastId() {
        return songOrPodcastId;
    }

    public void setSongOrPodcastId(int songOrPodcastId) {
        this.songOrPodcastId = songOrPodcastId;
    }
}
