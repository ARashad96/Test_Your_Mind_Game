package com.arashad96.androiddeveloperintermidatekit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Test_Your_Mind_Game extends AppCompatActivity {
    TextView timer;
    TextView additionalopertaion;
    TextView currentscore;
    TextView score;
    Button github;
    Button info;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button playagain;
    CountDownTimer countDownTimer;
    ArrayList<Integer> btnsvalue = new <Integer>ArrayList(Arrays.asList(0, 0, 0, 0));
    int z;
    int correct;
    int totalanswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test__your__mind__game);

        timer = findViewById(R.id.timer);
        additionalopertaion = findViewById(R.id.additionalopertaion);
        currentscore = findViewById(R.id.currentscore);
        score = findViewById(R.id.score);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        playagain = findViewById(R.id.playagain);

        //beginning of game
        createproblem();
        setvalues();


        //----------------

        //countdown timer 30s
        countDownTimer = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                //optional play music when game ends
                //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.winner);
                //mediaPlayer.start();

                score.setVisibility(View.VISIBLE);
                playagain.setVisibility(View.VISIBLE);

                score.setText(String.valueOf(correct + "/" + totalanswered));

                //deactivate buttons
                btn1.setClickable(false);
                btn2.setClickable(false);
                btn3.setClickable(false);
                btn4.setClickable(false);
            }
        }.start();
        //----------------
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkanswer(Integer.parseInt(btn1.getText().toString()));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkanswer(Integer.parseInt(btn2.getText().toString()));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkanswer(Integer.parseInt(btn3.getText().toString()));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkanswer(Integer.parseInt(btn4.getText().toString()));
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score.setVisibility(View.INVISIBLE);
                playagain.setVisibility(View.INVISIBLE);

                //activate buttons
                btn1.setClickable(true);
                btn2.setClickable(true);
                btn3.setClickable(true);
                btn4.setClickable(true);

                //reset timer
                countDownTimer.start();

                //reset score
                correct = 0;
                totalanswered = 0;
                currentscore.setText("score");

            }
        });
    }

    public void createproblem() {
        Random rand = new Random();
        int x = (int) (Math.random() * 50 + 1);
        int y = rand.nextInt(50);
        additionalopertaion.setText(x + " + " + y);

        z = x + y;

        Log.d("check", x + "   " + y + "    " + z);
    }

    public void setvalues() {
        Random rand = new Random();

        int random = (int) (Math.random() * 4 + 0);
        btnsvalue.set(random, z);
        for (int i = 0; i < 4; i++) {
            if (random == i) {
                //do nothing
            } else {
                btnsvalue.set(i, rand.nextInt(50));
            }
        }
        btn1.setText(String.valueOf(btnsvalue.get(0)));
        btn2.setText(String.valueOf(btnsvalue.get(1)));
        btn3.setText(String.valueOf(btnsvalue.get(2)));
        btn4.setText(String.valueOf(btnsvalue.get(3)));

        github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ARashad96/Test_Your_Mind_Game"));
                startActivity(intent);
            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(Test_Your_Mind_Game.this)
                        .setIcon(R.drawable.profile)
                        .setTitle("App info")
                        .setMessage("This app performing a simple calculation game using buttons, textview, toast, random, gridlayout and linearlayout.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }

    public void checkanswer(int value) {
        if (value == z) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            correct++;
            totalanswered++;
        } else {
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            totalanswered++;
        }
        currentscore.setText(correct + "/" + totalanswered);
        createproblem();
        setvalues();
    }
}
