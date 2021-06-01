package com.example.spendingtracker_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText editTextTextDescription2;
    EditText editTextValue2;
    EditText editTextDate2;
    ImageButton imageButtonUpdate;

    String id, description, value, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextTextDescription2 = findViewById(R.id.editTextTextDescription2);
        editTextValue2 = findViewById(R.id.editTextValue2);
        editTextDate2 = findViewById(R.id.editTextDate2);
        imageButtonUpdate = findViewById(R.id.imageButtonUpdate);
        imageButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("description")
           && getIntent().hasExtra("value") && getIntent().hasExtra("date")){

                id = getIntent().getStringExtra("id");
                description = getIntent().getStringExtra("description");
                value = getIntent().getStringExtra("value");
                date = getIntent().getStringExtra("date");

                editTextTextDescription2.setText(description);
                editTextValue2.setText(value);
                editTextDate2.setText(date);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}