package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by drblake on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testHoldsStuff(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }
    public void testHoldsManyThings(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        list.getCount();
        assertEquals(list.getCount(), 1);
        list.add(new NormalTweet("testing"));
        assertEquals(list.getCount(), 2);
    }
    public void testDuplicate(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        Tweet tweet2 = tweet;
        list.add(tweet);
        list.add(tweet2);
        list.getCount();
        assertEquals(list.getCount(), 1);
    }
    public void testGetTweets(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        Tweet tweet2 = new NormalTweet("testing");
        list.add(tweet);
        list.add(tweet2);
        ArrayList<Tweet> c = list.getTweets();
        assertEquals(c.get(1), tweet2);
        assertEquals(c.get(0), tweet);
    }
    public void testHasTweets(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        Tweet tweet2 = new NormalTweet("testing");
        Tweet tweet3 = tweet;
        list.add(tweet);
        list.add(tweet2);
        Boolean exists = list.hasTweet(tweet3);
        assertEquals(exists, Boolean.TRUE);
    }
    public void testRemoveTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        Tweet tweet2 = new NormalTweet("testing");
        list.add(tweet);
        list.add(tweet2);
        Boolean exists = list.hasTweet(tweet);
        assertEquals(exists, Boolean.TRUE);
        list.removeTweet(tweet);
        Boolean exists1 = list.hasTweet(tweet);
        assertEquals(exists1, Boolean.FALSE);
    }

}