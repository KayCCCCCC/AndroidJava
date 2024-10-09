package hieutt.com.sqlitedatabase;

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
    ListView lvCongViec;
    JobAdapter adapter;
    ArrayList<Job> jobArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MENU", "onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        lvCongViec = (ListView) findViewById(R.id.lvCongViec);
        jobArrayList = new ArrayList<>();
        adapter = new JobAdapter(this, R.layout.dong_cong_viec, jobArrayList);
        lvCongViec.setAdapter(adapter);


        database = new Database(this, "GhiChu.sqlite", null, 1);
        database.QueryData("Create table if not exists CongViec(id Integer Primary Key Autoincrement,TenCV nvarchar(200))");

        //insert
//        database.QueryData("Insert into CongViec values(null,'Lam bai tap')");
//        database.QueryData("Insert into CongViec values(null,'Design App')");


        GetDataCongViec();

    }

    private void GetDataCongViec() {
        Cursor dataCongViec = database.GetData("Select * from CongViec");
        jobArrayList.clear();
        while (dataCongViec.moveToNext()) {
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            Job job = new Job(id, ten);
            jobArrayList.add(job);

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MENU", "onCreateOptionsMenu: inflating menu");
        getMenuInflater().inflate(R.menu.add_job, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemThem) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        Dialog dialogThem = new Dialog(this);
        dialogThem.setContentView(R.layout.dialog_add_job);

        EditText editText = (EditText) dialogThem.findViewById(R.id.editTextText);
        Button btnAdd = (Button) dialogThem.findViewById(R.id.add);
        Button btnCancel = (Button) dialogThem.findViewById(R.id.cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCv = editText.getText().toString();

                if (tenCv.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui long nhap ten cong viec", Toast.LENGTH_SHORT).show();
                } else {
                    database.QueryData("Insert into CongViec values(null,'" + tenCv + "')");
                    Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
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
        dialog.setContentView(R.layout.dialog_update);
        EditText editText = (EditText) dialog.findViewById(R.id.editTextText);
        Button btnUpdate = (Button) dialog.findViewById(R.id.update);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);


        editText.setText(ten);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editText.getText().toString().trim();
                database.QueryData("UPDATE CongViec SET TenCv = '" + newName + "' WHERE id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
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
        dialodXoa.setMessage("Ban co muon xoa cong viec "+tencv+" khong?");
        dialodXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM CongViec WHERE id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Da xoa thanh cong", Toast.LENGTH_SHORT).show();
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