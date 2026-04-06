package com.example.goalmate.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.goalmate.data.entity.ChatMessage;
import com.example.goalmate.data.entity.DailyTip;
import com.example.goalmate.data.entity.Goal;
import com.example.goalmate.data.entity.Step;
import com.example.goalmate.data.entity.User;

import java.util.List;

@Dao
public interface AppDao {
    // Методи для вставки даних
    @Insert
    void insertUser(User user);

    @Insert
    void insertGoal(Goal goal);

    @Insert
    void insertStep(Step step);

    @Insert
    void insertTip(DailyTip tip);

    @Insert
    void insertMessage(ChatMessage msg);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM goals WHERE user_id = :userId")
    List<Goal> getGoalsForUser(int userId);
}