package com.anisjamadar26.triviaappdagger.ui.summary;

import com.anisjamadar26.triviaappdagger.dagger.FragmentScope;
import com.anisjamadar26.triviaappdagger.database.GameRoomDatabase;
import com.anisjamadar26.triviaappdagger.entity.GameEntity;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class SummaryViewModel {
    private GameRoomDatabase gameRoomDatabase;
    private CompositeDisposable compositeDisposable;

    @Inject
    public SummaryViewModel(GameRoomDatabase gameRoomDatabase, CompositeDisposable compositeDisposable) {
        this.gameRoomDatabase = gameRoomDatabase;
        this.compositeDisposable = compositeDisposable;
    }

    public void addData(GameEntity gameEntity) {
        compositeDisposable.add(
                gameRoomDatabase
                    .gameDao()
                    .insert(gameEntity)
                    .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                    }
                })
        );
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
}
