package com.example.goalmate.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_messages",
        foreignKeys = {
                @ForeignKey(entity = Goal.class, parentColumns = "id", childColumns = "goal_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE)
        })
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int goal_id;
    public int user_id;

    @NonNull
    public String role;

    @NonNull
    public String content;

    public long created_at = System.currentTimeMillis();
}