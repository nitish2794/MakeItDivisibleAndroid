package com.example.android.simpleaddition;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nitish on 3/4/2016.
 */
public class HighScoreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_scores);
        getSupportActionBar().hide();

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        //db.addScore(new Score("Ravi", 23));
        //db.addScore(new Score("Srinivas", 21));
        //db.addScore(new Score("Tommy", 14));
        //db.addScore(new Score("Karthik", 22));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        System.out.println("Reading: " + "Reading all contacts..");
        List<Score> contacts = db.getAllScores();
        String log = "";
        for (Score cn : contacts) {
            log = log + " " + cn.getName() + "  -  " + cn.getScore() + "\n";
            // Writing Contacts to log
            //Log.d("Name: ", log);
            System.out.println("Name: " + log);

        }
        TextView scoreview = (TextView) findViewById(R.id.scoreview);
        scoreview.setText(log);

    }
}
