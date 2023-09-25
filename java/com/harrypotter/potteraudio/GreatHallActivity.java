package com.harrypotter.potteraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class GreatHallActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton playbutton, pausebutton;
    ImageView previousbtn,nextbtn;
    ImageView mode, background, outbtn, inbtn;
    boolean night = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great_hall);

        playbutton = findViewById(R.id.play);
        pausebutton = findViewById(R.id.pause);
        mode = findViewById(R.id.nightmode);
        background = findViewById(R.id.greathallbackground);
        outbtn = findViewById(R.id.out);
        inbtn = findViewById(R.id.in);
        previousbtn = findViewById(R.id.previous);
        nextbtn = findViewById(R.id.next);



        previousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GreatHallActivity.this,HogwartsExpressActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GreatHallActivity.this,StaircaseActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });


        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!night) {
                    night = true;
                    background.setImageResource(R.drawable.greathallplacenight);
                    mode.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
                } else {
                    night = false;
                    background.setImageResource(R.drawable.greathallplace);
                    mode.setImageResource(R.drawable.ic_baseline_mode_night_24);
                }
            }
        });

    }

    public void play() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.greathall);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
            mediaPlayer.start();
        }else if(!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        stopPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
