package it.unibg.ticketgenerator.activities.mainactivity;

import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;
import it.unibg.ticketgenerator.R;
import it.unibg.ticketgenerator.activities.authenticationactivity.AutheticationActivity;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    MainActivityContract.Presenter mPresenter;

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);


        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.logout();
            }
        });

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
    public void startAuthenticationActivity() {
        Intent intent = new Intent(this, AutheticationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}