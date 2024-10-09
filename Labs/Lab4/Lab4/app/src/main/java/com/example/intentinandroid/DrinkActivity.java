package com.example.intentinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {
    private ListView listViewDrink;
    private ArrayList<Drink> drinkList;
    private DrinkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects);

        listViewDrink = findViewById(R.id.listViewObjects);
        drinkList = new ArrayList<>();

        // Thêm các đồ uống vào danh sách
        drinkList.add(new Drink("Pepsi", "Nước giải khát Pepsi", 10000, R.drawable.pepsi));
        drinkList.add(new Drink("Heineken", "Bia Heineken", 20000, R.drawable.heineken));
        drinkList.add(new Drink("Tiger", "Bia Tiger", 18000, R.drawable.tiger));
        drinkList.add(new Drink("Sài Gòn Đỏ", "Bia Sài Gòn Đỏ", 15000, R.drawable.saigondo));
        drinkList.add(new Drink("Cà Phê Đen", "Cà phê đen", 15000, R.drawable.capheden));
        drinkList.add(new Drink("Cà Phê Sữa", "Cà phê sữa", 20000, R.drawable.caphesua));
        drinkList.add(new Drink("Bạc Xỉu", "Bạc xỉu", 28000, R.drawable.bacxiu));
        drinkList.add(new Drink("Trà Sữa", "Trà sữa trân châu", 25000, R.drawable.trasua));
        drinkList.add(new Drink("Rau Má", "Nước rau má", 17000, R.drawable.rauma));
        drinkList.add(new Drink("Trà Đá", "Trà đá thanh mát", 10000, R.drawable.traduong));
        drinkList.add(new Drink("Nước Dừa", "Nước dừa tươi", 20000, R.drawable.nuocdua));
        drinkList.add(new Drink("Soda Chanh", "Nước soda chanh", 25000, R.drawable.sodachanh));
        drinkList.add(new Drink("Nước Cam", "Nước cam ép tươi", 18000, R.drawable.nuoccam));
        drinkList.add(new Drink("Sinh Tố Bơ", "Sinh tố bơ mát lạnh", 30000, R.drawable.sinhtobovietnam));



        adapter = new DrinkAdapter(this, R.layout.item_layout, drinkList);
        listViewDrink.setAdapter(adapter);

        listViewDrink.setOnItemClickListener((parent, view, position, id) -> {
            Intent resultIntent = new Intent();
            Drink selectedDrink = drinkList.get(position);

            // Tạo Bundle và đưa dữ liệu vào
            Bundle bundle = new Bundle();
            bundle.putString("itemName", selectedDrink.getName());
            bundle.putString("itemDescription", selectedDrink.getDescription());
            bundle.putString("itemPrice", String.valueOf(selectedDrink.getPrice()));
            bundle.putInt("itemImage", selectedDrink.getImage());

            // Đưa Bundle vào Intent
            resultIntent.putExtra("dataBundle", bundle);

            // Trả kết quả về MainActivity
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
