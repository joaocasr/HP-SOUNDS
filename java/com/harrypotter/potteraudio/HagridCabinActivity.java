package com.harrypotter.potteraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HagridCabinActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton playbutton,pausebutton;
    ImageView background,outbtn,inbtn;
    boolean out = false;
    boolean in = false;
    ImageView nextbtn,previousbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hagrid_cabin);

        playbutton = findViewById(R.id.play);
        pausebutton = findViewById(R.id.pause);
        background = findViewById(R.id.hagrids);
        outbtn = findViewById(R.id.out);
        inbtn = findViewById(R.id.in);
        nextbtn = findViewById(R.id.next);
        previousbtn = findViewById(R.id.previous);

        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                background.setImageResource(R.drawable.hagridcabin);
                outbtn.setVisibility(View.INVISIBLE);
                inbtn.setVisibility(View.VISIBLE);
            }
        });
        inbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                background.setImageResource(R.drawable.interiorcabinhagrid);
                outbtn.setVisibility(View.VISIBLE);
                inbtn.setVisibility(View.INVISIBLE);
            }
        });

        previousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HagridCabinActivity.this,BridgeActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HagridCabinActivity.this,ForbidenForestActivity.class);
                startActivity(intent2);
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

    public void play(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this,R.raw.hagrids);
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