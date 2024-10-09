package com.example.passing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SencondActivity extends AppCompatActivity {
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.textView);
        btnBack = findViewById(R.id.button6);
        Intent intent = getIntent();

        // Check for string extra
        String data = intent.getStringExtra("dataString");
        if (data != null) {
            textView.setText(data);
        }
        // Check for int extra
        else if (intent.hasExtra("dataInt")) {
            int dataInt = intent.getIntExtra("dataInt", 10);
            textView.setText(String.valueOf(dataInt));
        }
        // Check for array extra
        else if (intent.hasExtra("dataArray")) {
            String[] arrCourse = intent.getStringArrayExtra("dataArray");
            if (arrCourse != null && arrCourse.length > 0) {
                textView.setText(Arrays.toString(arrCourse));
            }
        }
        // Check for Serializable object
        else if (intent.hasExtra("dataObj")) {
            Student student = (Student) intent.getSerializableExtra("dataObj");
            if (student != null) {
                textView.setText(student.getName() + " - " + student.getAge());
            }
        }
        // Check for bundle extra
        else if (intent.hasExtra("dataBundle")) {
            Bundle bundle = intent.getBundleExtra("dataBundle");
            if (bundle != null) {
                String string = bundle.getString("string");
                int number = bundle.getInt("int");
                String[] array = bundle.getStringArray("array");
                Student studentBundle = (Student) bundle.getSerializable("obj");

                textView.setText(
                        string + "\n" +
                                number + "\n" +
                                Arrays.toString(array) + "\n" +
                                (studentBundle != null ? studentBundle.getName() : "No Student")
                );
            }
        }

        // Back button click listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SencondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
