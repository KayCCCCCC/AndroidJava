package com.example.intentinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_FOOD = 1;
    private static final int REQUEST_DRINK = 2;
    private ListView listViewOrder;
    private ArrayList<Item> orderList;  // Danh sách các món đã đặt (cả món ăn và đồ uống)
    private OrderAdapter adapter;
    private TextView txtTotal;
    private int totalAmount = 0;  // Tổng tiền
    private Button btnFood;
    private Button btnDrink;
    private Button btnExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewOrder = findViewById(R.id.listView);
        txtTotal = findViewById(R.id.textResult);
        orderList = new ArrayList<>();

        btnFood = findViewById(R.id.buttonFood);
        btnDrink = findViewById(R.id.buttonDrink);
        btnExist = findViewById(R.id.buttonExist);

        // Khởi tạo adapter cho ListView
        adapter = new OrderAdapter(this, R.layout.item_layout, orderList);
        listViewOrder.setAdapter(adapter);

        // Button để chọn món ăn
        btnFood.setOnClickListener(v -> {
            Intent intentFood = new Intent(MainActivity.this, FoodActivity.class);
            startActivityForResult(intentFood, REQUEST_FOOD);  // Sử dụng startActivityForResult
        });

        // Button để chọn đồ uống
        btnDrink.setOnClickListener(v -> {
            Intent intentDrink = new Intent(MainActivity.this, DrinkActivity.class);
            startActivityForResult(intentDrink, REQUEST_DRINK);  // Sử dụng startActivityForResult
        });

        // Xử lý sự kiện khi nhấn nút Exit
        btnExist.setOnClickListener(v -> {
            // Thoát Activity hiện tại
            finish();
            // Thoát ứng dụng hoàn toàn (nếu muốn)
            System.exit(0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            // Lấy Bundle từ Intent
            Bundle bundle = data.getBundleExtra("dataBundle");

            if (bundle != null) {
                // Lấy thông tin từ Bundle
                String itemName = bundle.getString("itemName");
                String itemDescription = bundle.getString("itemDescription");
                String itemPrice = bundle.getString("itemPrice");
                int itemImage = bundle.getInt("itemImage");

                // Thêm món ăn/đồ uống vào danh sách
                orderList.add(new Item(itemName, itemDescription, Integer.parseInt(itemPrice), itemImage));

                // Tính tổng tiền
                totalAmount += Integer.parseInt(itemPrice);
                txtTotal.setText("Total Price: " + totalAmount + "đ");

                // Cập nhật ListView
                adapter.notifyDataSetChanged();
            }
        }
    }
}