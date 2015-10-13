package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    public void testStart() throws Exception {
        Activity activity = getActivity();
    }
    public void testEditATweet() {
        //start lonley twitter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        //user clicks on a tweet they want to edit

        activity.getTweets().clear();
        getInstrumentation().waitForIdleSync();//makes sure all the threads finish



        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });
        getInstrumentation().waitForIdleSync();//makes sure all the threads finish

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();//makes sure all the threads finish
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null,false);


        final ListView oldTweetsList = activity.getOldTweetsList();
        final Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v,0,v.getId());
            }
        });
        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
        //test that the tweet being shown on the edit screen is the correct tweet we clicked on

        bodyText = receiverActivity.getEditableTweet();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("LOL");
            }
        });
        getInstrumentation().waitForIdleSync();//makes sure all the threads finish

        //test that we can edit the text of that tweet
        assert (bodyText.getText() == tweet);

        //save our edits

        //assert that our edits were saved and displayed properly


        receiverActivity.finish();

    }
}