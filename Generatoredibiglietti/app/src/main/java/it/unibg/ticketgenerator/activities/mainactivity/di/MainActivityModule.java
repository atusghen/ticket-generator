package it.unibg.ticketgenerator.activities.mainactivity.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivity;

@Module
@InstallIn(ActivityComponent.class)
public class MainActivityModule {

    @Provides
    MainActivity provideActivity(Activity activity) {
        return (MainActivity) activity;
    }
}