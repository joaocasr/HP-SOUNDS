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

import java.util.ArrayList;
import java.util.List;

public class GrimmauldPlaceEntranceActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton playbutton,pausebutton;
    ImageView background,outbtn,inbtn,incousine;
    private InterstitialAd mInterstitialAd;

    List<Integer> backgrounds = new ArrayList<>();
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grimmauld_place);

        backgrounds.add(R.drawable.grimmauldplacemainentrance);
        backgrounds.add(R.drawable.grimauldcusine);
        backgrounds.add(R.drawable.blacktree);

        playbutton = findViewById(R.id.play);
        pausebutton = findViewById(R.id.pause);
        background = findViewById(R.id.grimmauldplacebackground);
        outbtn = findViewById(R.id.out);
        inbtn = findViewById(R.id.in);

        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter-=1;
                if(counter==-1) {
                    Intent intent = new Intent(GrimmauldPlaceEntranceActivity.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }else{
                    inbtn.setVisibility(View.VISIBLE);
                    background.setImageResource(backgrounds.get(counter));
                }
            }
        });
        inbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter+=1;
                if(counter==2) inbtn.setVisibility(View.INVISIBLE);
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
                            mInterstitialAd.show(GrimmauldPlaceEntranceActivity.this);
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
            mediaPlayer = MediaPlayer.create(this,R.raw.grimmauldplaceaudio);
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