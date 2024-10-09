package com.example.listcrud;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText editText;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public ListView listViewItem;
    ArrayList<String> listSub;
    public int positionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText =  findViewById(R.id.editTextText);
        btnAdd = findViewById(R.id.button);
        btnUpdate = findViewById(R.id.button2);
        btnDelete = findViewById(R.id.button3);
        listViewItem = findViewById(R.id.listView);

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
        listViewItem.setAdapter(adapter);

        // handle read item
        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionItem = position;
                editText.setText(listSub.get(position));
                adapter.notifyDataSetChanged();
            }
        });

        // handle add item
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    editText.setError("REQUIRE");
                    Toast.makeText(MainActivity.this, "Please select item to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                listSub.add(editText.getText().toString());
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        // handle update item
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    editText.setError("REQUIRE");
                    Toast.makeText(MainActivity.this, "Please select item to update", Toast.LENGTH_SHORT).show();
                    return;
                }
                listSub.set(positionItem, editText.getText().toString());
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        // handle delete item
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    editText.setError("REQUIRE");
                    Toast.makeText(MainActivity.this, "Please select item to delete", Toast.LENGTH_SHORT).show();
                    return;
                }
                listSub.remove(positionItem);
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });


    }
}