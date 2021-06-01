package com.example.spendingtracker_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddRecordActivity extends AppCompatActivity {
    EditText editTextTextDescription;
    EditText editTextValue;
    EditText editTextDate;
    ImageButton imageButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        editTextTextDescription = findViewById(R.id.editTextTextDescription);
        editTextValue = findViewById(R.id.editTextValue);
        editTextDate = findViewById(R.id.editTextDate);
        imageButtonSend = findViewById(R.id.imageButtonSend);

        imageButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper myDB = new myDatabaseHelper(AddRecordActivity.this);
                myDB.addItemToDatabase(editTextTextDescription.getText().toString().trim(),
                        Integer.valueOf(editTextValue.getText().toString().trim()),
                        editTextDate.getText().toString().trim());
            }
        });
    }
}