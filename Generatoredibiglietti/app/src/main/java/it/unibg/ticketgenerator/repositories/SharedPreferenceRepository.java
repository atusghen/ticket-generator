package it.unibg.ticketgenerator.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

//class that handles the shared preferences
@Singleton
public class SharedPreferenceRepository {

    @Inject
    public SharedPreferenceRepository(@ApplicationContext Context context) {
        sharedPreferences = context.getSharedPreferences("it.unibg.ticketgenerator", Context.MODE_PRIVATE);
    }

    private SharedPreferences sharedPreferences;

    public void saveString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void saveBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void saveInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void saveLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void saveFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public void delete(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void deleteAll() {
        sharedPreferences.edit().clear().apply();
    }
}
