package com.anisjamadar26.triviaappdagger.ui.history;

import androidx.lifecycle.MutableLiveData;

import com.anisjamadar26.triviaappdagger.dagger.FragmentScope;
import com.anisjamadar26.triviaappdagger.database.GameRoomDatabase;
import com.anisjamadar26.triviaappdagger.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * used rxjava for getting data from database
 */

@FragmentScope
public class HistoryViewModel {
    private GameRoomDatabase gameRoomDatabase;
    private CompositeDisposable compositeDisposable;

    private List<GameEntity> gameEntityList = new ArrayList<>();
    MutableLiveData<List<GameEntity>> gameLiveData = new MutableLiveData<>();

    @Inject
    public HistoryViewModel(GameRoomDatabase gameRoomDatabase, CompositeDisposable compositeDisposable) {
        this.gameRoomDatabase = gameRoomDatabase;
        this.compositeDisposable = compositeDisposable;
    }

    public void getAllData() {
        compositeDisposable
                .add(
                        gameRoomDatabase.gameDao()
                            .getAllGameData()
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Consumer<List<GameEntity>>() {
                                @Override
                                public void accept(List<GameEntity> gameEntities) throws Exception {
                                    gameEntityList = gameEntities;
                                    gameLiveData.postValue(gameEntities);

                                }
                            })
                );

    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
}
