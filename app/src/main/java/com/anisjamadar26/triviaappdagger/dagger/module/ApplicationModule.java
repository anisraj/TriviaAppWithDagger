package com.anisjamadar26.triviaappdagger.dagger.module;

import android.content.Context;

import androidx.room.Room;

import com.anisjamadar26.triviaappdagger.MyApplication;
import com.anisjamadar26.triviaappdagger.dagger.qualifiers.ApplicationContext;
import com.anisjamadar26.triviaappdagger.database.GameRoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ApplicationModule {
    private MyApplication application;

    public ApplicationModule(MyApplication application) {
        this.application = application;
    }

    @ApplicationContext
    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    GameRoomDatabase providesGameRoomDatabase() {
        return Room.databaseBuilder(
                application,
                GameRoomDatabase.class,
                "db_name"
        ).build();
    }
}
