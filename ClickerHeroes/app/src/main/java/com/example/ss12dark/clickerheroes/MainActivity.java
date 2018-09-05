package com.example.ss12dark.clickerheroes;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView monsterImage;
    Button weapon1,weapon2,weapon3,weapon4;
    TextView weaponNumber1,weaponNumber2,weaponNumber3,weaponNumber4,gold,lvl,myName,damage;
    ProgressBar monsterHp;
    int dmg,lvlnum,money,monsterGiveMoney;
    int n1,n2,n3,n4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try{ getSupportActionBar().hide(); }catch (NullPointerException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();}
        setContentView(R.layout.activity_main);
        monsterImage = (ImageView) findViewById(R.id.monster);
        weapon1 = (Button) findViewById(R.id.w1);
        weapon2 = (Button) findViewById(R.id.w2);
        weapon3 = (Button) findViewById(R.id.w3);
        weapon4 = (Button) findViewById(R.id.w4);
        weaponNumber1 = (TextView) findViewById(R.id.wn1);
        weaponNumber2 = (TextView) findViewById(R.id.wn2);
        weaponNumber3 = (TextView) findViewById(R.id.wn3);
        weaponNumber4 = (TextView) findViewById(R.id.wn4);
        gold = (TextView) findViewById(R.id.money);
        lvl = (TextView) findViewById(R.id.lvl);
        myName = (TextView) findViewById(R.id.myName);
        damage = (TextView) findViewById(R.id.damage);
        monsterHp = (ProgressBar) findViewById(R.id.progressBar);

        monsterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monsterImage.setBackgroundResource(R.drawable.untitled);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        monsterImage.setBackgroundResource(R.drawable.untitleds);
                    }
                }, 200);
                monsterHp.setProgress(monsterHp.getProgress()-dmg);
                if(monsterHp.getProgress()<=0){
                    nextlvl();
                }
            }
        });


        startlvl1();
    }

    public void startlvl1(){
        lvlnum = 1;
        monsterGiveMoney =5;
        dmg =1;
        damage.setText("your damage: "+dmg);
        n1=0;
        n2=0;
        n3=0;
        n4=0;
        money=0;
        gold.setText("gold: "+money);
        monsterHp.setMax(10);
        monsterHp.setProgress(10);
    }
    public void nextlvl(){

        lvlnum++;
        lvl.setText("lvl "+lvlnum);
        if(lvlnum%10==0){
            boss();
        }else {
            int half = monsterHp.getMax() / 2;
            monsterHp.setMax(half * 3);
            monsterHp.setProgress(monsterHp.getMax());

        }
    }

    public void boss(){
        int half = monsterHp.getMax() / 2;
        monsterHp.setMax(half * 5);
        monsterHp.setProgress(monsterHp.getMax());
    }

    public void buyWeapon(View v){
        if(v==weapon1){
            dmg=dmg+500;
            n1++;
            weaponNumber1.setText(n1+"");
            damage.setText("your damage: "+dmg);
        }else if(v==weapon2){
            dmg=dmg+150;
            n2++;
            weaponNumber2.setText(n2+"");
            damage.setText("your damage: "+dmg);
        }else if(v==weapon3){
            dmg=dmg+50;
            n3++;
            weaponNumber3.setText(n3+"");
            damage.setText("your damage: "+dmg);
        }else if(v==weapon4){
            dmg=dmg+10;
            n4++;
            weaponNumber4.setText(n4+"");
            damage.setText("your damage: "+dmg);
        }
    }

}
