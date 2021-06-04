package com.example.spendingtracker_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddRecordActivity extends AppCompatActivity {
    EditText editTextTextDescription;
    EditText editTextValue;
    EditText editTextDate;
    ImageButton ButtonSend;
    ImageButton buttonDate;
    DatePickerDialog datepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        editTextTextDescription = findViewById(R.id.editTextTextDescription);
        editTextValue = findViewById(R.id.editTextValue);
        editTextDate = findViewById(R.id.editTextDate);
        ButtonSend = findViewById(R.id.ButtonSend);
        buttonDate = findViewById(R.id.buttonDate);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(AddRecordActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.show();
            }
        });

        ButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper myDB = new myDatabaseHelper(AddRecordActivity.this);
                myDB.addItemToDatabase(editTextTextDescription.getText().toString().trim(),
                        editTextValue.getText().toString().trim(),
                        editTextDate.getText().toString().trim());

            }
        });
    }
}