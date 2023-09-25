package com.harrypotter.potteraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ClockTowerCourtyardActivity extends AppCompatActivity {
    ImageView next,previous;
    MediaPlayer mediaPlayer;
    ImageButton playbutton,pausebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_tower_courtyard_main);

        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        playbutton = findViewById(R.id.play);
        pausebutton = findViewById(R.id.pause);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClockTowerCourtyardActivity.this,BridgeActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClockTowerCourtyardActivity.this,ClockTowerActivity.class);
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
    }
    public void play() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bridge);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
            mediaPlayer.start();
        } else if (!mediaPlayer.isPlaying()) {
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