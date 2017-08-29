package app.com.cvjuanresume.juansandoval.cvjuanresume;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.com.cvjuanresume.juansandoval.cvjuanresume.firebaseauth.LoginActivityFire;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.AboutMeFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.ContactMeFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.EducationFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.ExperienceFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.FragmentDrawer;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.SkillsFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.SocialMediaFragment;

/**
 * Created by jsandoval on 18/04/17.
 */

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {


    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivityFire.class));
                    finish();
                }
            }
        };

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new AboutMeFragment();
                title = getString(R.string.title_aboutme);
                break;
            case 1:
                fragment = new EducationFragment();
                title = getString(R.string.title_education);
                break;
            case 2:
                fragment = new ExperienceFragment();
                title = getString(R.string.title_experience);
                break;
            case 3:
                fragment = new SkillsFragment();
                title = getString(R.string.title_skills);
                break;
            case 4:
                fragment = new SocialMediaFragment();
                title = getString(R.string.title_socialmedia);
                break;
            case 5:
                fragment = new ContactMeFragment();
                title = getString(R.string.title_contactme);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
            mToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
