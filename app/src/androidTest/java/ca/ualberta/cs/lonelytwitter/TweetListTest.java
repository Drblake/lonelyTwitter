package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.FormatterClosedException;

/**
 * Created by drblake on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver {
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    private boolean weWereNotified;

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
        list.count();
        assertEquals(list.count(), 1);
        list.add(new NormalTweet("testing"));
        assertEquals(list.count(), 2);
    }
    public void testDuplicate(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        Tweet tweet2 = tweet;
        list.add(tweet);
        list.add(tweet2);
        list.count();
        assertEquals(list.count(), 1);
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
    public void myNotify(MyObservable observable){
        weWereNotified = Boolean.TRUE;
    }

    public void testObservable() {
        TweetList list = new TweetList();
        //needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        weWereNotified = Boolean.FALSE;
        //we shouldn't have been notified
        list.add(tweet);
        //we should have been notified
        assertTrue(weWereNotified);
    }
    public void testModifyTweetInList(){
        weWereNotified = Boolean.FALSE;
        TweetList list = new TweetList();
        //needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertTrue(weWereNotified);
        weWereNotified = Boolean.FALSE;
        //we shouldn't have been notified
        tweet.setText("different text");
        //we should have been notified
        assertTrue(weWereNotified);
    }

}
