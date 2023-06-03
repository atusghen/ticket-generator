package it.unibg.ticketgenerator.activities.authenticationactivity;

import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import it.unibg.ticketgenerator.R;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivity;

@AndroidEntryPoint
public class AutheticationActivity extends AppCompatActivity implements AutheticationActivityContract.View {

    @Inject
    AutheticationActivityContract.Presenter mPresenter;

    private TextInputLayout username;
    private TextInputLayout password;

    private TextInputLayout newUsername;
    private TextInputLayout newPassword;
    private TextInputLayout newName;
    private TextInputLayout newSurname;
    private TextInputLayout newCf;

    private Button loginButton;
    private Button registerButton;

    private ConstraintLayout loginLayout;
    private ConstraintLayout registerLayout;

    private ConstraintLayout loadingCircle;

    boolean login = true;

    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_authentication);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        username = findViewById(R.id.usernameTextInputLayout);
        password = findViewById(R.id.passwordTextInputLayout);

        newUsername = findViewById(R.id.newUsernameTextInputLayout);
        newPassword = findViewById(R.id.newPasswordTextInputLayout);
        newName = findViewById(R.id.newNameTextInputLayout);
        newSurname = findViewById(R.id.newSurnameTextInputLayout);
        newCf = findViewById(R.id.newCfTextInputLayout);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginLayout = findViewById(R.id.constraintLayoutLogin);
        registerLayout = findViewById(R.id.constraintLayoutRegister);

        loadingCircle = findViewById(R.id.loadingCircle);

        registerLayout.setTranslationX(displayMetrics.widthPixels);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!login) {
                    login = true;

                    loginLayout.animate().alpha(1).setDuration(250).start();
                    loginLayout.animate().translationX(0).setDuration(250).start();
                    registerLayout.animate().translationX(displayMetrics.widthPixels).setDuration(250).start();
                    registerLayout.animate().alpha(0).setDuration(250).start();


                    registerButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.shapebuttontransparent, null));
                    loginButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.shapebuttonwhite, null));

                    registerButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
                    loginButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.our_blue, null));
                } else {
                    if(username.getEditText().getText().toString().isEmpty() || password.getEditText().getText().toString().isEmpty())
                        return;

                    mPresenter.login(username.getEditText().getText().toString(), password.getEditText().getText().toString());
                    startLoading();

                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login) {
                    login = false;

                    loginLayout.animate().translationX(-displayMetrics.widthPixels).setDuration(250).start();
                    loginLayout.animate().alpha(0).setDuration(250).start();
                    registerLayout.animate().translationX(0).setDuration(250).start();
                    registerLayout.animate().alpha(1).setDuration(250).start();

                    loginButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.shapebuttontransparent, null));
                    registerButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.shapebuttonwhite, null));

                    loginButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
                    registerButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.our_purple, null));
                } else {
                    if(newUsername.getEditText().getText().toString().isEmpty() || newPassword.getEditText().getText().toString().isEmpty() || newName.getEditText().getText().toString().isEmpty() || newSurname.getEditText().getText().toString().isEmpty() || newCf.getEditText().getText().toString().isEmpty()) {
                        return;
                    }

                    mPresenter.register(newUsername.getEditText().getText().toString(), newPassword.getEditText().getText().toString(), newName.getEditText().getText().toString(), newSurname.getEditText().getText().toString(), newCf.getEditText().getText().toString());
                    startLoading();
                }

            }
        });

    }

    public void startLoading() {
        loadingCircle.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        loadingCircle.setVisibility(View.GONE);
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.constraintLayoutLogin), message, Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void setLoginData(String username, String password) {
        this.username.getEditText().setText(username);
        this.password.getEditText().setText(password);
    }
}
