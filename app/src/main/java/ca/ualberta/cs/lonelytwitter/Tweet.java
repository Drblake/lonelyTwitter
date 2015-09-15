package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by drblake on 9/15/15.
 */
public abstract class Tweet implements Tweetable{
    private String text;
    private Date date;
    private ArrayList<Mood> mood;

    public ArrayList<Mood> getMood() {
        return mood;
    }

    public void setMood(ArrayList<Mood> mood) {
        this.mood = mood;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.length() <= 140) {
            this.text = text;
        }else{
            throw new IllegalArgumentException("Tweets can't be that long, Shakespeare!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tweet(String text) {
        this.text = text;
        this.date = new Date();
    }

    public Tweet(String tweet, Date date) {
        this.text = tweet;
        this.date = date;


    }
    public abstract Boolean isImportant();


}
