package com.example.goalmate.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_tips",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class DailyTip {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int user_id;

    @NonNull
    public String content;
}