package com.example.intentinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView txtName = convertView.findViewById(R.id.textTen);
        TextView txtDescription = convertView.findViewById(R.id.txtMoTa);
        ImageView image = convertView.findViewById(R.id.imgHinh);
        TextView txtPrice = convertView.findViewById(R.id.txtGia);

        Food food = foodList.get(position);

        txtName.setText(food.getName());
        txtDescription.setText(food.getDescription());
        image.setImageResource(food.getImage());
        txtPrice.setText("Price: " + food.getPrice() + "đ");

        return convertView;
    }
}
