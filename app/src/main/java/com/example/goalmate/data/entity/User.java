package com.example.goalmate.data.entity;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    @NonNull
    public String email;

    @NonNull
    public String password_hash;

    public int level = 1;
    public int streak_days = 0;
    public boolean notifications_enabled = true;
}
