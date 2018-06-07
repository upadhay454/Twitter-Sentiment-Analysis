package DataManager;

import SentimentAnalys.SentimentClassifier;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gourav Chandra Upadhayay on 26/04/2017.
 */
public class TwitterManager {

    SentimentClassifier sentClassifier;
    int LIMIT = 10000; // the number of retrieved tweets
    ConfigurationBuilder cb;
    Twitter twitter;

    public TwitterManager() {
        cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("k0hoAwYyNI93MKf1ukDSNDsJh");
        cb.setOAuthConsumerSecret("6ws3p0SllT8vpmHwfJTgp3w1Mg0Gto2JjIhlyWowx24sZeaAu8");
        cb.setOAuthAccessToken("852011005370433536-HoMTl2zw4d4IO4mWSTrbB0KbEXZ5ofe");
        cb.setOAuthAccessTokenSecret("lbIS2EYIKoqqzbSFt8MFK5KYS2YNsolBC1fEAimA2r2GF");
        twitter = new TwitterFactory(cb.build()).getInstance();
        sentClassifier = new SentimentClassifier();
    }

    public void performQuery(String inQuery) throws InterruptedException, IOException {

        Query query = new Query(inQuery);
        query.setLang("en");
        query.setCount(10000);  //10000
        try {
            int count = 0;
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("docs/neu", true)));
            QueryResult r;
            do {
                r = twitter.search(query);
                ArrayList ts = (ArrayList) r.getTweets();
                for (int i = 0; i < ts.size() && count < LIMIT; i++) {

                    count++;

                    Status t = (Status) ts.get(i);
                    User user = t.getUser();
//                    Place place = t.getPlace();

                    String text = t.getText();
                    System.out.println("Tweet: " + text);
                    System.out.println(text + "\n");

                    long tweetId = t.getId();
                    System.out.println("Tweet_Id: " + tweetId);

                    String name = t.getUser().getScreenName();
                    System.out.println("UserName: " + name);

                    String profileLocation = user.getLocation();
                    System.out.println("User_Location: " + profileLocation);

//                    String placeCountryCode = place.getCountryCode();
//                    System.out.println("Place_CountryId: " + placeCountryCode);
                    String sent = sentClassifier.classify(t.getText());
                    System.out.println("Sentiment_Analysis: " + sent);
                    System.out.println("");

                    //writer.println(text);
                }
            } while ((query = r.nextQuery()) != null && count < LIMIT);
            writer.close();

        } catch (TwitterException te) {
            System.out.println("Couldn't connect: " + te);
        }
    }
}
