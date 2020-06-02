package com.example.sqlbasic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText editName,editAge,editMarks;
    Button addData,viewAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = mydb.getReadableDatabase();
        editName = findViewById(R.id.editTextTextPersonName);
        editAge = findViewById(R.id.editTextTextPersonName2);
        editMarks = findViewById(R.id.editTextTextPersonName3);
        addData = findViewById(R.id.button);
        viewAll = findViewById(R.id.button2);
        AddData();
        viewAll();
    }

    public void AddData(){
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted = mydb.insertData(editName.getText().toString(),editAge.getText().toString(),editMarks.getText().toString());
               if(isInserted = true)
                   Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewAll(){
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res = mydb.getAllData();
               if(res.getCount()==0){
                   Toast.makeText(MainActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                   showMessage("Error","Nothing Found");
                   return;
               }
               StringBuffer buffer = new StringBuffer();
               while (res.moveToNext()){
                   buffer.append("Id :"+res.getString(0)+"\n");
                   buffer.append("Name :"+res.getString(1)+"\n");
                   buffer.append("Age :"+res.getString(0)+"\n");
                   buffer.append("Marks :"+res.getString(0)+"\n\n");
               }
               showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
