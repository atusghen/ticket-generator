package it.unibg.ticketgenerator.activities.authenticationactivity.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import it.unibg.ticketgenerator.activities.authenticationactivity.AutheticationActivity;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivity;

@Module
@InstallIn(ActivityComponent.class)
public class AuthenticationActivityModule {

    @Provides
    AutheticationActivity provideActivity(Activity activity) {
        return (AutheticationActivity) activity;
    }
}