package com.harrypotter.potteraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class CommonRoomsActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton playbutton,pausebutton;
    ImageView background,nextplan,previous,next,godorms;
    List<Integer> backgrounds = new ArrayList<>();
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_rooms);

        backgrounds.add(R.drawable.commonroomplace);
        backgrounds.add(R.drawable.slytherin);
        backgrounds.add(R.drawable.ravenclaw);
        backgrounds.add(R.drawable.hufflepuff);

        playbutton = findViewById(R.id.play);
        pausebutton = findViewById(R.id.pause);
        background = findViewById(R.id.commonroombackground);
        nextplan = findViewById(R.id.nextplan);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        godorms = findViewById(R.id.godorms);

        nextplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter+=1;
                if(counter==4) counter =0;
                if(counter!=0) godorms.setVisibility(View.INVISIBLE);
                if(counter==0) godorms.setVisibility(View.VISIBLE);
                background.setImageResource(backgrounds.get(counter));
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
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommonRoomsActivity.this,StaircaseActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommonRoomsActivity.this,YardActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        godorms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommonRoomsActivity.this,DormitoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

    }
    public void play(){
        if(mediaPlayer ==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.commonroom);
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