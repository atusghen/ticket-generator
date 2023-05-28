package it.unibg.ticketgenerator.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;
import it.unibg.ticketgenerator.R;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    MainActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}