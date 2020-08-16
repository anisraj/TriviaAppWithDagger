package com.anisjamadar26.triviaappdagger;

import android.app.Application;

import com.anisjamadar26.triviaappdagger.dagger.component.ApplicationComponent;
import com.anisjamadar26.triviaappdagger.dagger.component.DaggerApplicationComponent;
import com.anisjamadar26.triviaappdagger.dagger.module.ApplicationModule;
import com.anisjamadar26.triviaappdagger.database.GameRoomDatabase;

import javax.inject.Inject;

public class MyApplication extends Application {

    public ApplicationComponent applicationComponent;

    @Inject
    GameRoomDatabase gameRoomDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        getDependancies();
    }

    private void getDependancies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }
}
