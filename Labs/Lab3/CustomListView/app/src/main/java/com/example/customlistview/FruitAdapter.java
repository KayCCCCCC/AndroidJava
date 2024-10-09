package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> listFruit;

    public FruitAdapter(Context context, int layout, List<Fruit> listFruit) {
        this.context = context;
        this.layout = layout;
        this.listFruit = listFruit;
    }

    @Override
    public int getCount() {
        return listFruit.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        // anh xa view
        TextView txtName = convertView.findViewById(R.id.textName);
        TextView txtDes = convertView.findViewById(R.id.textDes);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        // gán giá trị
        Fruit fruit = listFruit.get(position);

        txtName.setText(fruit.getName());
        txtDes.setText(fruit.getDescription());
        Glide.with(context)
                .load(fruit.getImage())
                .into(imageView);

        return convertView;
    }
}
