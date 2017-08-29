package app.com.cvjuanresume.juansandoval.cvjuanresume;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import app.com.cvjuanresume.juansandoval.cvjuanresume.firebaseauth.LoginActivityFire;

/**
 * Created by jsandoval on 19/04/17.
 */

/**
 * Activity that loads the VIDEO_INTRO of the App
 * This is the launch activity, that means this activity controls
 * the state of the walkthrough. With a Boolean flag i know if this
 * the first time the user is installing the app to show him
 * the Walkthrough tutorial of the app.
 */

public class SplashScreenActivity extends Activity {

    private static final String VIDEO_INTRO = "android.resource://app.com.cvjuanresume.juansandoval.cvjuanresume/raw/intro_nuevo";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setBackgroundDrawable(null);
        initFromLocal();
    }

    private void initFromLocal() {
        Uri videi = Uri.parse(VIDEO_INTRO);
        videoView = (VideoView) findViewById(R.id.fullscreen_content);
        videoView.setVideoURI(videi);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivityFire.class);
                    startActivity(intent);
                }
        });
        videoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }


}
