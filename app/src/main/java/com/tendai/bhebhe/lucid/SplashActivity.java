package com.tendai.bhebhe.lucid;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        WebView splashRender = findViewById(R.id.splash_render);
        splashRender.setWebViewClient(new WebViewClient());
        splashRender.getSettings().setJavaScriptEnabled(true);
        splashRender.setOnTouchListener((view, motionEvent) -> true);
        splashRender.loadUrl("http://127.0.0.1:8080/shader_app_2");


        player = MediaPlayer.create(this, R.raw.audio);
        if(player != null && !player.isPlaying()){
            player.seekTo(33000);
            player.start();
        }

        Thread startMenuThread = new Thread(() -> {
            try{
                Thread.sleep(10000);
                player.stop();
                player.release();
                player = null;
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                Thread.sleep(500);
                finish();
            }catch (Exception ignored){}
        });

        startMenuThread.start();
    }

}