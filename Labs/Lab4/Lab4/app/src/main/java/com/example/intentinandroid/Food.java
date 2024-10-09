package com.example.intentinandroid;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class Food extends TypeObjects {

    public Food(String name, String description, int price, int image) {
        super(name, description, price, image);
    }
}
