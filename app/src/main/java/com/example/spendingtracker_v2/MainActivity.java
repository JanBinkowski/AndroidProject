package com.example.spendingtracker_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
            Snackbar snackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),"There is no data yet. \nClick 'plus' button to add data.",
                    Snackbar.LENGTH_SHORT)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setDuration(8000).show();
        }else{
            while(cursor.moveToNext()){
                spend_id.add(cursor.getString(0));
                spend_description.add(cursor.getString(1));
                spend_value.add(cursor.getString(2));
                spend_date.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        if(item.getItemId() == R.id.total){
           // displayTotalSpendingInSnackBar();
            displayInfo2();
        }
        return super.onOptionsItemSelected(item);

    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Confirmation");
        builder.setMessage("Are you sure to delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabaseHelper myDB = new myDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    void displayTotalSpendingInSnackBar(){
        myDatabaseHelper myDB = new myDatabaseHelper(this);
        double totalSpend = myDB.getTotalOfAmount();
        Snackbar snackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),"Your total spending: "+totalSpend+" PLN",
                Snackbar.LENGTH_SHORT)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.show();
    }

    void displayInfo2(){
        myDatabaseHelper myDB = new myDatabaseHelper(this);
        double totalSpend = myDB.getTotalOfAmount();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Total Spending");
        builder.setMessage("Your total spending as far is: "+totalSpend+" PLN");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}