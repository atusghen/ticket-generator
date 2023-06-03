package it.unibg.ticketgenerator.activities.mainactivity;

import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;
import it.unibg.ticketgenerator.R;
import it.unibg.ticketgenerator.activities.authenticationactivity.AutheticationActivity;
import it.unibg.ticketgenerator.entities.Ticket;
import it.unibg.ticketgenerator.repositories.SharedPreferenceRepository;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    MainActivityContract.Presenter mPresenter;

    @Inject
    SharedPreferenceRepository sharedPreferenceRepository;

    private Button bookButton;
    private AppCompatImageButton logoutButton;

    private TextView welcomeText;
    private TextView ticketIdText;
    private TextView ticketBeforeText;
    private TextView ticketInfo1Text;
    private TextView ticketInfo2Text;

    private LinearLayout ticketShape;

    private ConstraintLayout ticketLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

        bookButton = findViewById(R.id.bookButton);

        logoutButton = findViewById(R.id.logoutButton);

        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText(String.format("Ciao %s!", sharedPreferenceRepository.getString("username")));

        ticketIdText = findViewById(R.id.ticketId);

        ticketBeforeText = findViewById(R.id.ticketBefore);

        ticketInfo1Text = findViewById(R.id.ticketInfo1);
        ticketInfo2Text = findViewById(R.id.ticketInfo2);

        ticketLayout = findViewById(R.id.ticketLayout);

        ticketShape = findViewById(R.id.ticket);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.createTicket(sharedPreferenceRepository.getString("token"));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceRepository.saveString("token", "");
                sharedPreferenceRepository.saveString("username", "");
                startAuthenticationActivity();
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

    @Override
    public void showTicket(Ticket ticket) {
        ticketIdText.setText(String.format(Locale.ITALY, "%s%03d", ticket.getPriority(), ticket.getTicketNumber()));

        ticketInfo1Text.setText(sharedPreferenceRepository.getString("username"));

        ticketInfo2Text.setText(getDateFromTimestamp(ticket.getCreationTime()));

        ticketLayout.animate().setInterpolator(new OvershootInterpolator()).scaleX(1).scaleY(1).setDuration(250).start();
    }

    private String getDateFromTimestamp(long creationTime) {
        Date date = new Date(creationTime);
        Format format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ITALY);
        return format.format(date);
    }
}