package com.anisjamadar26.triviaappdagger.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The data for this app is played game data,
 * so this is entity for that data,
 * Room will create database table from this,
 * and will add many entities of this type in the table
 * , we also mention table name and column names in this entity and make primary key on one column
 */

@Entity(tableName = "game_history_table")
public class GameEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String dateTime;

    @ColumnInfo
    private String userName;

    @ColumnInfo
    private String bestCricketer;

    @ColumnInfo
    private String flagColors;

    public GameEntity(@NonNull String dateTime, String userName, String bestCricketer, String flagColors) {
        this.dateTime = dateTime;
        this.userName = userName;
        this.bestCricketer = bestCricketer;
        this.flagColors = flagColors;
    }

    @NonNull
    public String getDateTime() {
        return dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getBestCricketer() {
        return bestCricketer;
    }

    public String getFlagColors() {
        return flagColors;
    }
}
