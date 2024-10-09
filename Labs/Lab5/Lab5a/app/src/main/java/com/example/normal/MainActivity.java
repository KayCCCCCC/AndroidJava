package com.example.normal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView userRecyclerView;
    private List<User> userList;
    private Adapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        userRecyclerView = findViewById(R.id.recycleView);
        SeedData();

        // create adapter
        userAdapter = new Adapter(MainActivity.this, userList);
        userRecyclerView.setAdapter(userAdapter);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
    private void SeedData() {
        userList = new ArrayList<User>();
        userList.add(new User("JohnD", "John Doe", "johndoe@example.com"));
        userList.add(new User("EmilyB", "Emily Brown", "emilybrown@example.com"));
        userList.add(new User("MichaelS", "Michael Smith", "michaelsmith@example.com"));
        userList.add(new User("SophiaJ", "Sophia Johnson", "sophiajohnson@example.com"));
        userList.add(new User("JamesW", "James Williams", "jameswilliams@example.com"));
    }
}