package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by drblake on 9/29/15.
 */
public class TweetList {
    public ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    public Tweet mostRecentTweet;

    public void add(Tweet tweet) {
        if (tweets.contains(tweet)){
            throw new IllegalArgumentException("Duplicate tweet found");
        }
        else{
            mostRecentTweet = tweet;
            tweets.add(mostRecentTweet);
        }

    }
    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }
    public int getCount(){
        return tweets.size();
    }
    public ArrayList<Tweet> getTweets(){
        return tweets;
    }
    public Boolean hasTweet(Tweet tweet){
        if (tweets.contains(tweet)){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }
    public void removeTweet(Tweet tweet){
        tweets.remove(tweet);
    }

}
