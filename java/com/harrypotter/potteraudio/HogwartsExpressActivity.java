package com.harrypotter.potteraudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class HogwartsExpressActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton playbutton,pausebutton;
    ImageView mode,background,outbtn,inbtn;
    boolean in = false;
    boolean night = false;
    ImageView nextbtn,previousbtn,imgsob;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hogwarts_express);

        playbutton = findViewById(R.id.play);
        pausebutton = findViewById(R.id.pause);
        mode = findViewById(R.id.nightmode);
        background = findViewById(R.id.expressbackground);
        outbtn = findViewById(R.id.out);
        inbtn = findViewById(R.id.in);
        nextbtn = findViewById(R.id.next);
        imgsob = findViewById(R.id.sobreposicao);

        previousbtn = findViewById(R.id.previous);

        previousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HogwartsExpressActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HogwartsExpressActivity.this,GreatHallActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        inbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(in && night){
                    in = false;
                    imgsob.setBackgroundColor(getResources().getColor(R.color.none));
                    background.setImageResource(R.drawable.hogwartsexpressnightplace);
                    outbtn.setVisibility(View.INVISIBLE);
                    inbtn.setVisibility(View.VISIBLE);
                }
                else if(!in && night){
                    in = true;
                    imgsob.setBackgroundColor(getResources().getColor(R.color.brown));
                    background.setImageResource(R.drawable.hogwartsexpresscompartiment);
                    outbtn.setVisibility(View.VISIBLE);
                    inbtn.setVisibility(View.INVISIBLE);
                }else if(in && !night){
                    in = false;
                    imgsob.setBackgroundColor(getResources().getColor(R.color.none));
                    background.setImageResource(R.drawable.hogwartsexpresstra);
                    outbtn.setVisibility(View.INVISIBLE);
                    inbtn.setVisibility(View.VISIBLE);
                }else{
                    in = true;
                    imgsob.setBackgroundColor(getResources().getColor(R.color.brown));
                    background.setImageResource(R.drawable.hogwartscompartimentday);
                    outbtn.setVisibility(View.VISIBLE);
                    inbtn.setVisibility(View.INVISIBLE);
                }
            }
        });
        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!in && !night){
                    in = true;
                    background.setImageResource(R.drawable.hogwartscompartimentday);
                    imgsob.setBackgroundColor(getResources().getColor(R.color.brown));
                    outbtn.setVisibility(View.VISIBLE);
                    inbtn.setVisibility(View.INVISIBLE);
                }
                else if(in && !night){
                    in = false;
                    background.setImageResource(R.drawable.hogwartsexpresstra);
                    imgsob.setBackgroundColor(getResources().getColor(R.color.brown));
                    outbtn.setVisibility(View.INVISIBLE);
                    inbtn.setVisibility(View.VISIBLE);
                }else if(!in && night){
                    in = true;
                    background.setImageResource(R.drawable.hogwartsexpresscompartiment);
                    imgsob.setBackgroundColor(getResources().getColor(R.color.brown));
                    outbtn.setVisibility(View.VISIBLE);
                    inbtn.setVisibility(View.INVISIBLE);
                }else{
                    in = false;
                    background.setImageResource(R.drawable.hogwartsexpressnightplace);
                    imgsob.setBackgroundColor(getResources().getColor(R.color.brown));
                    outbtn.setVisibility(View.INVISIBLE);
                    inbtn.setVisibility(View.VISIBLE);
                }
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
                if(!night){
                    background.setImageResource(R.drawable.hogwartsexpressnightplace);
                    inbtn.setVisibility(View.GONE);
                    outbtn.setVisibility(View.VISIBLE);
                    night=true;
                    imgsob.setBackgroundColor(getResources().getColor(R.color.none));
                    outbtn.setVisibility(View.VISIBLE);
                    mode.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
                }else{
                    outbtn.setVisibility(View.GONE);
                    inbtn.setVisibility(View.VISIBLE);
                    night=false;
                    background.setImageResource(R.drawable.hogwartsexpresstra);
                    imgsob.setBackgroundColor(getResources().getColor(R.color.brown));
                    mode.setImageResource(R.drawable.ic_baseline_mode_night_24);
                }
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-1810794261274785/4223745722", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(HogwartsExpressActivity.this);
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }


    public void play(){
        if(mediaPlayer ==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.hogwartsexpress);
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