package com.example.intentinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Item> orderList;

    public OrderAdapter(Context context, int layout, List<Item> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        // Ánh xạ các view trong layout order_item
        TextView txtName = convertView.findViewById(R.id.textTen);
        TextView txtDescription = convertView.findViewById(R.id.txtMoTa);
        TextView txtPrice = convertView.findViewById(R.id.txtGia);
        ImageView image = convertView.findViewById(R.id.imgHinh);

        // Lấy item hiện tại từ orderList
        Item item = orderList.get(position);

        // Gán giá trị cho các TextView và ImageView
        txtName.setText(item.getName());
        txtDescription.setText(item.getDescription());
        image.setImageResource(item.getImage());
        txtPrice.setText("Price: " + item.getPrice() + "đ");

        return convertView;
    }
}
