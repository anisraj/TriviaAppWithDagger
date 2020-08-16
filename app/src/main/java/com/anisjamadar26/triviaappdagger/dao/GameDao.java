package com.anisjamadar26.triviaappdagger.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.anisjamadar26.triviaappdagger.entity.GameEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * The data access object, or Dao,
 * here I specify SQL queries and associate them with method calls.
 *
 * here I specify Insert operation and Get all Data operation
 */

@Dao
public interface GameDao {

    @Insert
    Single<Long> insert(GameEntity gameEntity);

    @Query("SELECT * FROM game_history_table")
    Single<List<GameEntity>> getAllGameData();
}
