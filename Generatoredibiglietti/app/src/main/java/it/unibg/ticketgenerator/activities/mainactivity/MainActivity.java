package it.unibg.ticketgenerator.activities.mainactivity;

import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
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
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

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

    private MaterialButton bookButton;
    private AppCompatImageButton logoutButton;

    private TextView welcomeText;
    private TextView ticketIdText;
    private TextView ticketBeforeText;
    private TextView ticketInfo1Text;
    private TextView ticketInfo2Text;

    private TextView msgTextMain;
    private ImageView arrowImageMain;

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

        msgTextMain = findViewById(R.id.msgTextMain);
        arrowImageMain = findViewById(R.id.arrowImageMain);

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
                sharedPreferenceRepository.saveLong("ticket", 0);
                startAuthenticationActivity();
            }
        });

        checkTicketExits();

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
        if(ticket != null){
            msgTextMain.setVisibility(View.GONE);
            arrowImageMain.setVisibility(View.GONE);

            ticketIdText.setText(String.format(Locale.ITALY, "%s%03d", ticket.getPriority(), ticket.getTicketNumber()));

            ticketInfo1Text.setText(sharedPreferenceRepository.getString("username"));

            ticketInfo2Text.setText(getDateFromTimestamp(ticket.getCreationTime()));

            ticketLayout.animate().setInterpolator(new OvershootInterpolator()).scaleX(1).scaleY(1).setDuration(250).start();

            bookButton.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.shapebuttontransparent));
            bookButton.setTextColor(getColor(R.color.white));
            bookButton.setText("BIGLIETTO\nPRENOTATO");
            bookButton.setIcon(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_check));

        } else {
            msgTextMain.setVisibility(View.VISIBLE);
            arrowImageMain.setVisibility(View.VISIBLE);

            sharedPreferenceRepository.saveLong("ticket", 0);
        }
    }

    private String getDateFromTimestamp(long creationTime) {
        Date date = new Date(creationTime);
        Format format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ITALY);
        return format.format(date);
    }


    private void checkTicketExits() {
        if(sharedPreferenceRepository.getLong("ticket") != 0) {
            mPresenter.getTicket(sharedPreferenceRepository.getString("token"), sharedPreferenceRepository.getLong("ticket"));
        }
    }
}