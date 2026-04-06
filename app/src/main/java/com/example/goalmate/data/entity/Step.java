package com.example.goalmate.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "steps",
        foreignKeys = @ForeignKey(entity = Goal.class, parentColumns = "id", childColumns = "goal_id", onDelete = ForeignKey.CASCADE))
public class Step {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int goal_id;

    @NonNull
    public String title;

    public boolean is_completed = false;
    public long completed_at;
    public long created_at = System.currentTimeMillis();
}
