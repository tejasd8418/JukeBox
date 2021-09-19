package org.example.JukeBox.DaoClass;

import org.example.JukeBox.DatabaseSetup;
import org.example.JukeBox.Interface.PodcastInterface;
import org.example.JukeBox.ModelClass.Podcast;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PodcastImpl implements PodcastInterface {

    public List<Podcast> allPodcast(){
        List<Podcast> podcastlist = new ArrayList<>();
        Podcast podcast;

        try(Connection connection = DatabaseSetup.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from podcast");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                podcast = new Podcast(resultSet.getInt(1),resultSet.getString(2), resultSet.getInt(7),resultSet.getString(3), resultSet.getDate(4),resultSet.getString(5),resultSet.getTime(6));
                podcastlist.add(podcast);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return podcastlist;
    }
    public List<Podcast> searchPodcast(String searchByCelebrityOrDate, String searchByCateogory){
        List<Podcast> podcastSearchList = new ArrayList<>();
        Podcast podcast;
        try(Connection connection = DatabaseSetup.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from podcast");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                podcast = new Podcast(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(7), resultSet.getString(3),resultSet.getDate(4),resultSet.getString(5),resultSet.getTime(6));
                podcastSearchList.add(podcast);
            }
            if(searchByCateogory.equalsIgnoreCase("Celebrity")) {
                podcastSearchList = podcastSearchList.stream().filter(podcast1 -> podcast1.getCelebrityName().equalsIgnoreCase(searchByCelebrityOrDate)).collect(Collectors.toList());
            }
            else if(searchByCateogory.equalsIgnoreCase("Date")){
                java.util.Date date1= new SimpleDateFormat("dd/MM/yyyy").parse(searchByCelebrityOrDate);
                podcastSearchList = podcastSearchList.stream().filter(podcast1 -> podcast1.getDate().equals(date1)).collect(Collectors.toList());

            }
        }
        catch (Exception e){
            System.out.println(e + "yes");
        }

        return podcastSearchList;
    }
}
