package app.com.cvjuanresume.juansandoval.cvjuanresume;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.AboutMeFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.AchievementsFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.ContactMeFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.EducationFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.ExperienceFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.FragmentDrawer;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.SkillsFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.fragments.SocialMediaFragment;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.SQLiteHandler;
import app.com.cvjuanresume.juansandoval.cvjuanresume.utils.SessionManager;


public class MainActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());
                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                //  If the activity has never started before...
                if (isFirstStart) {
                    //  Launch app intro
                    Intent i = new Intent(MainActivity.this, WalkThroughActivity.class);
                    startActivity(i);
                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();
                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);
                    //  Apply changes
                    e.apply();
                }
            }
        });
        // Start the thread
        t.start();
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
            logoutUser();
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
                fragment = new AchievementsFragment();
                title = getString(R.string.title_achievements);
                break;
            case 4:
                fragment = new SkillsFragment();
                title = getString(R.string.title_skills);
                break;
            case 5:
                fragment = new SocialMediaFragment();
                title = getString(R.string.title_socialmedia);
                break;
            case 6:
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

    public void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
