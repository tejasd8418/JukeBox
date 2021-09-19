package org.example.JukeBox.Interface;

import org.example.JukeBox.ModelClass.Podcast;

import java.util.List;

public interface PodcastInterface {

    public List<Podcast> allPodcast();
    public List<Podcast> searchPodcast(String searchByCelebrityOrDate, String searchByCateogory);
}
