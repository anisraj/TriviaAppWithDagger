package com.anisjamadar26.triviaappdagger.dagger.component;

import android.content.Context;

import com.anisjamadar26.triviaappdagger.MyApplication;
import com.anisjamadar26.triviaappdagger.dagger.module.ApplicationModule;
import com.anisjamadar26.triviaappdagger.dagger.qualifiers.ApplicationContext;
import com.anisjamadar26.triviaappdagger.database.GameRoomDatabase;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MyApplication application);

    @ApplicationContext
    Context getContext();

    CompositeDisposable getCompositeDisposable();

    GameRoomDatabase getGameRoomDatabase();
}
