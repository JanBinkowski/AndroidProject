package com.example.spendingtracker_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView RecyclerViewList;
    FloatingActionButton ButtonAddRecord;

    myDatabaseHelper myDB;
    ArrayList<String> spend_id, spend_description, spend_value, spend_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerViewList = findViewById(R.id.RecyclerViewList);
        ButtonAddRecord = findViewById(R.id.ButtonAddRecord);

        ButtonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}