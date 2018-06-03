package com.example.ss12dark.animationexample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    float dX, dY; //for location and movement
    private View ImageView; // for using inside functions (this is the imageView)
    int checkPlace =0;
    Button stop,jump,start;
    ObjectAnimator startRun;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//-----------the background need to be white for better visual with the image------
        LinearLayout background = (LinearLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.WHITE);
//------------now i get the buttons and the image view from the layout xml -------
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        jump = (Button) findViewById(R.id.jump);
        ImageView = findViewById(R.id.run);
        ImageView.setVisibility(View.INVISIBLE);
//----------here i set OnClick to start/stop the animation list
        stop.setClickable(false);
        stop.setBackgroundColor(Color.RED);
        start.setBackgroundColor(Color.GREEN);
        start.setClickable(true);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView run = (ImageView) findViewById(R.id.run);
                run.setImageResource(R.drawable.running);
                //------here i put the animation on the imageView----
                AnimationDrawable runMan = (AnimationDrawable) run.getDrawable();
                runMan.start();
                //------------now i put the image view movement to go 700dp right and just go outside the screen----------
                dY = ImageView.getX();
                if(checkPlace==1){

                }else{
                    ImageView.setX(-500);
                }

                ImageView.setVisibility(View.VISIBLE);
                startRun = ObjectAnimator.ofFloat(ImageView, "translationX", 650f);
                startRun.setDuration(3000);



                if(checkPlace==1){
                    ImageView.setX(dY);
                    startRun.start();
                    checkPlace=0;
                    start.setClickable(true);
                    start.setBackgroundColor(Color.GREEN);
                    stop.setClickable(false);
                    stop.setBackgroundColor(Color.RED);
                    start.setText("restart");
                }else {
                    startRun.setRepeatCount(Animation.INFINITE);
                    startRun.start();
                    start.setClickable(false);
                    start.setBackgroundColor(Color.RED);
                    stop.setClickable(true);
                    stop.setBackgroundColor(Color.GREEN);
                }
            }
        });

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet bouncer = new AnimatorSet();
                ObjectAnimator fadeaAnim = ObjectAnimator.ofFloat(ImageView, "translationY", -200f);
                fadeaAnim.setDuration(250);
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(ImageView, "translationY", 50f);
                fadeAnim.setDuration(400);
                bouncer.play(fadeAnim).after(fadeaAnim);
                bouncer.start();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            dX = ImageView.getX();
          ImageView run = (ImageView) findViewById(R.id.run);
          run.setImageResource(R.drawable.running);
            //------here i put the animation on the imageView----
          AnimationDrawable runMan = (AnimationDrawable) run.getDrawable();
              runMan.stop();
              startRun.setRepeatCount(0);
              startRun.pause();
              ImageView.setX(dX);
                checkPlace=1;
                start.setClickable(true);
                start.setBackgroundColor(Color.GREEN);
                stop.setClickable(false);
                stop.setBackgroundColor(Color.RED);
                start.setText("finish");
          }

        });
//----------here i add the option to move the imageView with combination of OnClick and OnTouch-----
        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView.setOnTouchListener(new View.OnTouchListener() {

                    public boolean onTouch(View view, MotionEvent motionEvent) {

                            switch (motionEvent.getAction()) {
//--------i get the motionEvent from the OnTouch and check with switch what action he do and get the Y and X from the location -----
                                case MotionEvent.ACTION_DOWN:

                                    dX = view.getX() - motionEvent.getRawX();
                                    dY = view.getY() - motionEvent.getRawY();
                                    break;

                                case MotionEvent.ACTION_MOVE:
//----in case its movvement, i use animate to move it with he finger -------
                                    view.animate()
                                            .x(motionEvent.getRawX() + dX)
                                            .y(motionEvent.getRawY() + dY)
                                            .setDuration(0)
                                            .start();
                                    break;
                                default:
                                    return false;
                            }



                        return true;
                    }
                });
            }
        });
    }


}