package org.example.JukeBox.ModelClass;

import org.example.JukeBox.Interface.PlaylistDetailsType;

import java.sql.Date;
import java.sql.Time;

public class Podcast implements PlaylistDetailsType {


    int podcastId;
    String podcastName;
    String celebrityName;
    Date date;
    String podcastSrcFile;
    Time podcastDuration;
    int podcastEpisode;

    public Podcast(){

    }
    public Podcast(int podcastId, String podcastName,  int podcastEpisode, String celebrityName, Date date, String podcastSrcFile, Time podcastDuration){
        this.podcastId = podcastId;
        this.podcastName = podcastName;
        this.celebrityName = celebrityName;
        this.date = date;
        this.podcastSrcFile = podcastSrcFile;
        this.podcastDuration = podcastDuration;
        this.podcastEpisode = podcastEpisode;
    }


    public int getPodcastEpisode() {
        return podcastEpisode;
    }

    public void setPodcastEpisode(int podcastEpisode) {
        this.podcastEpisode = podcastEpisode;
    }

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPodcastSrcFile() {
        return podcastSrcFile;
    }

    public void setPodcastSrcFile(String podcastSrcFile) {
        this.podcastSrcFile = podcastSrcFile;
    }

    public Time getPodcastDuration() {
        return podcastDuration;
    }

    public void setPodcastDuration(Time podcastDuration) {
        this.podcastDuration = podcastDuration;
    }

    @Override
    public String toString() {
        return  podcastId +
                " " + podcastName +
                " " + podcastEpisode +
                " " + celebrityName +
                " " + date +
                " " + podcastDuration;
    }
}
