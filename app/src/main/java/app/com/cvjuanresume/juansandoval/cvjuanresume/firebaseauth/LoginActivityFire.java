package app.com.cvjuanresume.juansandoval.cvjuanresume.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

import app.com.cvjuanresume.juansandoval.cvjuanresume.MainActivity;
import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jsandoval on 29/08/17.
 */

public class LoginActivityFire extends AppCompatActivity {
    /**
     *
     * ButterKnife Implementation
     */
    private FirebaseAuth auth;
    @BindView(R.id.email) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.btn_signup) Button btnSignup;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.btn_reset_password) Button btnReset;
    @Nullable @BindView(R.id.toolbar) Toolbar toolbar;

    @OnClick(R.id.btn_signup)
    public void onClickSign(View v){
        startActivity(new Intent(LoginActivityFire.this, SignupActivity.class));
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(View v){
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivityFire.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                inputPassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(LoginActivityFire.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginActivityFire.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    @OnClick(R.id.btn_reset_password)
    public void onClickReset(View v){
        startActivity(new Intent(LoginActivityFire.this, ResetPasswordActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivityFire.this, MainActivity.class));
            finish();
        }
        // set the view now
        setContentView(R.layout.activity_login_fire);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

}
