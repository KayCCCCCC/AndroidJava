package com.example.intentinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    private ListView listViewFood;
    private ArrayList<Food> foodList;
    private FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects);

        listViewFood = findViewById(R.id.listViewObjects);
        foodList = new ArrayList<>();

        // Thêm các đồ uống vào danh sách
        foodList.add(new Food("Phở Hà Nội", "Phở Hà Nội với nước dùng đậm đà", 55000, R.drawable.phohanoi));
        foodList.add(new Food("Bún Bò Huế", "Bún bò Huế cay nồng với giò heo", 60000, R.drawable.bunbohue));
        foodList.add(new Food("Mì Quảng", "Mì Quảng đậm chất miền Trung", 40000, R.drawable.miquang));
        foodList.add(new Food("Hủ Tíu Sài Gòn", "Hủ tiếu Sài Gòn với nước dùng thanh", 35000, R.drawable.hutieusaigon));
        foodList.add(new Food("Cơm Tấm", "Cơm Tấm", 35000, R.drawable.comtam));
        foodList.add(new Food("Cơm Dương Châu", "Cơm chiên Dương Châu", 40000, R.drawable.comduongchau));
        foodList.add(new Food("Bún Cá", "Bún cá", 30000, R.drawable.buncakiengiang));
        foodList.add(new Food("Bún Riêu", "Bún riêu cua", 25000, R.drawable.bunrieu));
        foodList.add(new Food("Hủ Tiếu", "Hủ tiếu", 27000, R.drawable.hutieu));
        foodList.add(new Food("Phở Bò", "Phở bò với nước dùng hầm xương", 50000, R.drawable.phobovietnam));
        foodList.add(new Food("Cánh Gà Chiên Mắm", "Cánh gà chiên giòn với mắm", 45000, R.drawable.canghagia));
        foodList.add(new Food("Bánh Mì", "Bánh mì kẹp thịt", 20000, R.drawable.banhmi));
        foodList.add(new Food("Gỏi Cuốn", "Gỏi cuốn tươi ngon", 30000, R.drawable.goicuon));
        foodList.add(new Food("Nem Chua", "Nem chua rán giòn ngon", 25000, R.drawable.nemchua));



        adapter = new FoodAdapter(this, R.layout.item_layout, foodList);
        listViewFood.setAdapter(adapter);

        listViewFood.setOnItemClickListener((parent, view, position, id) -> {
            Intent resultIntent = new Intent();
            Food selectedFood = foodList.get(position);

            // Tạo Bundle và đưa dữ liệu vào
            Bundle bundle = new Bundle();
            bundle.putString("itemName", selectedFood.getName());
            bundle.putString("itemDescription", selectedFood.getDescription());
            bundle.putString("itemPrice", String.valueOf(selectedFood.getPrice()));
            bundle.putInt("itemImage", selectedFood.getImage());

            // Đưa Bundle vào Intent
            resultIntent.putExtra("dataBundle", bundle);

            // Trả kết quả về MainActivity
            setResult(RESULT_OK, resultIntent);
            finish();
        });


    }
}
