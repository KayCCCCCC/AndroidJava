package com.example.caculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView Title;
    TextView Result;
    EditText EditText1;
    EditText EditText2;
    Button BtnAdd;
    Button BtnSub;
    Button BtnMul;
    Button BtnDiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Title = (TextView) findViewById(R.id.Title);
        Result = (TextView) findViewById(R.id.Result);
        EditText1 = (EditText) findViewById(R.id.editNumber1);
        EditText2 = (EditText) findViewById(R.id.editNumber2);
        BtnAdd = (Button) findViewById(R.id.btnAdd);
        BtnSub = (Button) findViewById(R.id.btnSub);
        BtnMul = (Button) findViewById(R.id.btnMulti);
        BtnDiv = (Button) findViewById(R.id.btnDivi);

        Title.setText("Kết Quả:");

        // Handle Add
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = EditText1.getText().toString().trim();
                String value2 = EditText2.getText().toString().trim();

                if (value1.isEmpty() || value2.isEmpty()) {
                    Result.setText("2 số cần phải được nhập");
                    return;
                }

                try {
                    int num1 = Integer.parseInt(value1);
                    int num2 = Integer.parseInt(value2);

                    if (num1 < 0 || num2 < 0) {
                        Result.setText("2 số không được là số âm");
                        return;
                    }

                    int AddResult = num1 + num2;
                    Result.setText(AddResult + "");

                } catch (NumberFormatException e) {

                    Result.setText("Cả 2 giá trị đều phải là số");
                }
            }
        });

        // Handle Sub
        BtnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = EditText1.getText().toString().trim();
                String value2 = EditText2.getText().toString().trim();

                if (value1.isEmpty() || value2.isEmpty()) {
                    Result.setText("2 số cần phải được nhập");
                    return;
                }


                try {
                    int num1 = Integer.parseInt(value1);
                    int num2 = Integer.parseInt(value2);

                    if (num1 < 0 || num2 < 0) {
                        Result.setText("2 số không được là số âm");
                        return;
                    }

                    if (num2 > num1) {
                        Result.setText("Số trừ không được lớn hơn số bị trừ");
                        return;
                    }

                    // Thực hiện phép trừ
                    int SubResult = num1 - num2;
                    Result.setText(SubResult + "");

                } catch (NumberFormatException e) {
                    Result.setText("Cả 2 giá trị đều phải là số");
                }
            }
        });

        // Handle Mul
        BtnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = EditText1.getText().toString().trim();
                String value2 = EditText2.getText().toString().trim();

                if (value1.isEmpty() || value2.isEmpty()) {
                    Result.setText("2 số cần phải được nhập");
                    return;
                }

                try {
                    int num1 = Integer.parseInt(value1);
                    int num2 = Integer.parseInt(value2);

                    if (num1 < 0 || num2 < 0) {
                        Result.setText("Số nhập không thể là số âm");
                        return;
                    }

                    // Thực hiện phép nhân
                    int MulResult = num1 * num2;
                    Result.setText(MulResult + "");

                } catch (NumberFormatException e) {
                    Result.setText("Cả 2 giá trị đều phải là số");
                }
            }
        });

        // Handle Div
        BtnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = EditText1.getText().toString().trim();
                String value2 = EditText2.getText().toString().trim();

                if (value1.isEmpty() || value2.isEmpty()) {
                    Result.setText("2 số cần phải được nhập");
                    return;
                }

                try {
                    int num1 = Integer.parseInt(value1);
                    int num2 = Integer.parseInt(value2);

                    if (num1 < 0 || num2 < 0) {
                        Result.setText("Số nhập không thể là số âm");
                        return;
                    }

                    // Kiểm tra chia cho 0
                    if (num2 == 0) {
                        Result.setText("Số bị chia không thể là số 0");
                        return;
                    }

                    // Thực hiện phép chia
                    double DivResult = (double) num1 / num2;
                    Result.setText(DivResult+"");

                } catch (NumberFormatException e) {
                    Result.setText("Cả 2 giá trị đều phải là số");
                }
            }
        });

    }
}