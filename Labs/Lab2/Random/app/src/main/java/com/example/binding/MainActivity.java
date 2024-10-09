package com.example.binding;


import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Random Appication
TextView txtTitle;
TextView min;
TextView max;
EditText editMin;
EditText editMax;
Button btnRandom;
TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.random_application);

        txtTitle = (TextView) findViewById(R.id.title);
        min = (TextView) findViewById(R.id.Min);
        max = (TextView) findViewById(R.id.Max);
        editMin = (EditText) findViewById(R.id.editTextMin);
        editMax = (EditText) findViewById(R.id.editTextMax);
        btnRandom = (Button) findViewById(R.id.buttonRandom);
        result = (TextView) findViewById(R.id.result);


        //Random Application
        txtTitle.setText("True Random Number Generator");
        min.setText("Min");
        max.setText("Max");
        editMin.setText("1");
        editMax.setText("100");
        btnRandom.setText("Generate");



        // handle click
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueMin = String.valueOf(editMin.getText());
                String valueMax = String.valueOf(editMax.getText());
                if (Integer.parseInt(valueMin) > Integer.parseInt(valueMax)) {
                    result.setText("Min is not bigger than Max");
                    return;  // Exit if min is greater than max
                }

                Random random = new Random();
                int min = Integer.parseInt(valueMin);
                int max = Integer.parseInt(valueMax);

                // Generate a random number between min (inclusive) and max (inclusive)
                int randomValue = random.nextInt((max - min) + 1) + min;

                result.setText(String.valueOf(randomValue));
            }
        });


    }
}