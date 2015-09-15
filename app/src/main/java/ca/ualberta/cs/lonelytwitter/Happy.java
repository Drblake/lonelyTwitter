package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by drblake on 9/15/15.
 */
public class Happy extends Mood {
    public Happy(String mood) {
        super(mood);
    }

    public Happy(String mood, Date date) {
        super(mood, date);
    }
    @Override
    public String ShowMood(){
        return ":)";
    }
}
