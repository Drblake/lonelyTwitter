package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by drblake on 9/15/15.
 */
public class Sad extends Mood{
    public Sad(String mood) {
        super(mood);
    }

    public Sad(String mood, Date date) {
        super(mood, date);
    }
    @Override
    public String ShowMood(){
        return ":(";
    }
}
