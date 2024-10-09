package com.example.listadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listItem;
    ArrayList <String> listSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listItem = findViewById(R.id.listItem);

        listSub = new ArrayList<>();
        listSub.add("C#");
        listSub.add("JavaScript");
        listSub.add("TypeScript");
        listSub.add("Java");
        listSub.add("Unity");
        listSub.add("Ruby");
        listSub.add("Python");
        listSub.add("Go");
        listSub.add("Rust");


        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                listSub
        );
        listItem.setAdapter(adapter);

        // show item base one click
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, listSub.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //remove item base long click
        listItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listSub.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}