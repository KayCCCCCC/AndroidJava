package com.example.normal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<User> userList;

    public Adapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // execute scroll
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.usernameTextView.setText(userList.get(position).getUsername());
        holder.fullNameTextView.setText(userList.get(position).getFullName());
        holder.emailTextView.setText(userList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    protected TextView usernameTextView;
    protected TextView fullNameTextView;
    protected TextView emailTextView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        usernameTextView = itemView.findViewById(R.id.textViewUserName);
        fullNameTextView = itemView.findViewById(R.id.textViewFullName);
        emailTextView = itemView.findViewById(R.id.textViewEmail);
    }
}