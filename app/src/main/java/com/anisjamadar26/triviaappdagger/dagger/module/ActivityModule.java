package com.anisjamadar26.triviaappdagger.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.anisjamadar26.triviaappdagger.dagger.qualifiers.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return activity;
    }
}
