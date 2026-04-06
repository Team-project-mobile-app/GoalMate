package com.example.goalmate.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.goalmate.data.dao.AppDao;
import com.example.goalmate.data.entity.ChatMessage;
import com.example.goalmate.data.entity.DailyTip;
import com.example.goalmate.data.entity.Goal;
import com.example.goalmate.data.entity.Step;
import com.example.goalmate.data.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Goal.class, Step.class, ChatMessage.class, DailyTip.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDao appDao();

    private static volatile AppDatabase INSTANCE;
    private static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "goalmate_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AppDao dao = INSTANCE.appDao();

                for (int i = 1; i <= 10; i++) {
                    User user = new User();
                    user.name = "Студент " + i;
                    user.email = "student" + i + "@university.edu.ua";
                    user.password_hash = "secure_hash_" + i;
                    dao.insertUser(user);
                }

                for (int i = 1; i <= 10; i++) {
                    DailyTip tip = new DailyTip();
                    tip.user_id = i;
                    tip.content = "Щоденна порада №" + i + ": Розбивай великі цілі на маленькі кроки.";
                    dao.insertTip(tip);
                }

                for (int i = 1; i <= 20; i++) {
                    Goal goal = new Goal();
                    goal.user_id = (i % 10 == 0) ? 10 : i % 10;
                    goal.title = "Моя амбітна ціль №" + i;
                    goal.description = "Детальний опис для цілі номер " + i;
                    goal.status = "active";
                    dao.insertGoal(goal);
                }

                for (int i = 1; i <= 20; i++) {
                    Step step = new Step();
                    step.goal_id = i;
                    step.title = "Зробити перший крок " + i;
                    step.is_completed = (i % 2 == 0);
                    dao.insertStep(step);
                }

                for (int i = 1; i <= 20; i++) {
                    ChatMessage msg = new ChatMessage();
                    msg.goal_id = i;
                    msg.user_id = (i % 10 == 0) ? 10 : i % 10;
                    msg.role = (i % 2 == 0) ? "assistant" : "user";
                    msg.content = msg.role.equals("user") ?
                            "Як мені досягти цієї цілі?" :
                            "Ось покроковий план дій для тебе...";
                    dao.insertMessage(msg);
                }
            });
        }
    };
}