package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	private ArrayAdapter<Tweet> adapter;
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private Button saveButton;
	private Button clearButton;
	private LonelyTwitterActivity activity = this;

	public ListView getOldTweetsList() {
		return oldTweetsList;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);// view
		setContentView(R.layout.main); // view

		bodyText = (EditText) findViewById(R.id.body); //view
		saveButton = (Button) findViewById(R.id.save); //view
		clearButton = (Button) findViewById(R.id.clear); //view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); //view

		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_OK);
				tweets.clear(); //controller
				adapter.notifyDataSetChanged(); //view
				saveInFile(); //model
			}
		});
		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString(); //controller
				tweets.add(new NormalTweet(text)); //controller
				adapter.notifyDataSetChanged(); //view
				saveInFile(); //model

			}
		});
		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart(); //view
		loadFromFile(); //controller
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets); //model
		oldTweetsList.setAdapter(adapter); //controller
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME); //model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); //model
			Gson gson = new Gson(); //model
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); //model
			tweets = gson.fromJson(in,listType); //model

		} catch (FileNotFoundException e) {
			tweets = new ArrayList<Tweet>(); //model
		} catch (IOException e) {
			throw new RuntimeException(e); //view
		}
	}

	public EditText getBodyText() {
		return bodyText;
	}

	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,0); //Model
			OutputStreamWriter writer = new OutputStreamWriter(fos); //model
			Gson gson = new Gson(); //model
			gson.toJson(tweets, writer); //model
			writer.flush(); //model
			fos.close(); //controller

		} catch (FileNotFoundException e) {
				throw new RuntimeException(e); //view
		} catch (IOException e) {
				throw new RuntimeException(e); //view
		}
	}
}