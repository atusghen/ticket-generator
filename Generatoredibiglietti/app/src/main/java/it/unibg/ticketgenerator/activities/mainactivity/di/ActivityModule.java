package it.unibg.ticketgenerator.activities.mainactivity.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivity;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivityContract;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivityPresenter;

@Module
@InstallIn(ActivityComponent.class)
public abstract class ActivityModule {
    @Binds
    abstract MainActivityContract.View bindActivity(MainActivity activity);

    @Binds
    abstract MainActivityContract.Presenter bindPresenter(MainActivityPresenter impl);
}
