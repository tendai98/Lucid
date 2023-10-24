package com.tendai.bhebhe.lucid;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Locale;

public class ViewerActivity extends AppCompatActivity {

    private WebView splashRender;
    private Intent receivedIntent;
    private MediaPlayer player;
    private int lastPlayerSeekPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        getWindow().addFlags(FLAG_KEEP_SCREEN_ON);
        receivedIntent = getIntent();

        splashRender = findViewById(R.id.splash_render);
        lastPlayerSeekPosition = receivedIntent.getIntExtra("seek", 30000);
        initializeWebView(receivedIntent.getStringExtra("module"));
        initMediaPlayer(lastPlayerSeekPosition);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeWebView(receivedIntent.getStringExtra("module"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashRender.destroy();
        destroyMediaPlayer();
    }

    private void initializeWebView(String shaderModule) {
        splashRender.setWebViewClient(new WebViewClient());
        splashRender.getSettings().setJavaScriptEnabled(true);
        splashRender.setOnTouchListener((view, motionEvent) -> true);
        splashRender.loadUrl(String.format(Locale.ENGLISH ,"http://127.0.0.1:8080/%s", shaderModule));
    }

    private void initMediaPlayer(int startSeek) {
        player = MediaPlayer.create(this, R.raw.audio);
        if(player != null && !player.isPlaying()){
            player.seekTo(startSeek);
            player.start();
        }
    }

    private void destroyMediaPlayer(){
        player.stop();
        player.release();
        player = null;
    }
}