package app.com.cvjuanresume.juansandoval.cvjuanresume;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.CustomizedTextView;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.SampleSlide;

/**
 * Created by jsandoval on 19/04/17.
 */

public class WalkThroughActivity extends AppIntro {

    private CustomizedTextView title, textinfo, powered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addSlide(SampleSlide.newInstance(R.layout.slide_1));
        addSlide(SampleSlide.newInstance(R.layout.slide_2));
        addSlide(SampleSlide.newInstance(R.layout.slide_3));
        addSlide(SampleSlide.newInstance(R.layout.slide_4));
        setSkipText(getString(R.string.skip_walk));
        setDoneText(getString(R.string.done_walk));
        getWindow().setBackgroundDrawable(null);
        title = (CustomizedTextView)findViewById(R.id.title_walk);
        textinfo = (CustomizedTextView)findViewById(R.id.text_walk);
        powered = (CustomizedTextView)findViewById(R.id.powered_walk);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment ){
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(WalkThroughActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment ){
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(WalkThroughActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment){
        super.onSlideChanged(oldFragment,newFragment);
    }
}
