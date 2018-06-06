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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float dX, dX2,dY,dY2;
    private View ImageView;
    int checkPlace =0;
    int changestyle = 4;
    int changeshape = 3;
    Button stop,jump,start,change,shape;
    View sample;
    ObjectAnimator startRun;
    Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout background = (LinearLayout) findViewById(R.id.background);
        background.setBackgroundColor(Color.WHITE);

        shape = (Button) findViewById(R.id.changeshape);
        sample = (View) findViewById(R.id.sample);
        change = (Button) findViewById(R.id.changeStyle);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        jump = (Button) findViewById(R.id.jump);
        ImageView = findViewById(R.id.run);
        ImageView.setVisibility(View.INVISIBLE);

        stop.setClickable(false);
        stop.setBackgroundColor(Color.RED);
        start.setBackgroundColor(Color.GREEN);
        start.setClickable(true);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView run = (ImageView) findViewById(R.id.run);
                run.setImageResource(R.drawable.running);

                AnimationDrawable runMan = (AnimationDrawable) run.getDrawable();
                runMan.start();

                dX2 = ImageView.getX();
                if(checkPlace==1){

                }else{
                    ImageView.setX(-500);
                }

                ImageView.setVisibility(View.VISIBLE);
                startRun = ObjectAnimator.ofFloat(ImageView, "translationX", 650f);
                startRun.setDuration(3000);



                if(checkPlace==1){
                    ImageView.setX(dX2);
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
                dY2 = ImageView.getY();
                ObjectAnimator up = ObjectAnimator.ofFloat(ImageView, "translationY", dY2-600);
                up.setDuration(400);
                ObjectAnimator down = ObjectAnimator.ofFloat(ImageView, "translationY", dY2-287);
                down.setDuration(350);
                bouncer.play(down).after(up);
                bouncer.start();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            dX = ImageView.getX();
          ImageView run = (ImageView) findViewById(R.id.run);
          run.setImageResource(R.drawable.running);

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

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (changestyle%4){
                    case 0:{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            jump.setTextAppearance(R.style.text);
                            change.setTextAppearance(R.style.text);
                            changestyle++;
                        }else{
                            Toast.makeText(mContext,"your device API wont support style change", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 1:{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            jump.setTextAppearance(R.style.text2);
                            change.setTextAppearance(R.style.text2);
                            changestyle++;
                        }else{
                            Toast.makeText(mContext,"your device API wont support style change", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 2:{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            jump.setTextAppearance(R.style.text3);
                            change.setTextAppearance(R.style.text3);
                            changestyle++;
                        }else{
                            Toast.makeText(mContext,"your device API wont support style change", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 3:{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            jump.setTextAppearance(R.style.text4);
                            change.setTextAppearance(R.style.text4);
                            changestyle++;
                        }else{
                            Toast.makeText(mContext,"your device API wont support style change", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            }
        });

        shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (changeshape%3){
                    case 0:{sample.setBackgroundResource(R.drawable.shape1);changeshape++;break;}
                    case 1:{sample.setBackgroundResource(R.drawable.shape2);changeshape++;break;}
                    case 2:{sample.setBackgroundResource(R.drawable.shape3);changeshape++;break;}
                }
            }
        });

        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView.setOnTouchListener(new View.OnTouchListener() {

                    public boolean onTouch(View view, MotionEvent motionEvent) {

                            switch (motionEvent.getAction()) {

                                case MotionEvent.ACTION_DOWN:

                                    dX = view.getX() - motionEvent.getRawX();
                                    dY = view.getY() - motionEvent.getRawY();
                                    break;

                                case MotionEvent.ACTION_MOVE:

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