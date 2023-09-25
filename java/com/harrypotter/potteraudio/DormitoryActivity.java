package com.harrypotter.potteraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DormitoryActivity extends AppCompatActivity {
    ImageView previous,mode,background;
    MediaPlayer mediaPlayer;
    ImageButton playbutton,pausebutton;
    boolean night = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dormitory);

        playbutton = findViewById(R.id.play);
        pausebutton = findViewById(R.id.pause);
        previous = findViewById(R.id.back);
        mode = findViewById(R.id.nightmode);
        background = findViewById(R.id.dormbackground);

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
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DormitoryActivity.this,CommonRoomsActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!night) {
                    night = true;
                    background.setImageResource(R.drawable.dormitorynight);
                    mode.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
                } else {
                    night = false;
                    background.setImageResource(R.drawable.dormitoryday);
                    mode.setImageResource(R.drawable.ic_baseline_mode_night_24);
                }
            }
        });

    }

    public void play(){
        if(mediaPlayer ==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.dormitoryaudio);
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
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void pause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }

    public void stop(){
        stopPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}