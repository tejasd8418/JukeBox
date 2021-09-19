package org.example.JukeBox;

import org.example.JukeBox.DaoClass.AccountImpl;
import org.example.JukeBox.DaoClass.PlaylistImpl;
import org.example.JukeBox.DaoClass.PodcastImpl;
import org.example.JukeBox.DaoClass.SongImpl;
import org.example.JukeBox.Exception.WrongCateogoryException;
import org.example.JukeBox.Interface.PlaylistDetailsType;
import org.example.JukeBox.ModelClass.PlaylistDetails;
import org.example.JukeBox.ModelClass.Podcast;
import org.example.JukeBox.ModelClass.Songs;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JukeBoxMain {

    public static void main(String[] args)  {

        AccountImpl accountImpl = new AccountImpl();
        SongImpl songImpl = new SongImpl();
        PodcastImpl podcastImpl = new PodcastImpl();
        PlayAudio playAudio = new PlayAudio();
        PlaylistImpl playlistImpl = new PlaylistImpl();
        PlaylistDetails playlistDetails = new PlaylistDetails();
        JukeBoxMain jukeBoxMain = new JukeBoxMain();


        List<PlaylistDetails> createPlaylistList = new ArrayList<>();
        List<PlaylistDetails> editPlaylistList = new ArrayList<>();
        Set<Integer> idSet = new HashSet<>();
        List<PlaylistDetails> createNonDuplicatePlaylist = new ArrayList<>();
        List<PlaylistDetails> editNonDuplicatePlaylist = new ArrayList<>();
        List<PlaylistDetailsType> getPlayListlist = new ArrayList<>();
        List<Songs> getSongsFromPlaylist = new ArrayList<>();
        List<Songs> getSongsFromPlaylist1 = new ArrayList<>();
        List<Songs> allSongs = songImpl.allSongs();
        List<Podcast> allPodcast = podcastImpl.allPodcast();
        List<Podcast> getPodcastFromPlaylist = new ArrayList<>();
        List<String> getPlaylistName = new ArrayList<>();
        int flag2 = 0;
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Do you already have account \n Enter 'Y' for yes.    Enter 'N' for no.");
            String yesOrNo, name, emailId, password = null;
            int age = 0;
            yesOrNo = sc.nextLine();
            if (yesOrNo.equalsIgnoreCase("N")) {
                System.out.println("Enter your Name");
                name = sc.nextLine();
                System.out.println("Enter your age");
                age = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter your email Id");
                emailId = sc.nextLine();
                String checkEmail = "^[a-zA-z]{1}(.){3,}@[a-z]+.[a-z]+";
                Pattern pattern = Pattern.compile(checkEmail);
                Matcher matcher = pattern.matcher(emailId);
                while(!matcher.find()){
                    System.out.println("Invalid Email Id....Please Try again");
                    System.out.println("Enter your email Id");
                    emailId = sc.nextLine();
                    pattern = Pattern.compile(checkEmail);
                    matcher = pattern.matcher(emailId);
                }

                System.out.println("Create your password");
                password = sc.nextLine();
                while(password.length()<4){
                    System.out.println("Password should contain atleast 4 characters");
                    password = sc.nextLine();
                }
                accountImpl.createAccount(name, age, emailId, password);
                System.out.println("Your userId is: " + accountImpl.getUserId(emailId, password));
            }

            System.out.println("Sign in to your account: ");
            System.out.println("Enter your userId");
            int userId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter your password");
            String password1 = sc.nextLine();
            boolean checkIfAccountExist = accountImpl.checkIfUserHaveAccount(userId, password1);
            if (!checkIfAccountExist) {
                System.out.println("You have entered wrong userId or Password");
            } else {
                System.out.println("\n\nAll available Songs");
                System.out.println("Song Id     SongName            Artist      Album       Genre       Duration");
                for (Songs song : allSongs) {
                    System.out.printf("\n %-10d %-20s %-10s %-13s %-10s %-10s", song.getSongId(), song.getSongName() ,song.getArtist(), song.getAlbum(), song.getGenre(), song.getSongDuration());
                }
                System.out.println("\n\nAll available Podcast");
                System.out.println("PodcastId PodcastEpisode     PodcastName         Celebrity       Date         Duration");
                for (Podcast podcast : allPodcast) {
                    System.out.printf("\n %-13d %-13d %-20s %-11s %-15s %-10s", podcast.getPodcastId(), podcast.getPodcastEpisode(), podcast.getPodcastName(), podcast.getCelebrityName(), podcast.getDate(), podcast.getPodcastDuration());
                }
                    System.out.println("\n");

                while (true) {
                    System.out.println(" 1. Enter 1. To play a song \n 2. Enter 2. To play a podcast \n " +
                            " 3. Enter 3. to sort songs by cateogory \n" + " 4. Enter 4. to search a podcast by cateogory \n" +
                            " 5. Enter 5 to create your own playlist \n  6. Enter 6 to view your playlist \n 7. Enter 7 to edit your playlist \n " +
                            "8. Enter any other key to exit");

                    int choice = sc.nextInt();
                    int loop;
                    PlayPlaylist playPlaylist = new PlayPlaylist();
                    int playlistType = 0;
                    switch (choice) {
                        case 1:
                            System.out.println("Enter Id of song you want to play");
                            int flag = 0;
                            String filePath = "";

                            int songToPlay = sc.nextInt();
                            System.out.println("Enter a number of times you want to play");
                            loop = sc.nextInt();
                            try {
                                for (Songs s : allSongs) {
                                    if (s.getSongId() == songToPlay) {
                                        flag = 1;
                                        filePath = "Resources\\" + s.getSongSrcFile();

                                        System.out.println("Now playing Song:");
                                        System.out.println("Name: " + s.getSongName() + "  Album: " + s.getAlbum() + "   Duration: " + s.getSongDuration() + "\n");
                                        jukeBoxMain.readAudio(filePath,loop);
                                    }
                                }
                                if (flag == 0) {
                                    System.out.println("Enter valid song Id");
                                }
                            } catch (Exception ex) {
                                System.out.println("Error with playing sound.");
                                ex.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Enter Id of podcast you want to play");
                            int podcastToPlay = sc.nextInt();
                            System.out.println("Enter the episode of podcast");
                            int episodeToPlay = sc.nextInt();
                            System.out.println("Enter a number of times you want to play");
                            loop = sc.nextInt();
                            filePath = "";
                            try {
                                flag = 0;
                                for (Podcast p : allPodcast) {
                                    if (p.getPodcastId() == podcastToPlay && p.getPodcastEpisode() == episodeToPlay) {
                                        flag = 1;
                                        filePath = "Resources\\" + p.getPodcastSrcFile();

                                        System.out.println("Now playing Podcast:");
                                        System.out.println("Name: " + p.getPodcastName() + "  Episode: " + p.getPodcastEpisode() + "   Duration: " + p.getPodcastDuration() + "\n");
                                        jukeBoxMain.readAudio(filePath, loop);
                                    }
                                }
                                if (flag == 0) {
                                    System.out.println("Enter valid podcast Id");
                                }
                            } catch (Exception ex) {
                                System.out.println("Error with playing sound.");
                                ex.printStackTrace();
                            }
                            break;
                        case 3:
                            System.out.println("Enter a cateogory according to which you have to search. i.e. Artist, Genre, Album Name");
                            sc.nextLine();
                            filePath = "";
                            String cateogory = sc.nextLine();
                            List<Songs> sortBySongs;
                            try {
                                if(!(cateogory.equalsIgnoreCase("Artist") || cateogory.equalsIgnoreCase("Album") || cateogory.equalsIgnoreCase("Genre"))){
                                    throw new WrongCateogoryException("You have entered wrong cateogory");
                                }
                            }
                            catch(WrongCateogoryException e){
                                System.out.println(e.getMessage());
                                return;
                            }
                            String searchName = "";
                            if(cateogory.equalsIgnoreCase("Artist")){
                                System.out.println("Enter a artist name");
                                 searchName = sc.nextLine();
                            }
                            else if(cateogory.equalsIgnoreCase("Album")){
                                System.out.println("Enter a album name");
                                 searchName = sc.nextLine();

                            }
                            else if(cateogory.equalsIgnoreCase("Genre")){
                                System.out.println("Enter a genre name");
                                 searchName = sc.nextLine();

                            }

                            System.out.println("Song Id     SongName            Artist      Album      Genre       Duration");
                            sortBySongs = songImpl.sortSongs(cateogory,searchName);

                            for (Songs song1 : sortBySongs) {
                                System.out.printf("\n %-10d %-20s %-10s %-13s %-10s %-10s", song1.getSongId(), song1.getSongName() ,song1.getArtist(), song1.getAlbum(), song1.getGenre(), song1.getSongDuration());
                            }
                            System.out.println("\nDo you want to play song. Enter 1 for yes, 0 for no");
                            int wantToPlaySong = sc.nextInt();
                            if(wantToPlaySong == 0){
                                break;
                            }
                            else if(wantToPlaySong == 1) {
                                System.out.println("\n\nEnter Id of song you want to play");
                                songToPlay = sc.nextInt();
                                System.out.println("Enter a number of times you want to play");
                                loop = sc.nextInt();
                                try {
                                    flag = 0;
                                    for (Songs s : sortBySongs) {
                                        if (s.getSongId() == songToPlay) {
                                            flag = 1;
                                            System.out.println(s.getSongId());
                                            System.out.println(s.getSongSrcFile());
                                            filePath = "Resources\\" + s.getSongSrcFile();

                                            System.out.println("Now playing Song:");
                                            System.out.println("Name: " + s.getSongName() + "  Album: " + s.getAlbum() + "   Duration: " + s.getSongDuration() + "\n");
                                            jukeBoxMain.readAudio(filePath, loop);
                                        }
                                    }
                                    if (flag == 0) {
                                        System.out.println("Enter valid song Id");
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Error with playing sound.");
                                    ex.printStackTrace();
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Enter a cateogory according to which you have to search. i.e. Celebrity or Date");
                            sc.nextLine();
                            filePath = "";
                            String searchByCateogory = sc.nextLine();
                            String celebrityOrDate = "";
                            if (searchByCateogory.equalsIgnoreCase("Celebrity")) {
                                System.out.println("Enter a celebrity Name");
                                celebrityOrDate = sc.nextLine();
                            } else if (searchByCateogory.equalsIgnoreCase("Date")) {
                                System.out.println("Enter a date in DD/MM/YYYY format");
                                celebrityOrDate = sc.nextLine();
                            }
                            try {
                                if(!(searchByCateogory.equalsIgnoreCase("Celebrity") || searchByCateogory.equalsIgnoreCase("Date") )){
                                    throw new WrongCateogoryException("You have entered wrong cateogory");
                                }
                            }
                            catch(WrongCateogoryException e){
                                System.out.println(e.getMessage());
                                return;
                            }

                            List<Podcast> searchByPodcast1 = podcastImpl.searchPodcast(celebrityOrDate, searchByCateogory);
                            if(searchByPodcast1.size()==0){
                                System.out.println("No Podcast Available");
                                break;
                            }
                            System.out.println("\nPodcastId PodcastEpisode     PodcastName         Celebrity       Date         Duration");

                            for (Podcast podcast1 : searchByPodcast1) {
                                System.out.printf("\n %-13d %-13d %-20s %-11s %-15s %-10s", podcast1.getPodcastId(), podcast1.getPodcastEpisode(), podcast1.getPodcastName(), podcast1.getCelebrityName(), podcast1.getDate(), podcast1.getPodcastDuration());
                            }
                            System.out.println("\nDo you want to play any podcast. Enter 1 for yes, Enter 0 for no");
                            int wantToPlay = sc.nextInt();
                            if(wantToPlay == 0){
                                break;
                            }
                            else if(wantToPlay == 1) {

                                if (searchByPodcast1.size() != 0) {
                                    System.out.println("\nEnter Id of podcast you want to play");
                                    podcastToPlay = sc.nextInt();
                                    System.out.println("Enter the episode of podcast you want to play");
                                    episodeToPlay = sc.nextInt();
                                    System.out.println("Enter a number of times you want to play");
                                    loop = sc.nextInt();
                                    try {
                                        flag = 0;
                                        for (Podcast p : searchByPodcast1) {
                                            if (p.getPodcastId() == podcastToPlay && p.getPodcastEpisode() == episodeToPlay) {
                                                flag = 1;
                                                filePath = "Resources\\" + p.getPodcastSrcFile();

                                                System.out.println("Now playing Podcast:");
                                                System.out.println("Name: " + p.getPodcastName() + "  Episode: " + p.getPodcastEpisode() + "   Duration: " + p.getPodcastDuration() + "\n");
                                                jukeBoxMain.readAudio(filePath, loop);
                                            }
                                        }
                                        if (flag == 0) {
                                            System.out.println("Enter valid podcast Id");
                                        }
                                    } catch (Exception ex) {
                                        System.out.println("Error with playing sound.");
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            break;

                        case 5:
                            System.out.println(" 1. Press 1. to create playlist of songs \n 2. Press 2. to create playlist of podcast \n" +
                                    " 3. Press 3 to create playlist of both songs and podcast");
                            playlistType = sc.nextInt();
                            sc.nextLine();
                            String playlistName = "";
                            int songId = 0;
                            int podcastId = 0;
                            int flag3 = 0;
                            int songOrpodcastId = 0;
                            System.out.println("Enter the name for your playlist");
                            getPlaylistName = playlistImpl.getPlaylistName(userId);
                            while(flag3==0) {
                                playlistName = sc.nextLine();
                                if(getPlaylistName.size()==0) {
                                    flag3 = 1;
                                }
                                for (String existingPlaylistName : getPlaylistName) {
                                    flag3 = 1;
                                    if (playlistName.equalsIgnoreCase(existingPlaylistName)) {
                                        flag3 = 0;
                                        System.out.println("Playlist with this name:" + playlistName + " Already exists");
                                        break;
                                    }
                                }
                            }
                            flag3=0;

                            int userExit = 1;
                            String playlistTypeStr = "";
                            if (playlistType == 1) {
                                playlistTypeStr = "Songs";
                                while (userExit == 1) {
                                    System.out.println("Enter a songId of song you want to add in a playlist");
                                    songId = sc.nextInt();
                                    for (Songs songs : allSongs) {
                                        if (songId == songs.getSongId()) {
                                            flag2 = 1;
                                        }
                                    }
                                    if (flag2 == 0) {
                                        System.out.println("Song with Id " + songId + " does not exist");
                                        return;
                                    }
                                    flag2 = 0;

                                    playlistDetails = new PlaylistDetails(userId, songId, playlistTypeStr);
                                    if (!createPlaylistList.contains(playlistDetails)) {
                                        createPlaylistList.add(playlistDetails);
                                    } else {
                                        System.out.println("Song already exist in playlist");
                                    }
                                    System.out.println("Do you want to add more song. Enter 1 to add, Enter 0 to stop");
                                    userExit = sc.nextInt();
                                }
                                createNonDuplicatePlaylist = createPlaylistList.stream()
                                        .filter(e -> idSet.add(e.getSongOrPodcastId()))
                                        .collect(Collectors.toList());
                                playlistImpl.createPlaylist(createNonDuplicatePlaylist, userId, playlistName);
                            }
                            if (playlistType == 2) {
                                playlistTypeStr = "Podcast";
                                while (userExit == 1) {

                                    System.out.println("Enter a podcastId of Podcast you want to add in a playlist");
                                    podcastId = sc.nextInt();
                                    for (Podcast podcast : allPodcast) {
                                        if (podcastId == podcast.getPodcastId()) {
                                            flag2 = 1;
                                        }
                                    }
                                    if (flag2 == 0) {
                                        System.out.println("Podcast with Id " + podcastId + " does not exist");
                                        return;
                                    }
                                    flag2 = 0;
                                    playlistDetails = new PlaylistDetails(userId, podcastId, playlistTypeStr);

                                    if (!createPlaylistList.contains(playlistDetails)) {
                                        createPlaylistList.add(playlistDetails);
                                    } else {
                                        System.out.println("Podcast already exist in playlist");
                                    }
                                    System.out.println("Do you want to add more Podcast. Enter 1 to add, Enter 0 to stop");
                                    userExit = sc.nextInt();
                                }
                                createNonDuplicatePlaylist = createPlaylistList.stream()
                                        .filter(e -> idSet.add(e.getSongOrPodcastId()))
                                        .collect(Collectors.toList());

                                playlistImpl.createPlaylist(createNonDuplicatePlaylist, userId, playlistName);
                            }
                            if (playlistType == 3) {
                                while (userExit == 1) {
                                    System.out.println("Enter a type of Audio you want to add (Songs, or Podcast)");
                                    playlistTypeStr = sc.nextLine();
                                    if (playlistTypeStr.equalsIgnoreCase("Songs")) {
                                        System.out.println("Enter a songId of song you want to add in a playlist");
                                        songId = sc.nextInt();
                                        for (Songs songs : allSongs) {
                                            if (songId == songs.getSongId()) {
                                                flag2 = 1;
                                            }
                                        }
                                        if (flag2 == 0) {
                                            System.out.println("Song with " + songId + " does not exist");
                                            return;
                                        }
                                        flag2 = 0;
                                        playlistDetails = new PlaylistDetails(userId, songId, playlistTypeStr);
                                        createPlaylistList.add(playlistDetails);
                                        System.out.println("Do you want to add more song. Enter 1 to add, Enter 0 to stop");
                                        userExit = sc.nextInt();
                                        sc.nextLine();
                                    } else if (playlistTypeStr.equalsIgnoreCase("Podcast")) {
                                        System.out.println("Enter a podcastId of podcast you want to add in a playlist");
                                        podcastId = sc.nextInt();
                                        for (Podcast podcast : allPodcast) {
                                            if (podcastId == podcast.getPodcastId()) {
                                                flag2 = 1;
                                            }
                                        }
                                        if (flag2 == 0) {
                                            System.out.println("Podcast with " + podcastId + " does not exist");
                                            return;
                                        }
                                        flag2 = 0;
                                        playlistDetails = new PlaylistDetails(userId, podcastId, playlistTypeStr);
                                        createPlaylistList.add(playlistDetails);
                                        System.out.println("Do you want to add more podcast. Enter 1 to add, Enter 0 to stop");
                                        userExit = sc.nextInt();
                                        sc.nextLine();
                                    }
                                }
                                createNonDuplicatePlaylist = createPlaylistList.stream()
                                        .filter(e -> idSet.add(e.getSongOrPodcastId()))
                                        .collect(Collectors.toList());

                                playlistImpl.createPlaylist(createNonDuplicatePlaylist, userId, playlistName);
                            }
                            break;


                        case 6:
                            getPlaylistName = playlistImpl.getPlaylistName(userId);

                            if(getPlaylistName.size() == 0){
                                System.out.println("You dont have any playlist to view. Press 5 to create playlist");
                                break;
                            }
                            System.out.println("Your all Playlist:");
                            for (String existingPlaylistName : getPlaylistName) {
                                System.out.println(existingPlaylistName);
                            }

                            System.out.println("\nEnter your playlist Name");
                            sc.nextLine();
                            String getPlaylist = sc.nextLine();
                            flag3 = 0;

                            for (String existingPlaylistName : getPlaylistName) {
                                if (getPlaylist.equalsIgnoreCase(existingPlaylistName)) {
                                    flag3 = 1;
                                    break;
                                }
                            }
                            if(flag3 == 0){
                                System.out.println("Playlist with name: "+ getPlaylist +", does not exist");
                                break;
                            }
                            getPlayListlist = playlistImpl.getPlayList(getPlaylist, userId);
                            if (getPlayListlist != null) {
                                for (PlaylistDetailsType p : getPlayListlist) {
                                    if (p instanceof Songs) {

                                        Songs songs = (Songs) p;
                                        System.out.printf("\n %-10d %-20s %-10s %-13s %-10s %-10s", songs.getSongId(), songs.getSongName() ,songs.getArtist(), songs.getAlbum(), songs.getGenre(), songs.getSongDuration());

                                    }
                                    if (p instanceof Podcast) {

                                        Podcast podcast = (Podcast) p;
                                        System.out.printf("\n %-13d %-13d %-20s %-11s %-15s %-10s", podcast.getPodcastId(), podcast.getPodcastEpisode(), podcast.getPodcastName(), podcast.getCelebrityName(), podcast.getDate(), podcast.getPodcastDuration());
                                    }
                                }
                            }
                            System.out.println("\n");
                            flag2=0;

                            System.out.println("Press 1 to play any audio. \nPress 2 to play entire playlist. \n Press any other number to exit");
                            int choice1 = sc.nextInt();
                            switch (choice1) {
                                case 1:
                                    System.out.println("What you want to play? Songs or podcast:");
                                    sc.nextLine();
                                    String filePath1 = "";
                                    int flag4 =0;
                                    String songsOrPodcast = sc.nextLine();
                                    if (songsOrPodcast.equalsIgnoreCase("songs")) {
                                        System.out.println("Enter a songId of song you want to play");
                                        int songToPlay1 = sc.nextInt();
                                        System.out.println("Enter a number of times you want to play");
                                        loop = sc.nextInt();
                                        Songs song1;
                                        for (PlaylistDetailsType playlistDetailsType1 : getPlayListlist) {
                                            if (playlistDetailsType1 instanceof Songs) {
                                                song1 = (Songs) playlistDetailsType1;
                                                getSongsFromPlaylist.add(song1);
                                                if (song1.getSongId() == songToPlay1) {
                                                    flag4=1;
                                                    filePath1 = "Resources\\" + song1.getSongSrcFile();

                                                    System.out.println("Now playing Song:");
                                                    System.out.println("Name: " + song1.getSongName() + "  Album: " + song1.getAlbum() + "   Duration: " + song1.getSongDuration() + "\n");
                                                    jukeBoxMain.readAudio(filePath1,loop);

                                                }
                                            }
                                        }
                                    } else if (songsOrPodcast.equalsIgnoreCase("podcast")) {
                                        System.out.println("Enter a podcastId of podcast you want to play");
                                        int podcastToPlay1 = sc.nextInt();
                                        System.out.println("Enter a episode you want to play");
                                        int episodeToPlay1 = sc.nextInt();
                                        System.out.println("Enter a number of times you want to play");
                                        loop = sc.nextInt();
                                        Podcast podcast1;
                                        for (PlaylistDetailsType playlistDetailsType1 : getPlayListlist) {
                                            if (playlistDetailsType1 instanceof Podcast) {
                                                flag4=1;
                                                podcast1 = (Podcast) playlistDetailsType1;
                                                getPodcastFromPlaylist.add(podcast1);
                                                if (podcast1.getPodcastId() == podcastToPlay1 && podcast1.getPodcastEpisode() == episodeToPlay1) {
                                                    filePath1 = "Resources\\" + podcast1.getPodcastSrcFile();

                                                    System.out.println("Now playing Podcast:");
                                                    System.out.println("Name: " + podcast1.getPodcastName() + "  Episode: " + podcast1.getPodcastEpisode() + "   Duration: " + podcast1.getPodcastDuration() + "\n");
                                                    jukeBoxMain.readAudio(filePath1,loop);
                                                }
                                            }
                                        }
                                    }
                                    if(flag4==0){
                                        System.out.println("Wrong Input or Wrong Song/PodcastId");
                                    }
                                    break;


                                case 2:
                                    int loop1 = 1;
                                    System.out.println(" Press 1. for playing playlist. \n Press 2. for playing in reverse order" +
                                            "\n Press 3. to Shuffle the song \n Press any other number to exit");
                                    int playlistOperation = sc.nextInt();
                                    Songs songs;
                                    Podcast podcast;
                                    switch (playlistOperation) {
                                        case 1:
                                            System.out.println("Enter the number of times you want to play this playlist");
                                            loop1 = sc.nextInt();
                                            for(int i = 0; i<loop1;i++) {
                                                jukeBoxMain.readPlaylist(getPlayListlist);
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Enter the number of times you want to play this playlist");
                                            loop1 = sc.nextInt();
                                            Collections.reverse(getPlayListlist);
                                            for(int i = 0; i<loop1;i++) {
                                                jukeBoxMain.readPlaylist(getPlayListlist);
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Enter the number of times you want to play this playlist");
                                            loop1 = sc.nextInt();
                                            Collections.shuffle(getPlayListlist);
                                            for(int i = 0; i<loop1;i++) {
                                                jukeBoxMain.readPlaylist(getPlayListlist);
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                default:
                                    break;

                            }
                            break;

                        case 7:
                            getPlaylistName = playlistImpl.getPlaylistName(userId);

                            if(getPlaylistName.size() == 0){
                                System.out.println("You dont have any playlist to edit. Press 5 to create playlist");
                                break;
                            }
                            System.out.println("Your playlist are:");
                            for (String existingPlaylistName : getPlaylistName) {
                                System.out.println(existingPlaylistName);
                            }

                            System.out.println("\nEnter your playlistName that you want to edit");
                            sc.nextLine();
                            String type = "";
                            flag3 = 0;
                            String albumName = "";
                            String editPlaylistName = "";
                            getPlaylistName = playlistImpl.getPlaylistName(userId);
                            editPlaylistName = sc.nextLine();
                            for (String existingPlaylistName : getPlaylistName) {
                                if (editPlaylistName.equalsIgnoreCase(existingPlaylistName)) {
                                    flag3 = 1;
                                    break;
                                }
                            }
                            if(flag3 == 0){
                                System.out.println("Playlist with name: "+ editPlaylistName +", does not exist");
                                return;
                            }
                            getPlayListlist = playlistImpl.getPlayList(editPlaylistName, userId);
                            if (getPlayListlist != null) {
                                for (PlaylistDetailsType p : getPlayListlist) {
                                    if(p instanceof Songs){
                                        Songs song = (Songs) p;
                                        System.out.printf("\n %-10d %-20s %-10s %-13s %-10s %-10s", song.getSongId(), song.getSongName() ,song.getArtist(), song.getAlbum(), song.getGenre(), song.getSongDuration());
                                    }
                                    if (p instanceof Podcast){
                                        Podcast podcast = (Podcast) p;
                                        System.out.printf("\n %-13d %-13d %-20s %-11s %-15s %-10s", podcast.getPodcastId(), podcast.getPodcastEpisode(), podcast.getPodcastName(), podcast.getCelebrityName(), podcast.getDate(), podcast.getPodcastDuration());
                                    }
                                }
                                userExit = 1;
                                while (userExit == 1) {
                                    System.out.println("\n\nEnter a type of audio you want to add (Songs or Podcast)");
                                    playlistTypeStr = sc.nextLine();
                                    if (playlistTypeStr.equalsIgnoreCase("Songs")) {
                                        System.out.println("Do you want to add album or songs");
                                        String albumOrSong = sc.nextLine();
                                        if (albumOrSong.equalsIgnoreCase("songs")) {
                                            albumName = "";
                                            type = "songs";
                                            System.out.println("Enter a songId of song you want to add in a playlist");
                                            songId = sc.nextInt();
                                            for (Songs songs : allSongs) {
                                                if (songId == songs.getSongId()) {
                                                    flag2 = 1;
                                                }
                                            }
                                            if (flag2 == 0) {
                                                System.out.println("Song with " + songId + " does not exist");
                                                return;
                                            }
                                            flag2 = 0;
                                            playlistDetails = new PlaylistDetails(userId, songId, playlistTypeStr);
                                            editPlaylistList.add(playlistDetails);
                                            System.out.println("Do you want to add more song. Enter 1 to add, Enter 0 to stop");
                                            userExit = sc.nextInt();
                                            sc.nextLine();
                                        } else if (albumOrSong.equalsIgnoreCase("Album")) {
                                            System.out.println("Enter the name of the album");
                                            type = "album";
                                            albumName = sc.nextLine();
                                            for (Songs songs : allSongs) {
                                                if (albumName.equalsIgnoreCase(songs.getAlbum())) {
                                                    flag2 = 1;
                                                }
                                            }
                                            if (flag2 == 0) {
                                                System.out.println("Album with " + albumName + " does not exist");
                                                return;
                                            }
                                            flag2 = 0;
                                            playlistDetails = new PlaylistDetails(userId, type, albumName);
                                            editPlaylistList.add(playlistDetails);
                                            System.out.println("Do you want to add more song. Enter 1 to add, Enter 0 to stop");
                                            userExit = sc.nextInt();
                                            sc.nextLine();
                                        }
                                    } else if (playlistTypeStr.equalsIgnoreCase("Podcast")) {
                                        System.out.println("Enter a podcastId of podcast you want to add in a playlist");
                                        podcastId = sc.nextInt();
                                        for (Podcast podcast : allPodcast) {
                                            if (podcastId == podcast.getPodcastId()) {
                                                flag2 = 1;
                                            }
                                        }
                                        if (flag2 == 0) {
                                            System.out.println("Podcast with " + podcastId + " does not exist");
                                            return;
                                        }
                                        flag2 = 0;
                                        type = "podcast";
                                        albumName = "";
                                        playlistDetails = new PlaylistDetails(userId, podcastId, playlistTypeStr);
                                        editPlaylistList.add(playlistDetails);
                                        System.out.println("Do you want to add more podcast. Enter 1 to add, Enter 0 to stop");
                                        userExit = sc.nextInt();
                                        sc.nextLine();
                                    }
                                }
                                editNonDuplicatePlaylist = editPlaylistList.stream()
                                        .filter(e -> idSet.add(e.getSongOrPodcastId()))
                                        .collect(Collectors.toList());

                                playlistImpl.editPlayList(editNonDuplicatePlaylist, editPlaylistName, albumName, userId, type);
                            }
                            break;
                        default:
                            return;
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(e + "Main");
        } catch (Exception e) {
            System.out.println(e + "Main1");
        }
    }


    static int goBack = 0;
    public void readPlaylist(List<PlaylistDetailsType> allAudios) {
        PlayPlaylist playPlaylist = new PlayPlaylist();
        Songs songs = new Songs();
        String filePath = "";
        Podcast podcast = new Podcast();
        int flag = 0;
        int c = 0;
        int i = 0;
        try {
            for (i = 0; i < allAudios.size(); i++) {
                System.out.println(goBack + "561");
                if (goBack == 1) {
                    if(i==0 && i==1){
                        System.out.println("Cant move back its 1st song of a playlist");
                    }

                    else if(i!=0 && i!=1) {
                    i = i - 2;
                    }
                    goBack = 0;
                }
                if(goBack==2){
                    i = allAudios.size() - 2;
                    goBack = 0;
                }
                PlaylistDetailsType pdt = allAudios.get(i);
                if (pdt instanceof Songs) {
                    songs = (Songs) pdt;
                    System.out.println("Now playing Song:");
                    System.out.println("Name: " + songs.getSongName() + "  Album: " + songs.getAlbum() + "   Duration: " + songs.getSongDuration() + "\n");
                    filePath = "Resources\\" + songs.getSongSrcFile();
                }
                else if (pdt instanceof Podcast) {
                    podcast = (Podcast) pdt;
                    System.out.println("Now playing Podcast");
                    System.out.println("Podcast Name: " + podcast.getPodcastName() + " Episode: " + podcast.getPodcastEpisode() + " Duration: " + podcast.getPodcastDuration());
                    filePath = "Resources\\" + podcast.getPodcastSrcFile();

                }
                playPlaylist = new PlayPlaylist(filePath);
                playPlaylist.play();
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("1. pause");
                    System.out.println("2. resume");
                    System.out.println("3. restart");
                    System.out.println("4. next");
                    System.out.println("5. previous");

                    if (scanner.hasNextInt()) {
                        c = scanner.nextInt();
                    } else {
                        break;
                    }
                    if(!(c==5 && i==0)) {
                        if(c==5 && i == allAudios.size()-1){
                            i= i-1;
                            goBack = 2;
                        }
                        playPlaylist.gotoChoice(c);
                    }

                    if (c == 5 && goBack!=2) {
                        goBack = 1;
                    }

                    if (c == 4 || c == 5) {
                        if(c==5 && i==0){
                            System.out.println("Cant move backward this is the 1st song of playlist");
                        }
                        else {
                            break;
                        }
                    }
                    c = 0;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void readAudio(String audioSrcFile, int count) {
        PlayAudio playAudio = new PlayAudio();
        int c = 0;
        count = count-1;
        try {

            playAudio.playAudio1(audioSrcFile,count);

            playAudio.play();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4. stop");

                if (c == 4) {
                    break;
                }

                if (scanner.hasNextInt()) {
                    c = scanner.nextInt();
                } else {
                    break;
                }
                switch (c) {
                    case 1:
                        playAudio.pause();
                        break;
                    case 2:
                        playAudio.resumeAudio();
                        break;
                    case 3:
                        playAudio.restart();
                        break;
                    case 4:
                        playAudio.stop();
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound." + ex);
        }
    }

}
