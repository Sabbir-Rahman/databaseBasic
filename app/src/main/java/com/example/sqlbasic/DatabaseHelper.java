package com.example.sqlbasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "hello.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String ID = "ID";
    public static final String USER_FULL_NAME = "Fullname";
    public static final String USER_NAME = "Username";
    public static final String NATIONALITY = "Nationality";
    public static final String AGE = "Age";
    public static final String BLOOD_GROUP = "Blood_group";
    public static final String OCCUPATION= "Occupation";
    public static final String GENDER = "Gender";
    public static final String PHONE = "Phone";
    public static final String EMAIL = "Email";
    public static final String ADDRESS = "Address";
    public static final String EMERGENCY_CONTACT = "Emergency_contact";
    public static final String PASS_WORD = "Password";
    public static final String EXTRA1 = "Marks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase dbRegister) {
        System.out.println("On Create is called");


        dbRegister.execSQL("CREATE TABLE " + TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_FULL_NAME + " TEXT , "
                + AGE + " TEXT , "
                + EXTRA1 + " TEXT "
                + ");"
        );
        System.out.println("Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbRegister, int oldVersion, int newVersion) {
        dbRegister.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        System.out.println("table dropped");
        onCreate(dbRegister);
    }

    public boolean insertData(String name,String age,String marks){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(USER_FULL_NAME,name);
          contentValues.put(AGE,age);
          contentValues.put(EXTRA1,marks);
          long result = db.insert(TABLE_NAME,null,contentValues);
          if(result == -1)
              return false;
          else
              return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME,null);
        return res;
    }


}