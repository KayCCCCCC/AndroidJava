package com.example.customlistview;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listFruit;
    ArrayList<Fruit> arrayFruit;
    FruitAdapter adapter;

    // crud
    Button addBtn;
    Button editBtn;
    Button removeBtn;
    Button selectImage;
    ImageView imageView;
    EditText name;
    EditText description;

    private Uri selectedImageUri;
    private int selectedPosition;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        AnhXa();
        adapter = new FruitAdapter(this, R.layout.item_layout, arrayFruit);
        listFruit.setAdapter(adapter);

        selectImage = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView2);
        addBtn = (Button) findViewById(R.id.button2);
        editBtn = (Button) findViewById(R.id.button3);
        removeBtn = (Button) findViewById(R.id.button4);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        registerResult();

        selectImage.setOnClickListener(view -> pickImage());

        name = (EditText) findViewById(R.id.editTextText);
        description = (EditText) findViewById(R.id.editTextText2);

        listFruit.setOnItemClickListener((parent, view, position, id) -> {
            addBtn.setVisibility(View.INVISIBLE);
            editBtn.setVisibility(View.VISIBLE);
            removeBtn.setVisibility(View.VISIBLE);
            selectedPosition = position;
            Fruit selectedFruit = arrayFruit.get(position);
            name.setText(selectedFruit.getName());
            description.setText(selectedFruit.getDescription());
            selectedImageUri = selectedFruit.getImage();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //add

        addBtn.setVisibility(View.VISIBLE);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fruitName = name.getText().toString();
                String fruitDescription = description.getText().toString();
                if (selectedImageUri != null && !fruitName.isEmpty() && !fruitDescription.isEmpty()) {
                    addItem(fruitName, fruitDescription, selectedImageUri);
                    name.setText("");
                    description.setText("");
                    imageView.setImageResource(0);
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all fields and select an image.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //edit

        editBtn.setVisibility(View.INVISIBLE);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition >= 0) {
                    String fruitName = name.getText().toString();
                    String fruitDescription = description.getText().toString();
                    if (selectedImageUri != null && !fruitName.isEmpty() && !fruitDescription.isEmpty()) {
                        updateItem(selectedPosition, fruitName, fruitDescription, selectedImageUri);
                        name.setText("");
                        description.setText("");
                        imageView.setImageResource(0);
                        selectedPosition = -1;
                        editBtn.setVisibility(View.INVISIBLE);
                        removeBtn.setVisibility(View.INVISIBLE);
                        addBtn.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(MainActivity.this, "Please fill in all fields and select an image.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //remove
        removeBtn.setVisibility(View.INVISIBLE);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition >= 0) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Confirm")
                            .setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                                deleteItem(selectedPosition);
                                selectedPosition = -1;
                                name.setText("");
                                description.setText("");
                                imageView.setImageResource(0);
                                Toast.makeText(MainActivity.this, "Fruit removed.", Toast.LENGTH_SHORT).show();
                                removeBtn.setVisibility(View.INVISIBLE);
                                editBtn.setVisibility(View.INVISIBLE);
                                addBtn.setVisibility(View.VISIBLE);
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Please select a fruit to remove.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void AnhXa(){
        listFruit = findViewById(R.id.listItem);
        arrayFruit = new ArrayList<>();
        arrayFruit.add(new Fruit("Dưa Hấu (Watermelon)", "A large, round fruit with a sweet, juicy red flesh. Rich in vitamins A and C.", Uri.parse("android.resource://"+ getPackageName() + "/" + R.drawable.duahau)));
        arrayFruit.add(new Fruit("Mít (Jackfruit)", "The largest tree-borne fruit, known for its strong aroma and sweet, fleshy lobes. A good source of fiber and vitamin C.", Uri.parse("android.resource://"+ getPackageName() + "/" + R.drawable.mit)));
        arrayFruit.add(new Fruit("Sầu Riêng (Durian)", "A spiky fruit with a pungent odor and creamy, custardy flesh. An acquired taste, but highly prized in Southeast Asia. Rich in vitamins and minerals.", Uri.parse("android.resource://"+ getPackageName() + "/" + R.drawable.saurieng)));
        arrayFruit.add(new Fruit("Bơ (Avocado)", "A pear-shaped fruit with a smooth, buttery texture and a mild flavor. A great source of healthy fats, fiber, and potassium.", Uri.parse("android.resource://"+ getPackageName() + "/" + R.drawable.bo)));
        arrayFruit.add(new Fruit("Cam (Orange)", "A citrus fruit with a bright orange peel and a juicy, tangy flesh. Rich in vitamin C and antioxidants.", Uri.parse("android.resource://"+ getPackageName() + "/" + R.drawable.cam)));
    }

    private void addItem(String name, String description, Uri image) {
        arrayFruit.add(new Fruit(name, description, image));
        adapter.notifyDataSetChanged();
    }

    private void updateItem(int position, String name, String description, Uri image) {
        Fruit item = arrayFruit.get(position);
        item.setName(name);
        item.setDescription(description);
        item.setImage(image);
        adapter.notifyDataSetChanged();
    }

    private void deleteItem(int position) {
        arrayFruit.remove(position);
        adapter.notifyDataSetChanged();
    }

    private void registerResult() {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    if (imageUri != null) {
                        selectedImageUri = imageUri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                            imageView.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText(MainActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        resultLauncher.launch(Intent.createChooser(intent, "Select Image"));
    }
}