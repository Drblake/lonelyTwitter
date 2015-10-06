package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by drblake on 9/29/15.
 */
//want to make observable
public class TweetList implements MyObservable, MyObserver{
    public ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    public Tweet mostRecentTweet;


    public void add(Tweet tweet) {
        if (tweets.contains(tweet)) {
            throw new IllegalArgumentException("Duplicate tweet found");
        } else {
            mostRecentTweet = tweet;
            tweets.add(mostRecentTweet);
            tweet.addObserver(this);
            notifyAllObservers();
        }
    }
    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }
    public int count(){
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

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer){
        observers.add(observer);
    }
    private void notifyAllObservers(){
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }
    public void myNotify(MyObservable observable){
        notifyAllObservers();
    }
}
