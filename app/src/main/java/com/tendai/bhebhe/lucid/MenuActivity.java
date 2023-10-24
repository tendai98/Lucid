package com.tendai.bhebhe.lucid;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MenuActivity extends AppCompatActivity {

    private WebView splashRender;
    private MediaPlayer player;
    private boolean isPlayerRunning = false;
    private int lastPlayerSeekPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        getWindow().addFlags(FLAG_KEEP_SCREEN_ON);
        splashRender = findViewById(R.id.splash_render);

        CardView shader3 = findViewById(R.id.shader_3);
        CardView shader4 = findViewById(R.id.shader_4);
        CardView shader5 = findViewById(R.id.shader_5);
        CardView shader6 = findViewById(R.id.shader_6);
        CardView shader7 = findViewById(R.id.shader_7);
        CardView shader8 = findViewById(R.id.shader_8);
        CardView shader9 = findViewById(R.id.shader_9);
        CardView shader10 = findViewById(R.id.shader_10);
        CardView shader11 = findViewById(R.id.shader_11);


        initializeWebView();
        initMediaPlayer(30000);

        shader3.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_3");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader4.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_4");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader5.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_5");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader6.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_7");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader7.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_8");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader8.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_9");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader9.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_10");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader10.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_11");
            startActivity(i);
            destroyMediaPlayer();
        });

        shader11.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ViewerActivity.class);
            i.putExtra("seek", lastPlayerSeekPosition);
            i.putExtra("module", "shader_app_6");
            startActivity(i);
            destroyMediaPlayer();
        });

        Thread playerSeekUpdateThread = new Thread(() -> {
            while (true) {
                try {
                    if (isPlayerRunning) {
                        lastPlayerSeekPosition = player.getCurrentPosition();
                    }
                    Thread.sleep(10);
                } catch (Exception ignored) {
                }
            }
        });

        playerSeekUpdateThread.start();

    }


    @Override
    protected void onResume() {
        super.onResume();
        initializeWebView();
        initMediaPlayer(lastPlayerSeekPosition);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashRender.destroy();
    }

    private void initMediaPlayer(int startSeek) {
        player = MediaPlayer.create(this, R.raw.audio);
        if (player != null && !player.isPlaying()) {
            player.seekTo(startSeek);
            player.start();
            isPlayerRunning = true;
        }
    }

    private void destroyMediaPlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    private void initializeWebView() {
        splashRender.setWebViewClient(new WebViewClient());
        splashRender.getSettings().setJavaScriptEnabled(true);
        splashRender.setOnTouchListener((view, motionEvent) -> true);
        splashRender.loadUrl("http://127.0.0.1:8080/shader_app_2");
    }

}