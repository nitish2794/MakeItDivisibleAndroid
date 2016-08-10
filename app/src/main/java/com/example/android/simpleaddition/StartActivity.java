package com.example.android.simpleaddition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nitish on 3/6/2016.
 */
public class StartActivity extends Activity implements Animation.AnimationListener {

    private final long startTime = 0 * 1000;
    private final long interval = 0 * 1000;
    Animation animZoomin;
    LinearLayout titletext;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        //getSupportActionBar().hide();
        titletext = (LinearLayout) findViewById(R.id.start_layout);
        animZoomin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animZoomin.setAnimationListener(this);
        // start the animation
        //titletext.startAnimation(animFadein);
        titletext.startAnimation(animZoomin);
        //countDownTimer = new MyCountDownTimer(startTime, interval);
        // countDownTimer.start();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation
        // check for fade in animation
        if (animation == animZoomin) {
            Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(nextScreen);
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // Animation is repeating
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // Animation started
    }


    //------------------- timer code -------------------------------
    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            // titletext.startAnimation(animZoomin);
            // Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
            // startActivity(nextScreen);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }
    }
    //---------------------------------------------------------------
}
