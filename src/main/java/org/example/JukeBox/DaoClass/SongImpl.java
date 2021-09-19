package org.example.JukeBox.DaoClass;

import org.example.JukeBox.DatabaseSetup;
import org.example.JukeBox.Interface.SongInterface;
import org.example.JukeBox.ModelClass.Songs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongImpl implements SongInterface {
    public List<Songs> allSongs(){
        List<Songs> songslist = new ArrayList<>();
        Songs songs;
        int songId = 0;
        String songName, artist, genre, album, songSrcFile = null;
        String time1 = "";
        Time songDuration;

        try(Connection connection = DatabaseSetup.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from songs");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                songId = resultSet.getInt(1);
                songName = resultSet.getString(2);
                artist = resultSet.getString(3);
                genre = resultSet.getString(4);
                album = resultSet.getString(5);
                songDuration = resultSet.getTime(6);
                songSrcFile = resultSet.getString(7);


                songs = new Songs(songId,songName,artist,genre,album,songDuration,songSrcFile);
                songslist.add(songs);
            }

        }
        catch (Exception e){
            System.out.println(e);
        }


        return songslist;
    }




    public List<Songs> sortSongs(String sortByCategory, String searchName){
        List<Songs> sortSongList = new ArrayList<>();
        List<Songs> tempSongStorage = new ArrayList<>();
        Songs songs;
        String query = "";

        try(Connection connection = DatabaseSetup.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("Select * from songs");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                songs = new Songs(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getTime(6),resultSet.getString(7));
                sortSongList.add(songs);
            }
            if(sortByCategory.equalsIgnoreCase("Artist")) {
                sortSongList = sortSongList.stream().filter(songs1 -> songs1.getArtist().equalsIgnoreCase(searchName)).collect(Collectors.toList());
                sortSongList = sortSongList.stream().sorted((c1,c2)->c1.getSongName().compareTo(c2.getSongName())).collect(Collectors.toList());

            }
            else if(sortByCategory.equalsIgnoreCase("Genre")) {
                sortSongList = sortSongList.stream().filter(songs1 -> songs1.getGenre().equalsIgnoreCase(searchName)).collect(Collectors.toList());
                sortSongList = sortSongList.stream().sorted((c1,c2)->c1.getSongName().compareTo(c2.getSongName())).collect(Collectors.toList());

            }
            else if(sortByCategory.equalsIgnoreCase("Album")) {
                sortSongList = sortSongList.stream().filter(songs1 -> songs1.getAlbum().equalsIgnoreCase(searchName)).collect(Collectors.toList());
                sortSongList = sortSongList.stream().sorted((c1,c2)->c1.getSongName().compareTo(c2.getSongName())).collect(Collectors.toList());

            }
            else{
                System.out.println("Please Enter Artist, Genre or Album only");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return sortSongList;
    }

}
