package it.unibg.ticketgenerator.activities.authenticationactivity.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import it.unibg.ticketgenerator.activities.authenticationactivity.AutheticationActivity;
import it.unibg.ticketgenerator.activities.authenticationactivity.AutheticationActivityContract;
import it.unibg.ticketgenerator.activities.authenticationactivity.AutheticationActivityPresenter;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivity;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivityContract;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivityPresenter;

@Module
@InstallIn(ActivityComponent.class)
public abstract class ActivityModule {
    @Binds
    abstract AutheticationActivityContract.View bindActivity(AutheticationActivity activity);

    @Binds
    abstract AutheticationActivityContract.Presenter bindPresenter(AutheticationActivityPresenter impl);
}
