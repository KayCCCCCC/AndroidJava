package com.example.lab5b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder>{
    private ArrayList<Device> deviceList;
    private OnItemClickListener listener;

    // Interface để giao tiếp sự kiện
    public interface OnItemClickListener {
        void onEdit(int position);

        void onDelete(int position);
    }

    // Hàm để set listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public DeviceAdapter(ArrayList<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Device device = deviceList.get(position);
        holder.titleDevice.setText(device.getTitleDevice());
        holder.descDevice.setText(device.getDescDevice());
        holder.typeDevice.setText(device.getType());
        holder.imageDevice.setImageURI(device.getImageDevice());

    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleDevice;
        TextView descDevice;
        TextView typeDevice;
        ImageView imageDevice;
        ImageView menuOptions;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            titleDevice = itemView.findViewById(R.id.titleDevice);
            descDevice = itemView.findViewById(R.id.descDevice);
            typeDevice = itemView.findViewById(R.id.typeDevice);
            imageDevice = itemView.findViewById(R.id.imageDevice);
            menuOptions = itemView.findViewById(R.id.menuOptions);

            menuOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo PopupMenu
                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_item, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION && listener != null) {
                                int id = item.getItemId();
                                if (id == R.id.action_edit) {
                                    listener.onEdit(position);
                                    return true;
                                } else if (id == R.id.action_delete) {
                                    listener.onDelete(position);
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            });
        }
    }

    // Hàm để thêm Device mới
    public void addDevice(Device device) {
        deviceList.add(device);
        notifyItemInserted(deviceList.size() - 1);
    }

    // Hàm để cập nhật Device
    public void updateDevice(int position, Device device) {
        deviceList.set(position, device);
        notifyItemChanged(position);
    }

    // Hàm để xóa Device
    public void removeDevice(int position) {
        deviceList.remove(position);
        notifyItemRemoved(position);
    }
}
