package it.unibg.ticketgenerator.activities.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import it.unibg.ticketgenerator.activities.MainActivity;
import it.unibg.ticketgenerator.activities.MainActivityContract;
import it.unibg.ticketgenerator.activities.MainActivityPresenter;

@Module
@InstallIn(ActivityComponent.class)
public abstract class ActivityModule {
    @Binds
    abstract MainActivityContract.View bindActivity(MainActivity activity);

    @Binds
    abstract MainActivityContract.Presenter bindPresenter(MainActivityPresenter impl);
}
