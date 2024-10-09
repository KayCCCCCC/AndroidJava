package com.example.lab5b;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeviceActivity extends AppCompatActivity {

    ArrayList<Device> deviceList = new ArrayList<>();
    Button btnAddDevice;
    DeviceAdapter deviceAdapter;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri = null;

    private ImageView dialogImageView; // Tham chiếu đến ImageView trong dialog
    private int currentEditPosition = -1; // Vị trí hiện tại khi chỉnh sửa
    private boolean isEditMode = false; // Kiểm tra chế độ thêm hay sửa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_device);

        RecyclerView rvDevice = findViewById(R.id.rvDeviceList);
        btnAddDevice = findViewById(R.id.btn_add_device);

        String prefixUri = "android.resource://" + getPackageName() + "/";
        deviceList.add(new Device("iPhone", "iPhone là dòng điện thoại thông minh do Apple phát triển.", Uri.parse(prefixUri + R.drawable.iphone), "iOS"));
        deviceList.add(new Device("Samsung", "Samsung là một trong những hãng sản xuất điện thoại lớn nhất thế giới.", Uri.parse(prefixUri + R.drawable.samsung), "Android"));
        deviceList.add(new Device("Oppo", "Oppo là thương hiệu điện thoại nổi tiếng với các tính năng camera vượt trội.", Uri.parse(prefixUri + R.drawable.oppo), "Android"));
        deviceAdapter = new DeviceAdapter(deviceList);
        rvDevice.setAdapter(deviceAdapter);
        rvDevice.setLayoutManager(new LinearLayoutManager(this));

        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDeviceDialog();
            }
        });

        // Trong phần adapter
        deviceAdapter.setOnItemClickListener(new DeviceAdapter.OnItemClickListener() {
            @Override
            public void onEdit(int position) {
                showEditDeviceDialog(position);
            }

            @Override
            public void onDelete(int position) {
                showDeleteConfirmationDialog(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            if (dialogImageView != null) {
                dialogImageView.setImageURI(selectedImageUri);
            }
        }
    }

    private void showAddDeviceDialog() {
        selectedImageUri = null; // Đảm bảo URI hình ảnh được đặt lại khi thêm mới

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Device");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.popup_add_device, null, false);

        final EditText etTitle = viewInflated.findViewById(R.id.etTitle);
        final EditText etDescription = viewInflated.findViewById(R.id.etDescription);
        final EditText etType = viewInflated.findViewById(R.id.etType);
        dialogImageView = viewInflated.findViewById(R.id.ivImage); // Lưu tham chiếu đến ImageView

        dialogImageView.setOnClickListener(v -> {
            // Mở bộ chọn ảnh
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        });

        builder.setView(viewInflated);

        builder.setPositiveButton("Add", (dialog, which) -> {
            dialog.dismiss();
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String type = etType.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty() || type.isEmpty()) {
                Toast.makeText(DeviceActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri imageUri = selectedImageUri != null ? selectedImageUri : Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.iphone);

            Device newDevice = new Device(title, description, imageUri, type);
            deviceAdapter.addDevice(newDevice);
            Toast.makeText(DeviceActivity.this, "Device added", Toast.LENGTH_SHORT).show();

            selectedImageUri = null; // Reset sau khi thêm
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showEditDeviceDialog(final int position) {
        selectedImageUri = null; // Đảm bảo URI hình ảnh được đặt lại khi chỉnh sửa

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Device");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.popup_update_device, null, false);

        final EditText etTitle = viewInflated.findViewById(R.id.etTitle);
        final EditText etDescription = viewInflated.findViewById(R.id.etDescription);
        final EditText etType = viewInflated.findViewById(R.id.etType);
        dialogImageView = viewInflated.findViewById(R.id.ivImage); // Lưu tham chiếu đến ImageView

        // Điền dữ liệu hiện tại vào các trường
        Device device = deviceList.get(position);
        etTitle.setText(device.getTitleDevice());
        etDescription.setText(device.getDescDevice());
        etType.setText(device.getType());
        dialogImageView.setImageURI(device.getImageDevice());

        dialogImageView.setOnClickListener(v -> {
            // Mở bộ chọn ảnh
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        });

        builder.setView(viewInflated);

        builder.setPositiveButton("Update", (dialog, which) -> {
            dialog.dismiss();
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String type = etType.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty() || type.isEmpty()) {
                Toast.makeText(DeviceActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri imageUri = selectedImageUri != null ? selectedImageUri : device.getImageDevice();

            Device updatedDevice = new Device(title, description, imageUri, type);
            deviceAdapter.updateDevice(position, updatedDevice);
            Toast.makeText(DeviceActivity.this, "Device updated", Toast.LENGTH_SHORT).show();

            selectedImageUri = null; // Reset sau khi chỉnh sửa
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }


    // Phương thức hiển thị dialog xác nhận xóa Device
    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Device");
        builder.setMessage("Are you sure you want to delete this device?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            deviceAdapter.removeDevice(position);
            Toast.makeText(DeviceActivity.this, "Device deleted", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
