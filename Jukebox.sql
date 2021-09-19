use jukebox;

create table user(
userId int auto_increment,
username char(30) not null,
age int not null,
emailId varchar(30) not null,
userPassword char(12) not null,
primary key (userId)
);
alter table user modify userPassword varchar(20) not null;
alter table user auto_increment = 1001;

select * from user;
select * from songs;
select * from podcast;
select * from playlist;
select * from playlistdetails;

create table playlist(
playlistId int auto_increment,
userId int not null,
playlistName varchar(30) not null,
primary key(playlistId),
foreign key(userId) references user(userId)
);
alter table playlist auto_increment = 1;
select * from playlistdetails;
select * from playlist;


create table playlistDetails(
detailId int auto_increment,
playlistId int,
userId int not null,
songOrPodcastId int not null,
type varchar(10) not null,
primary key(detailId, playlistId),
foreign key(userId) references user(userId)
);
alter table playListDetails auto_increment = 501;

create table songs(
songId int auto_increment,
songName varchar(50) not null,
artist varchar(30) not null,
genre varchar(30) not null,
album varchar(30),
songDuration time not null,
songSrcFile varchar(500) not null,
primary key(songId)
);
alter table songs auto_increment = 10001;

select * from songs;
insert into songs values(default, 'Wavin Flag', 'Knaan', 'Pop', 'World Cup', '00:03:44', 'WavinFlag.wav' );
insert into songs values(default, 'Om Shanti Om', 'Raju', 'Party', 'Rising', '00:08:57', 'OmShantiOm.wav' );
insert into songs values(default, 'All is well', 'Raju', 'Pop', 'Unity', '00:04:36', 'AllIsWell.wav' );
insert into songs values(default, 'Why this Kolavari', 'Raju', 'Jazz', 'Rising', '00:04:08', 'WhyThisKolavari.wav' );
insert into songs values(default, 'Rang de basanti', 'Shekhar', 'Jazz', 'Unity', '00:00:37', 'RangdeBasanti.wav' );
insert into songs values(default, 'Singham', 'Raju', 'Jazz', 'Rising', '00:05:50', 'Singham.wav' );
insert into songs values(default, 'De Ghuma ke', 'Shekhar', 'Pop', 'World Cup', '00:03:54', 'DeGhumaKe.wav' );

select * from songs where artist = 'raju';

create table podcast(
podcastId int auto_increment,
podcastName varchar(80) not null,
celebrityname varchar(30) not null,
publishDate date not null,
podcastSrcFile varchar(500) not null,
podcastDuration time not null,
primary key(podcastId)
);
alter table podcast drop primary key;
alter table podcast add primary key(podcastid, podcastepisode);
alter table podcast drop auto_increment;
ALTER TABLE podcast DROP PRIMARY KEY, CHANGE podcastid podcastid int;
alter table podcast add column podcastEpisode int default 1;
alter table podcast auto_increment = 20001;
select * from podcast;
insert into podcast values(20001, 'Wavin Flag', 'Knaan', '2020-12-08', 'WavinFlag.wav' , '00:03:44' );
insert into podcast values(20002, 'Rang de basanti', 'Shekhar', '2020-08-08', 'RangdeBasanti.wav' , '00:00:37' );
insert into podcast values(20002, 'DeGhumaKe', 'Shekhar', '2020-08-09', 'DeGhumaKe.wav' , '00:03:54',2 );
insert into podcast values(20003, 'Om Shanti Om', 'Raju', '2020-04-14', 'OmShantiOm.wav' , '00:08:57',1 );
insert into podcast values(20003, 'Why this Kolavari', 'Raju', '2020-10-11', 'WhyThisKolavari.wav' , '00:04:08',2 );

