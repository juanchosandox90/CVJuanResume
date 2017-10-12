package app.com.cvjuanresume.juansandoval.cvjuanresume.firebaseauth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import app.com.cvjuanresume.juansandoval.cvjuanresume.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jsandoval on 29/08/17.
 */

public class ResetPasswordActivity extends AppCompatActivity {
    /**
     *
     * ButterKnife Implementation
     */
    private FirebaseAuth auth;
    @BindView(R.id.email) EditText inputEmail;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.btn_back) Button btnBack;
    @BindView(R.id.btn_reset_password) Button btnReset;

    @OnClick(R.id.btn_reset_password)
    public void onClickReset(View v){
        String email = inputEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, getString(R.string.link_sent), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, getString(R.string.link_failed), Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @OnClick(R.id.btn_back)
    public void onClickBack(View v){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
    }

}
