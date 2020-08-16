package com.anisjamadar26.triviaappdagger.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.anisjamadar26.triviaappdagger.dao.GameDao;
import com.anisjamadar26.triviaappdagger.entity.GameEntity;

/**
 * Room uses the DAO to issue queries to its database
 */

@Database(entities = {GameEntity.class}, version = 1, exportSchema = false)
public abstract class GameRoomDatabase extends RoomDatabase {

    public abstract GameDao gameDao();

}
