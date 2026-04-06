package com.example.goalmate;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalmate.data.AppDatabase;
import com.example.goalmate.data.entity.User;
import com.example.goalmate.ui.UserAdapter;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Обов'язкові системні виклики (ти їх випадково видалив)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Знаходимо наш список на екрані та налаштовуємо його
        RecyclerView recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Підключаємо базу даних
        AppDatabase db = AppDatabase.getDatabase(this);

        // Робимо запит до БД у фоновому потоці
        Executors.newSingleThreadExecutor().execute(() -> {
            List<User> userList = db.appDao().getAllUsers();

            // Оновлюємо екран обов'язково в головному потоці (UI Thread)
            runOnUiThread(() -> {
                UserAdapter adapter = new UserAdapter(userList);
                recyclerView.setAdapter(adapter);
            });
        });
    }
}