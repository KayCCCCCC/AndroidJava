package com.example.sqlitedatabase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView listViewCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listViewCongViec = findViewById(R.id.listView);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.activity_item, arrayCongViec);
        listViewCongViec.setAdapter(adapter);


        // create database ghichu
        database = new Database(this, "GhiChu.sqlite", null, 1);

        // create table congviec
        database.QueryData("Create table if not exists CongViec(id Integer Primary Key Autoincrement,"
        + "TenCV nvarchar(200))"
        );

        // insert data
/*        database.QueryData("Insert into CongViec values(null, 'Android')");
        database.QueryData("Insert into CongViec values(null, 'IOS')");*/

        GetDataCongViec();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MENU", "onCreateOptionsMenu: inflating menu");
        getMenuInflater().inflate(R.menu.item_add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemThem) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetDataCongViec() {
        // Truy vấn để lấy dữ liệu và sắp xếp theo id giảm dần
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        arrayCongViec.clear();
        while (dataCongViec.moveToNext()) {
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            CongViec congViec = new CongViec(id, ten);
            arrayCongViec.add(congViec);
        }
        adapter.notifyDataSetChanged();
    }



    private void DialogThem() {
        Dialog dialogThem = new Dialog(this);
        dialogThem.setContentView(R.layout.popup_add_item);

        EditText editText = (EditText) dialogThem.findViewById(R.id.editTextText);
        Button btnAdd = (Button) dialogThem.findViewById(R.id.add);
        Button btnCancel = (Button) dialogThem.findViewById(R.id.cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCv = editText.getText().toString();

                if (tenCv.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter name job", Toast.LENGTH_SHORT).show();
                } else {
                    database.QueryData("Insert into CongViec values(null,'" + tenCv + "')");
                    Toast.makeText(MainActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                    dialogThem.dismiss();

                    GetDataCongViec();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThem.dismiss();
            }
        });

        dialogThem.show();
    }

    public void DialogUpdate(String ten, int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_update_item);
        EditText editText = (EditText) dialog.findViewById(R.id.editTextText);
        Button btnUpdate = (Button) dialog.findViewById(R.id.update);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);


        editText.setText(ten);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editText.getText().toString().trim();
                database.QueryData("UPDATE CongViec SET TenCv = '" + newName + "' WHERE id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Update success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public  void DialogDelete(String tencv, int id){
        AlertDialog.Builder dialodXoa = new AlertDialog.Builder(this);
        dialodXoa.setMessage("Are you sure remove "+tencv+" ?");
        dialodXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM CongViec WHERE id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Remove success", Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });
        dialodXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialodXoa.show();
    }
}