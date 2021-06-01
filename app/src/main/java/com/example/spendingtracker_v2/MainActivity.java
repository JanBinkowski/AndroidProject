package com.example.spendingtracker_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    RecyclerView RecyclerViewList;
    FloatingActionButton ButtonAddRecord;

    myDatabaseHelper myDB;
    ArrayList<String> spend_id, spend_description, spend_value, spend_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerViewList = findViewById(R.id.RecyclerViewList);
        ButtonAddRecord = findViewById(R.id.ButtonAddRecord);

        ButtonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
        });

        myDB = new myDatabaseHelper(MainActivity.this);
        spend_id = new ArrayList<>();
        spend_description = new ArrayList<>();
        spend_value = new ArrayList<>();
        spend_date = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this, spend_id, spend_description, spend_value, spend_date);
        RecyclerViewList.setAdapter(customAdapter);
        RecyclerViewList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 ){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                spend_id.add(cursor.getString(0));
                spend_description.add(cursor.getString(1));
                spend_value.add(cursor.getString(2));
                spend_date.add(cursor.getString(3));
            }
        }
    }


}