package org.example.JukeBox.DaoClass;

import org.example.JukeBox.DatabaseSetup;
import org.example.JukeBox.Interface.PlaylistDetailsType;
import org.example.JukeBox.Interface.PlaylistInterface;
import org.example.JukeBox.ModelClass.PlaylistDetails;
import org.example.JukeBox.ModelClass.Podcast;
import org.example.JukeBox.ModelClass.Songs;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public class PlaylistImpl implements PlaylistInterface, PlaylistDetailsType {

    public List<String> getPlaylistName(int userId){
        List<String> namesOfPlaylist = new ArrayList<>();
        try(Connection connection = DatabaseSetup.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("Select playlistName from playlist where userId = ?");
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                namesOfPlaylist.add(resultSet.getString(1));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return namesOfPlaylist;
    }

    public void createPlaylist(List<PlaylistDetails> createPlaylistList, int userId, String playlistName){
        int playlistId = 0;
        try(Connection connection = DatabaseSetup.getConnection()){

            PreparedStatement preparedStatement1 = connection.prepareStatement("Insert into playlist(userId,playlistName) values(?,?)");
            preparedStatement1.setInt(1,userId);
            preparedStatement1.setString(2,playlistName);
            preparedStatement1.executeUpdate();


            PreparedStatement preparedStatement2 = connection.prepareStatement("Select playlistId from playlist where userId = ? and playlistName = ?");
            preparedStatement2.setInt(1,userId);
            preparedStatement2.setString(2,playlistName);
            ResultSet resultSet = preparedStatement2.executeQuery();
            while(resultSet.next()){
                playlistId = resultSet.getInt(1);
            }




            for(PlaylistDetails playlistDetails: createPlaylistList) {
                PreparedStatement preparedStatement = connection.prepareStatement("Insert into playlistdetails(playlistId, userId, songOrPodcastId, type) values(?,?,?,?)");
                preparedStatement.setInt(1,playlistId);
                preparedStatement.setInt(2,playlistDetails.getUserId());
                preparedStatement.setInt(3,playlistDetails.getSongOrPodcastId());
                preparedStatement.setString(4,playlistDetails.getType());
                int rows = preparedStatement.executeUpdate();
                if(rows>0){
                    System.out.println(playlistDetails.getType()+" with ID: "+ playlistDetails.getSongOrPodcastId() +" added successfully in playlist");
                }
                else{
                    System.out.println("Cant add to the playlist");
                }
            }


        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public List<PlaylistDetailsType> getPlayList(String playListName, int userId){
        List<PlaylistDetailsType> getAllFromPlaylist = new ArrayList<>();

        int playlistId = 0;
        String query = "";

        List<PlaylistDetails> types = new ArrayList<>();
        PlaylistDetailsType playlistDetailsType;
        PlaylistDetails playlistDetails;
        Songs songs;
        Podcast podcast;
        try(Connection connection = DatabaseSetup.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct playlistId,userId from playlistDetails where playlistId = (select playlistId from playlist where playlistname = ?)");
            preparedStatement.setString(1, playListName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //System.out.println("class4");
                playlistId = resultSet.getInt(1);
                if(userId != resultSet.getInt(2)){
                    System.out.println("You dont have such playlist");
                    return null;
                }
            }
            PreparedStatement preparedStatement1 = connection.prepareStatement("Select songOrPodcastId, type from playlistdetails where playlistid = ? ");
            preparedStatement1.setInt(1, playlistId);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                playlistDetails = new PlaylistDetails(resultSet1.getInt(1), resultSet1.getString(2));
                types.add(playlistDetails);
            }

                for (PlaylistDetails type11 : types) {
                    if (type11.getType().equalsIgnoreCase("Songs")) {
                        query = "select distinct s.songName, s.artist, s.genre, s.album, s.songduration, s.songId, s.songSrcFile from songs s join playlistdetails p on s.songId = p.songOrPodcastId where songOrPodcastId = ?";
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query);
                        preparedStatement2.setInt(1, type11.getSongOrPodcastId());
                        ResultSet resultSet2 = preparedStatement2.executeQuery();
                        while (resultSet2.next()) {
                            playlistDetailsType = new Songs(resultSet2.getInt(6),resultSet2.getString(1),resultSet2.getString(2),resultSet2.getString(3),resultSet2.getString(4),resultSet2.getTime(5), resultSet2.getString(7));
                            getAllFromPlaylist.add(playlistDetailsType);
                        }
                    } else if (type11.getType().equalsIgnoreCase("Podcast")) {
                        query = "select distinct s.podcastName, s.podcastEpisode, s.celebrityName, s.publishdate, s.podcastduration, s.podcastId, s.podcastSrcFile from podcast s join playlistdetails p on s.podcastId = p.songOrPodcastId where songOrPodcastId = ?";
                        PreparedStatement preparedStatement3 = connection.prepareStatement(query);
                        preparedStatement3.setInt(1, type11.getSongOrPodcastId());
                        ResultSet resultSet3 = preparedStatement3.executeQuery();
                        while (resultSet3.next()) {
                            podcast = new Podcast(resultSet3.getInt(6),resultSet3.getString(1),resultSet3.getInt(2),resultSet3.getString(3),resultSet3.getDate(4),resultSet3.getString(7),resultSet3.getTime(5));
                            getAllFromPlaylist.add(podcast);
                        }
                    }
                }
            }

        catch (Exception e){
            System.out.println(e);
        }


        return getAllFromPlaylist;
    }
    public void editPlayList(List<PlaylistDetails>editPlaylistList, String playListName, String albumName, int userId,String type){
        int playlistId = 0;
        List<PlaylistDetails> newEditPlaylistlist = new ArrayList<>();
        Set<Integer> idSet = new LinkedHashSet<>();
        List<PlaylistDetails> editPlaylistList1 = new ArrayList<>();
        Songs songs;
        PlaylistDetails playlistDetails1;
        try(Connection connection = DatabaseSetup.getConnection()){

            PreparedStatement preparedStatement2 = connection.prepareStatement("Select playlistId from playlist where userId = ? and playlistName = ?");
            preparedStatement2.setInt(1,userId);
            preparedStatement2.setString(2,playListName);
            ResultSet resultSet = preparedStatement2.executeQuery();
            while(resultSet.next()){
                playlistId = resultSet.getInt(1);
            }

            for(PlaylistDetails playlistDetails: editPlaylistList) {
                if (type.equalsIgnoreCase("Album")) {
                    type = "Songs";
                    PreparedStatement preparedStatement3 = connection.prepareStatement("Select songId from Songs where album = ?");
                    preparedStatement3.setString(1, albumName);
                    ResultSet resultSet1 = preparedStatement3.executeQuery();
                    while (resultSet1.next()) {
                        playlistDetails1 = new PlaylistDetails(userId, resultSet1.getInt(1), type);
                        editPlaylistList1.add(playlistDetails1);

                    }

                }
            }
                PreparedStatement preparedStatement3 = connection.prepareStatement("select pd.songOrPodcastId from playlistDetails pd" +
                        " join playlist p on pd.playlistId = p.playlistId where p.playlistName = ? ");
                preparedStatement3.setString(1,playListName);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                while(resultSet3.next()){
                    int oldId = resultSet3.getInt(1);
                    idSet.add(oldId);
                }

                editPlaylistList.addAll(editPlaylistList1);

            newEditPlaylistlist = editPlaylistList.stream()
                    .filter(e -> idSet.add(e.getSongOrPodcastId()))
                    .collect(Collectors.toList());



            for(PlaylistDetails playlistDetails2:newEditPlaylistlist) {

                PreparedStatement preparedStatement = connection.prepareStatement("Insert into playlistdetails(playlistId, userId, songOrPodcastId, type) values(?,?,?,?)");
                preparedStatement.setInt(1, playlistId);
                preparedStatement.setInt(2, playlistDetails2.getUserId());
                preparedStatement.setInt(3, playlistDetails2.getSongOrPodcastId());
                preparedStatement.setString(4, playlistDetails2.getType());
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println(playlistDetails2.getType() + " with ID: " + playlistDetails2.getSongOrPodcastId() + " added successfully in playlist");
                } else {
                    System.out.println("Cant add to the playlist");
                }
            }



        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
