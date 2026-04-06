package com.example.goalmate.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "goals",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class Goal {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int user_id;

    @NonNull
    public String title;

    public String description;
    public long deadline;
    public int progress = 0;
    public String status = "active";
    public long created_at = System.currentTimeMillis();
    public long updated_at;
}