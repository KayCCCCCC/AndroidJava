package com.example.intentinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DrinkAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Drink> drinkList;

    public DrinkAdapter(Context context, int layout, List<Drink> drinkList) {
        this.context = context;
        this.layout = layout;
        this.drinkList = drinkList;
    }

    @Override
    public int getCount() {
        return drinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return drinkList.get(position);
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

        Drink drink = drinkList.get(position);

        txtName.setText(drink.getName());
        txtDescription.setText(drink.getDescription());
        image.setImageResource(drink.getImage());
        txtPrice.setText("Price: " + drink.getPrice() + "đ");

        return convertView;
    }
}
