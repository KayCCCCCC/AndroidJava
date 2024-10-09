package com.example.passing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnMain;
    Button btnInt;
    Button btnArray;
    Button btnObj;
    Button btnBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Binding String
        btnMain = findViewById(R.id.button);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SencondActivity.class);
                intent.putExtra("dataString", "binding data from main");
                startActivity(intent);
            }
        });

        // Binding Int
        btnInt = findViewById(R.id.button2);
        btnInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SencondActivity.class);
                intent.putExtra("dataInt", 6);
                startActivity(intent);
            }
        });

        // Binding Array
        btnArray = findViewById(R.id.button3);
        btnArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SencondActivity.class);
                String[] arrCourse = {"Android", "IOS", "Unity"};
                intent.putExtra("dataArray", arrCourse);
                startActivity(intent);
            }
        });

        // Binding Obj
        btnObj = findViewById(R.id.button4);
        btnObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SencondActivity.class);
                Student student = new Student("Trần Trung Hiếu", 2003);
                intent.putExtra("dataObj", student);
                startActivity(intent);
            }
        });
        // Binding Bundle
        btnBundle = findViewById(R.id.button5);
        btnBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SencondActivity.class);
                String[] arrCourse = {"Android", "IOS", "Unity"};
                Student student = new Student("Trần Trung Hiếu", 2003);

                Bundle bundle = new Bundle();
                bundle.putString("string", "binding data with string");
                bundle.putInt("int", 6);
                bundle.putStringArray("array", arrCourse);
                bundle.putSerializable("obj", student);

                intent.putExtra("dataBundle", bundle);
                startActivity(intent);
            }
        });
    }
}