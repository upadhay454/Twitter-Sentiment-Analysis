Twitter Sentiment Analysis in java using open-nlp machine learning lib


Twitter Sentiment Analyse using NLP 

Steps :
1. Make twitter application:
          setOAuthConsumerKey("k0hoAwYyNI93MKf1ukDSNDsJh");
        cb.setOAuthConsumerSecret("6ws3p0SllT8vpmHwfJTgp3w1Mg0Gto2JjIhlyWowx24sZeaAu8");
        cb.setOAuthAccessToken("852011005370433536-HoMTl2zw4d4IO4mWSTrbB0KbEXZ5ofe");
        cb.setOAuthAccessTokenSecret("lbIS2EYIKoqqzbSFt8MFK5KYS2YNsolBC1fEAimA2r2GF"); 

2. Insatll the MySql Database 5.1
by below mention detail
user name : root
password: root
Port no.=3306

3.create schema name twitter and create table

CREATE TABLE  `twitter`.`signup` (
  `User Id` varchar(45) NOT NULL default '',
  
`Name` varchar(45) default NULL,
  `Email Id` varchar(45) default NULL,
 
 `Password` varchar(45) default NULL,
  `Date Of Birth` varchar(45) default NULL,
 PRIMARY KEY  (`User Id`)
);



