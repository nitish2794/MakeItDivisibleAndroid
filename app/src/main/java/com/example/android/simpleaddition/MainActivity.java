package com.example.android.simpleaddition;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    //================== variables ======================
    private final long startTime = 45 * 1000;
    private final long interval = 1 * 1000;
    public TextView text;
    String name = "";
    View popupView = null;
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        text = (TextView) this.findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime / 1000));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //-------------- my methods --------------------------------
    private void incrementScore() {
        TextView numberTextView = (TextView) findViewById(R.id.score);
        int score = Integer.parseInt(numberTextView.getText().toString());
        numberTextView.setText("" + (score + 1));
    }

    private void updateDiv() {
        TextView divTextView = (TextView) findViewById(R.id.div_text);
        // int prevdiv = Integer.parseInt(divTextView.getText().toString());
        TextView numberTextView = (TextView) findViewById(R.id.number);
        int number = Integer.parseInt(numberTextView.getText().toString());
        Random r = new Random();
        int Low = 2;
        int High = 10;
        int div = r.nextInt(High - Low) + Low;
        while (number % div == 0) {
            div = r.nextInt(High - Low) + Low;
        }
        divTextView.setText("" + div);
    }

    public void endGame() {

        TextView numberTextView = (TextView) findViewById(R.id.number);
        //int number = Integer.parseInt(numberTextView.getText().toString());
        TextView left1 = (TextView) findViewById(R.id.left1);
        int moves1 = Integer.parseInt(left1.getText().toString());
        TextView left2 = (TextView) findViewById(R.id.left2);
        int moves2 = Integer.parseInt(left2.getText().toString());
        TextView left3 = (TextView) findViewById(R.id.left3);
        int moves3 = Integer.parseInt(left3.getText().toString());
        TextView endTextView = (TextView) findViewById(R.id.end);
        TextView divTextView = (TextView) findViewById(R.id.div_text);
        TextView scoreTextView = (TextView) findViewById(R.id.score);
        TextView timerTextView = (TextView) findViewById(R.id.timer);
        final int score = Integer.parseInt(scoreTextView.getText().toString());

        //--------------- show popup and add to database ---------------------
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(popupView);
        //popupWindow.setWidth();
        //popupWindow.setHeight(200);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        ((TextView) popupWindow.getContentView().findViewById(R.id.score_text)).setText("Score : " + score);
        ((TextView) popupWindow.getContentView().findViewById(R.id.endMessage)).setText("Game Over!");
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 50, 50);

        Button close = (Button) popupView.findViewById(R.id.save);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_text = (EditText) popupView.findViewById(R.id.name);
                name = name_text.getText().toString();
                //int score=0;

                System.out.print("NAmE - " + name + " SCORE - " + score);

                saveScore(name, score);

                popupWindow.dismiss();
            }
        });

        //endTextView.setText("Game Over! Score : " + score);
        timerTextView.setText("45");
        numberTextView.setText("0");
        scoreTextView.setText("0");
        divTextView.setText("1");
        left1.setText("20");
        left2.setText("20");
        left3.setText("20");
        Button add1 = (Button) findViewById(R.id.p1);
        add1.setEnabled(true);
        Button add2 = (Button) findViewById(R.id.p2);
        add2.setEnabled(true);
        Button add3 = (Button) findViewById(R.id.p3);
        add3.setEnabled(true);
        countDownTimer.cancel();
        timerHasStarted = false;


    }

    public void checkMoves() {
        TextView numberTextView = (TextView) findViewById(R.id.number);
        //int number = Integer.parseInt(numberTextView.getText().toString());
        TextView left1 = (TextView) findViewById(R.id.left1);
        int moves1 = Integer.parseInt(left1.getText().toString());
        TextView left2 = (TextView) findViewById(R.id.left2);
        int moves2 = Integer.parseInt(left2.getText().toString());
        TextView left3 = (TextView) findViewById(R.id.left3);
        int moves3 = Integer.parseInt(left3.getText().toString());
        if ((moves1 == 0) && (moves2 == 0) && (moves3 == 0)) {
            text.setText("0 moves!");
            endGame();
        }
    }

    public int checkScore() {
        TextView scoreTextView = (TextView) findViewById(R.id.score);
        int score = Integer.parseInt(scoreTextView.getText().toString());
        return score;
    }

    public void clearEndText() {
        TextView endTextView = (TextView) findViewById(R.id.end);
        //int score = Integer.parseInt(scoreTextView.getText().toString());
        endTextView.setText("");
    }

    public void add1(View view) {
        if (checkScore() == 0) {
            //clearEndText();
        }
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;

        }
        TextView numberTextView = (TextView) findViewById(R.id.number);
        int number = Integer.parseInt(numberTextView.getText().toString());

        TextView divTextView = (TextView) findViewById(R.id.div_text);
        int div = Integer.parseInt(divTextView.getText().toString());
        numberTextView.setText("" + (number + 1));

        if ((number + 1) % div == 0) {
            incrementScore();
            updateDiv();
        }
        //------------------- decrementing the moves-------------------
        TextView left1 = (TextView) findViewById(R.id.left1);
        int moves1 = Integer.parseInt(left1.getText().toString());
        left1.setText("" + (moves1 - 1));
        if (moves1 == 1) {
            Button add1 = (Button) findViewById(R.id.p1);
            add1.setEnabled(false);
            checkMoves();
        }
        //-------------------------------------------------------------


    }

    public void add2(View view) {
        if (checkScore() == 0) {
            // clearEndText();
        }
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;

        }

        TextView numberTextView = (TextView) findViewById(R.id.number);
        int number = Integer.parseInt(numberTextView.getText().toString());
        //System.out.println("getting number");
        TextView divTextView = (TextView) findViewById(R.id.div_text);
        int div = Integer.parseInt(divTextView.getText().toString());
        // System.out.println("getting div");
        numberTextView.setText("" + (number + 2));


        if ((number + 2) % div == 0) {
            incrementScore();
            updateDiv();
        }
        //------------------- decrementing the moves-------------------
        TextView left2 = (TextView) findViewById(R.id.left2);
        int moves2 = Integer.parseInt(left2.getText().toString());
        left2.setText("" + (moves2 - 1));
        if (moves2 == 1) {
            Button add2 = (Button) findViewById(R.id.p2);
            add2.setEnabled(false);
            checkMoves();
        }
        //-------------------------------------------------------------


    }

    public void add3(View view) {
        if (checkScore() == 0) {
            //clearEndText();
        }
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;

        }

        TextView numberTextView = (TextView) findViewById(R.id.number);
        int number = Integer.parseInt(numberTextView.getText().toString());

        TextView divTextView = (TextView) findViewById(R.id.div_text);
        int div = Integer.parseInt(divTextView.getText().toString());
        numberTextView.setText("" + (number + 3));

        if ((number + 3) % div == 0) {
            incrementScore();
            updateDiv();
        }
        //------------------- decrementing the moves-------------------
        TextView left3 = (TextView) findViewById(R.id.left3);
        int moves3 = Integer.parseInt(left3.getText().toString());
        left3.setText("" + (moves3 - 1));
        if (moves3 == 1) {
            Button add3 = (Button) findViewById(R.id.p3);
            add3.setEnabled(false);
            checkMoves();
        }
        //-------------------------------------------------------------


    }

    public void goToHighScores(View view) {
        //Starting a new Intent
        Intent nextScreen = new Intent(getApplicationContext(), HighScoreActivity.class);
        startActivity(nextScreen);
    }
    //---------------------------------------------------------------

    //-----------------------------------------------------
    public void saveScore(String name, int score) {
        // ------ saving to db ---------------
        DatabaseHandler db = new DatabaseHandler(this);

        if (name.contentEquals(""))
            db.addScore(new Score("Anonymous", score));
        else
            db.addScore(new Score(name, score));
        db.close();
        //-----------------------------------------------------
    }

    //------------------- timer code -------------------------------
    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            text.setText("Time's up!");
            endGame();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText("" + millisUntilFinished / 1000);
        }
    }


}
